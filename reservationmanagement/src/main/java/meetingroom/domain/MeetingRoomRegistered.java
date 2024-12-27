package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class MeetingRoomRegistered extends AbstractEvent {

    private String reservationId;
    private String lacation;
    private ReservationStatus reservationStatus;
    private MeetingRoomId meetingRoomId;

    public MeetingRoomRegistered(Reservation aggregate) {
        super(aggregate);
    }

    public MeetingRoomRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
