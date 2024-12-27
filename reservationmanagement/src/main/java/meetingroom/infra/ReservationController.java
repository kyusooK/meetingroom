package meetingroom.infra;
import meetingroom.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/reservations")
@Transactional
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;

    @RequestMapping(value = "/reservations/{id}/createreservation",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Reservation createReservation(@PathVariable(value = "id") String id, @RequestBody CreateReservationCommand createReservationCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /reservation/createReservation  called #####");
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            
            optionalReservation.orElseThrow(()-> new Exception("No Entity Found"));
            Reservation reservation = optionalReservation.get();
            reservation.createReservation(createReservationCommand);
            
            reservationRepository.save(reservation);
            return reservation;
            
    }
    @RequestMapping(value = "/reservationsmodifyreservation",
            method = RequestMethod.PATCH,
            produces = "application/json;charset=UTF-8")
    public Reservation modifyReservation(HttpServletRequest request, HttpServletResponse response, 
        ) throws Exception {
            System.out.println("##### /reservation/modifyReservation  called #####");
            Reservation reservation = new Reservation();
            reservation.modifyReservation();
            reservationRepository.save(reservation);
            return reservation;
    }
    @RequestMapping(value = "/reservations/{id}/cancelreservation",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public Reservation cancelReservation(@PathVariable(value = "id") String id, @RequestBody CancelReservationCommand cancelReservationCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /reservation/cancelReservation  called #####");
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            
            optionalReservation.orElseThrow(()-> new Exception("No Entity Found"));
            Reservation reservation = optionalReservation.get();
            reservation.cancelReservation(cancelReservationCommand);
            
            reservationRepository.save(reservation);
            return reservation;
            
    }
}
//>>> Clean Arch / Inbound Adaptor
