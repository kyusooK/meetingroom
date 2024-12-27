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
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RoomCreated'"
    )
    public void wheneverRoomCreated_RegisterMeetingRoom(
        @Payload RoomCreated roomCreated
    ) {
        RoomCreated event = roomCreated;
        System.out.println(
            "\n\n##### listener RegisterMeetingRoom : " + roomCreated + "\n\n"
        );

        // Sample Logic //
        Reservation.registerMeetingRoom(event);
    }
}
//>>> Clean Arch / Inbound Adaptor