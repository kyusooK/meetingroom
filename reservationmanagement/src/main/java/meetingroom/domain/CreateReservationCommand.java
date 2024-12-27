package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateReservationCommand {

    private Date startDate;
    private Date endDate;
    private String meetingName;
    private ReservationStatus reservationStatus;
    private String userId;
    private FacilityRequestId facilityRequestId;
}
