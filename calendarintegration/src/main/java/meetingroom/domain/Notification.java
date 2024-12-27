package meetingroom.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import meetingroom.CalendarintegrationApplication;
import meetingroom.domain.CalendarCreated;
import meetingroom.domain.CalendarDeleted;
import meetingroom.domain.NotificationSent;

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

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        CalendarCreated calendarCreated = new CalendarCreated(notification);
        calendarCreated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if reservationCreated.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getMeetingRoomId(), Map.class);

        repository().findById(reservationCreated.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            CalendarCreated calendarCreated = new CalendarCreated(notification);
            calendarCreated.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendToUser(ReservationCreated reservationCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        NotificationSent notificationSent = new NotificationSent(notification);
        notificationSent.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if reservationCreated.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getMeetingRoomId(), Map.class);

        repository().findById(reservationCreated.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            NotificationSent notificationSent = new NotificationSent(notification);
            notificationSent.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendToUser(ReservationModified reservationModified) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        NotificationSent notificationSent = new NotificationSent(notification);
        notificationSent.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if reservationModified.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationModified.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationModified.getMeetingRoomId(), Map.class);

        repository().findById(reservationModified.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            NotificationSent notificationSent = new NotificationSent(notification);
            notificationSent.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void sendToUser(ReservationCancelled reservationCancelled) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        NotificationSent notificationSent = new NotificationSent(notification);
        notificationSent.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if reservationCancelled.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCancelled.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCancelled.getMeetingRoomId(), Map.class);

        repository().findById(reservationCancelled.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            NotificationSent notificationSent = new NotificationSent(notification);
            notificationSent.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void deleteCalendar(
        ReservationCancelled reservationCancelled
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Notification notification = new Notification();
        repository().save(notification);

        CalendarDeleted calendarDeleted = new CalendarDeleted(notification);
        calendarDeleted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if reservationCancelled.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCancelled.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCancelled.getMeetingRoomId(), Map.class);

        repository().findById(reservationCancelled.get???()).ifPresent(notification->{
            
            notification // do something
            repository().save(notification);

            CalendarDeleted calendarDeleted = new CalendarDeleted(notification);
            calendarDeleted.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
