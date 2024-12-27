package meetingroom.domain;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;
import meetingroom.ReservationmanagementApplication;

@Entity
@Table(name = "Reservation_table")
@Data
//<<< DDD / Aggregate Root
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    private Date startDate;

    private Date endDate;

    private String meetingName;

    private String location;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private String userId;

    @Embedded
    private FacilityRequestId facilityRequestId;

    private Long roomId;

    private String roomName;

    @PostPersist
    public void onPostPersist() {
        MeetingRoomRegistered meetingRoomRegistered = new MeetingRoomRegistered(
            this
        );
        meetingRoomRegistered.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    @PreUpdate
    public void onPreUpdate() {
        ReservationModified reservationModified = new ReservationModified(this);
        reservationModified.publishAfterCommit();
    }

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
        Reservation reservation = new Reservation();
        reservation.setRoomId(roomCreated.getId());
        reservation.setMeetingName(roomCreated.getRoomName());
        reservation.setLocation(roomCreated.getLocation());
        reservation.setReservationStatus(ReservationStatus.RESERVED);
        repository().save(reservation);

        MeetingRoomRegistered meetingRoomRegistered = new MeetingRoomRegistered(reservation);
        meetingRoomRegistered.publishAfterCommit();

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
