package meetingroom.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import lombok.Data;
import meetingroom.CalendarintegrationApplication;

@Entity
@Table(name = "Notification_table")
@Data
//<<< DDD / Aggregate Root
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;

    private String userId;

    @Embedded
    private Message message;

    @PostPersist
    public void onPostPersist() {
        CalendarCreated calendarCreated = new CalendarCreated(this);
        calendarCreated.publishAfterCommit();

        NotificationSent notificationSent = new NotificationSent(this);
        notificationSent.publishAfterCommit();

        CalendarDeleted calendarDeleted = new CalendarDeleted(this);
        calendarDeleted.publishAfterCommit();
    }

    public static NotificationRepository repository() {
        NotificationRepository notificationRepository = CalendarintegrationApplication.applicationContext.getBean(
            NotificationRepository.class
        );
        return notificationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void createCalendar(ReservationCreated reservationCreated) {
        //implement business logic here:

        Notification notification = new Notification();
        notification.setUserId(reservationCreated.getUserId());
        notification.message.setLocation(reservationCreated.getLacation());
        notification.message.setMeetingName(reservationCreated.getMeetingName());
        notification.message.setUsingEndDate(reservationCreated.getStartDate());
        notification.message.setUsingEndDate(reservationCreated.getEndDate());
        notification.message.setReservationStatus(ReservationStatus.RESERVED);
        repository().save(notification);

        CalendarCreated calendarCreated = new CalendarCreated(notification);
        calendarCreated.publishAfterCommit();
        

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendToUser(ReservationCreated reservationCreated) {
        //implement business logic here:

        
        repository().findById(reservationCreated.getReservationId()).ifPresent(notification->{
            
            notification.setUserId(reservationCreated.getUserId());
            notification.message.setLocation(reservationCreated.getLacation());
            notification.message.setMeetingName(reservationCreated.getMeetingName());
            notification.message.setUsingEndDate(reservationCreated.getStartDate());
            notification.message.setUsingEndDate(reservationCreated.getEndDate());
            notification.message.setReservationStatus(ReservationStatus.RESERVED); // do something
            repository().save(notification);

            NotificationSent notificationSent = new NotificationSent(notification);
            notificationSent.publishAfterCommit();

        });
    

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendToUser(ReservationModified reservationModified) {
        repository().findById(reservationModified.getReservationId()).ifPresent(notification->{
            
            notification.message.setMeetingName(reservationModified.getMeetingName());
            notification.message.setUsingEndDate(reservationModified.getStartDate());
            notification.message.setUsingEndDate(reservationModified.getEndDate());
            repository().save(notification);

            NotificationSent notificationSent = new NotificationSent(notification);
            notificationSent.publishAfterCommit();

        });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendToUser(ReservationCancelled reservationCancelled) {
        
        repository().findById(reservationCancelled.getReservationId()).ifPresent(notification->{
            
            notification.message.setReservationStatus(ReservationStatus.CANCELED); // do something
            repository().save(notification);

            NotificationSent notificationSent = new NotificationSent(notification);
            notificationSent.publishAfterCommit();

        });

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deleteCalendar(
        ReservationCancelled reservationCancelled
    ) {
        repository().findById(reservationCancelled.getReservationId()).ifPresent(notification->{
            
            notification.message.setReservationStatus(ReservationStatus.CANCELED); // do something
            repository().save(notification);

            CalendarDeleted calendarDeleted = new CalendarDeleted(notification);
            calendarDeleted.publishAfterCommit();


        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
