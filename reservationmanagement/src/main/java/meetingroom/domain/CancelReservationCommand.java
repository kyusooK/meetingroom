package meetingroom.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CancelReservationCommand {

    private String reservationId;
    private ReservationStatus reservationStatus;
}
