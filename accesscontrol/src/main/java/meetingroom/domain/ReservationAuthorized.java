package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationAuthorized extends AbstractEvent {

    private String userId;
    private String name;
    private String rank;

    public ReservationAuthorized(User aggregate) {
        super(aggregate);
    }

    public ReservationAuthorized() {
        super();
    }
}
//>>> DDD / Domain Event
