package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import meetingroom.domain.*;
import meetingroom.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ReservationCreated extends AbstractEvent {

    private Long reservationId;
    private Date startDate;
    private Date endDate;
    private String meetingName;
    private String lacation;
    private ReservationStatus reservationStatus;
    private String userId;
    private FacilityRequestId facilityRequestId;
    private Long roomId;
    private String roomName;

    public ReservationCreated(Reservation aggregate) {
        super(aggregate);
    }

    public ReservationCreated() {
        super();
    }
}
//>>> DDD / Domain Event
