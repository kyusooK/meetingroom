package meetingroom.domain;

import java.util.Date;
import java.util.List;
import meetingroom.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "reservations",
    path = "reservations"
)
public interface ReservationRepository
    extends PagingAndSortingRepository<Reservation, Long> {
    @Query(
        value = "select reservation " +
        "from Reservation reservation " +
        "where(:roomId is null or reservation.roomId = :roomId) and (:roomName is null or reservation.roomName like %:roomName%)"
    )
    Reservation getMeetingRoom(Long roomId, String roomName);
}
