package meetingroom.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import meetingroom.AccesscontrolApplication;
import meetingroom.domain.ReservationAuthorized;

@Entity
@Table(name = "User_table")
@Data
//<<< DDD / Aggregate Root
public class User {

    @Id
    private String userId;

    private String name;

    private String rank;

    private String department;

    @PostPersist
    public void onPostPersist() {
        ReservationAuthorized reservationAuthorized = new ReservationAuthorized(
            this
        );
        reservationAuthorized.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static UserRepository repository() {
        UserRepository userRepository = AccesscontrolApplication.applicationContext.getBean(
            UserRepository.class
        );
        return userRepository;
    }
}
//>>> DDD / Aggregate Root
