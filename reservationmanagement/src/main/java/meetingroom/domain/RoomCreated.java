package meetingroom.domain;

import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

@Data
@ToString
public class RoomCreated extends AbstractEvent {

    private Long id;
    private String roomName;
    private String location;
    private Object reservationStatus;
}
