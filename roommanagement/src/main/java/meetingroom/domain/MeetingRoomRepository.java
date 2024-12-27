package meetingroom.domain;

import meetingroom.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "meetingRooms",
    path = "meetingRooms"
)
public interface MeetingRoomRepository
    extends PagingAndSortingRepository<MeetingRoom, Long> {}
