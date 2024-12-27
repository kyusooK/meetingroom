package meetingroom.domain;

import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

@Data
@ToString
public class ReservationCreated extends AbstractEvent {

    private Long reservationId;
    private Date startDate;
    private Date endDate;
    private String meetingName;
    private String lacation;
    private Object reservationStatus;
    private String userId;
    private Object facilityRequestId;
    private Object meetingRoomId;
}
