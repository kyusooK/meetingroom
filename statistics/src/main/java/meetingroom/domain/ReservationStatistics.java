package meetingroom.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import meetingroom.StatisticsApplication;
import meetingroom.domain.MeetingRoomCancelAnalyzed;
import meetingroom.domain.MeetingRoomReservationAnalyzed;

@Entity
@Table(name = "ReservationStatistics_table")
@Data
//<<< DDD / Aggregate Root
public class ReservationStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long statisticsId;

    private Integer reservedCount;

    private Long roomId;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @PostPersist
    public void onPostPersist() {
    }

    public static ReservationStatisticsRepository repository() {
        ReservationStatisticsRepository reservationStatisticsRepository = StatisticsApplication.applicationContext.getBean(
            ReservationStatisticsRepository.class
        );
        return reservationStatisticsRepository;
    }

    //<<< Clean Arch / Port Method
    public static void analyzeReservation(
        ReservationCreated reservationCreated
    ) {
        //implement business logic here:

        ReservationStatistics reservationStatistics = new ReservationStatistics();
        reservationStatistics.setRoomId(reservationCreated.getRoomId());
        reservationStatistics.setReservedCount(reservationStatistics.getReservedCount() + 1);
        reservationStatistics.setReservationStatus(ReservationStatus.RESERVED);
        repository().save(reservationStatistics);

        MeetingRoomReservationAnalyzed meetingRoomReservationAnalyzed = new MeetingRoomReservationAnalyzed(reservationStatistics);
        meetingRoomReservationAnalyzed.publishAfterCommit();

        /** Example 2:  finding and process
        
        // if reservationCreated.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getMeetingRoomId(), Map.class);

        repository().findById(reservationCreated.get???()).ifPresent(reservationStatistics->{
            
            reservationStatistics // do something
            repository().save(reservationStatistics);

            MeetingRoomReservationAnalyzed meetingRoomReservationAnalyzed = new MeetingRoomReservationAnalyzed(reservationStatistics);
            meetingRoomReservationAnalyzed.publishAfterCommit();
            MeetingRoomCancelAnalyzed meetingRoomCancelAnalyzed = new MeetingRoomCancelAnalyzed(reservationStatistics);
            meetingRoomCancelAnalyzed.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void analyzeReservation(
        ReservationCancelled reservationCancelled
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        ReservationStatistics reservationStatistics = new ReservationStatistics();
        repository().save(reservationStatistics);

        MeetingRoomReservationAnalyzed meetingRoomReservationAnalyzed = new MeetingRoomReservationAnalyzed(reservationStatistics);
        meetingRoomReservationAnalyzed.publishAfterCommit();
        MeetingRoomCancelAnalyzed meetingRoomCancelAnalyzed = new MeetingRoomCancelAnalyzed(reservationStatistics);
        meetingRoomCancelAnalyzed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if reservationCancelled.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCancelled.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCancelled.getMeetingRoomId(), Map.class);

        repository().findById(reservationCancelled.get???()).ifPresent(reservationStatistics->{
            
            reservationStatistics // do something
            repository().save(reservationStatistics);

            MeetingRoomReservationAnalyzed meetingRoomReservationAnalyzed = new MeetingRoomReservationAnalyzed(reservationStatistics);
            meetingRoomReservationAnalyzed.publishAfterCommit();
            MeetingRoomCancelAnalyzed meetingRoomCancelAnalyzed = new MeetingRoomCancelAnalyzed(reservationStatistics);
            meetingRoomCancelAnalyzed.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
