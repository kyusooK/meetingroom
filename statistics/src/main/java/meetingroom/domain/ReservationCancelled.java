package meetingroom.domain;

import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

@Data
@ToString
public class ReservationCancelled extends AbstractEvent {

    private String reservationId;
    private Object reservationStatus;
}
