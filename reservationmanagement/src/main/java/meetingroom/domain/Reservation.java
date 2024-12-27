package meetingroom.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import meetingroom.ReservationmanagementApplication;
import meetingroom.domain.MeetingRoomRegistered;

@Entity
@Table(name = "Reservation_table")
@Data
//<<< DDD / Aggregate Root
public class Reservation {

    @Id
    private String reservationId;

    private Date startDate;

    private Date endDate;

    private String meetingName;

    private String lacation;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private String userId;

    @Embedded
    private FacilityRequestId facilityRequestId;

    @Embedded
    private MeetingRoomId meetingRoomId;

    @PostPersist
    public void onPostPersist() {
        MeetingRoomRegistered meetingRoomRegistered = new MeetingRoomRegistered(
            this
        );
        meetingRoomRegistered.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationmanagementApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    //<<< Clean Arch / Port Method
    public void createReservation(
        CreateReservationCommand createReservationCommand
    ) {
        //implement business logic here:

        ReservationCreated reservationCreated = new ReservationCreated(this);
        reservationCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void modifyReservation() {
        //implement business logic here:

        ReservationModified reservationModified = new ReservationModified(this);
        reservationModified.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void cancelReservation(
        CancelReservationCommand cancelReservationCommand
    ) {
        //implement business logic here:

        ReservationCancelled reservationCancelled = new ReservationCancelled(
            this
        );
        reservationCancelled.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void registerMeetingRoom(RoomCreated roomCreated) {
        //implement business logic here:

        /** Example 1:  new item 
        Reservation reservation = new Reservation();
        repository().save(reservation);

        MeetingRoomRegistered meetingRoomRegistered = new MeetingRoomRegistered(reservation);
        meetingRoomRegistered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(roomCreated.get???()).ifPresent(reservation->{
            
            reservation // do something
            repository().save(reservation);

            MeetingRoomRegistered meetingRoomRegistered = new MeetingRoomRegistered(reservation);
            meetingRoomRegistered.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
