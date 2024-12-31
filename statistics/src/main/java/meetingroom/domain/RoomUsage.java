package meetingroom.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "RoomUsage_table")
@Data
public class RoomUsage {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long roomId;
    private Integer reservedCount;
}
