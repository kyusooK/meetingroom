package meetingroom.domain;

import java.util.Date;
import lombok.Data;

@Data
public class GetMeetingRoomQuery {

    private Long roomId;
    private String roomName;
}
