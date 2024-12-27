package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class NotificationSent extends AbstractEvent {

    private Long notificationId;
    private String userId;
    private Message message;

    public NotificationSent(Notification aggregate) {
        super(aggregate);
    }

    public NotificationSent() {
        super();
    }
}
//>>> DDD / Domain Event
