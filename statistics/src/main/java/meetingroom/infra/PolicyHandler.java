package meetingroom.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import meetingroom.config.kafka.KafkaProcessor;
import meetingroom.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReservationStatisticsRepository reservationStatisticsRepository;

    @Autowired
    FacilityStatisticsRepository facilityStatisticsRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCreated'"
    )
    public void wheneverReservationCreated_AnalyzeReservation(
        @Payload ReservationCreated reservationCreated
    ) {
        ReservationCreated event = reservationCreated;
        System.out.println(
            "\n\n##### listener AnalyzeReservation : " +
            reservationCreated +
            "\n\n"
        );

        // Sample Logic //
        ReservationStatistics.analyzeReservation(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ReservationCancelled'"
    )
    public void wheneverReservationCancelled_AnalyzeReservation(
        @Payload ReservationCancelled reservationCancelled
    ) {
        ReservationCancelled event = reservationCancelled;
        System.out.println(
            "\n\n##### listener AnalyzeReservation : " +
            reservationCancelled +
            "\n\n"
        );

        // Sample Logic //
        ReservationStatistics.analyzeReservation(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='FacilityDecreased'"
    )
    public void wheneverFacilityDecreased_AnalyzeUsingFacility(
        @Payload FacilityDecreased facilityDecreased
    ) {
        FacilityDecreased event = facilityDecreased;
        System.out.println(
            "\n\n##### listener AnalyzeUsingFacility : " +
            facilityDecreased +
            "\n\n"
        );

        // Sample Logic //
        FacilityStatistics.analyzeUsingFacility(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
