package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class CalendarCreated extends AbstractEvent {

    private Long notificationId;
    private String userId;
    private Message message;

    public CalendarCreated(Notification aggregate) {
        super(aggregate);
    }

    public CalendarCreated() {
        super();
    }
}
//>>> DDD / Domain Event
