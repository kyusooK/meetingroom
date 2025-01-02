package meetingroom.domain;

import java.util.Date;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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

    @Embedded
    private UserId userId;

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

    @PostUpdate
    public void onPostUpdate() {
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
    public void createReservation(CreateReservationCommand createReservationCommand) {
        Reservation reservation = repository().findById(getReservationId())
            .orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다."));
    
        try {
            RestTemplate restTemplate = new RestTemplate();
            String userServiceUrl = "http://localhost:8084/users/" + createReservationCommand.getUserId();
            ResponseEntity<Map> response = restTemplate.getForEntity(userServiceUrl, Map.class);
            
            boolean isDirector = "부장".equals(response.getBody().get("rank"));
            boolean isMainConferenceRoom = "대회의실".equals(reservation.getRoomName());
            boolean isDevelopmentTeam = "개발팀".equals(response.getBody().get("department"));
            boolean isSmallConferenceRoom = "소회의실".equals(reservation.getRoomName());
            
            if (!isDirector && isMainConferenceRoom) {
                throw new IllegalArgumentException("대회의실은 부장 직급만 예약이 가능합니다.");
            }
            
            if (isDevelopmentTeam && !isSmallConferenceRoom) {
                throw new IllegalArgumentException("개발팀은 소회의실만 예약 가능합니다.");
            }
    
            // 권한 확인 후 예약 진행
            reservation.setStartDate(createReservationCommand.getStartDate());
            reservation.setEndDate(createReservationCommand.getEndDate());
            reservation.setMeetingName(createReservationCommand.getMeetingName());
            reservation.setReservationStatus(createReservationCommand.getReservationStatus());
            reservation.setFacilityRequestId(createReservationCommand.getFacilityRequestId());
            
            repository().save(reservation);
    
            ReservationCreated reservationCreated = new ReservationCreated(this);
            reservationCreated.publishAfterCommit();
            
        } catch (Exception e) {
            throw new IllegalArgumentException("사용자 권한 확인 중 오류가 발생했습니다: " + e.getMessage());
        }
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
        reservation.setLocation(roomCreated.getLocation());
        reservation.setReservationStatus(ReservationStatus.AVAILABLED);
        reservation.setRoomName(roomCreated.getRoomName());
        repository().save(reservation);

        MeetingRoomRegistered meetingRoomRegistered = new MeetingRoomRegistered(reservation);
        meetingRoomRegistered.publishAfterCommit();

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
