package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelReservationCommand {

    private Long reservationId;
    private ReservationStatus reservationStatus;
}
