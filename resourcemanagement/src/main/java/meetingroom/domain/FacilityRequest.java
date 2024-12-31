package meetingroom.domain;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import meetingroom.ResourcemanagementApplication;

@Entity
@Table(name = "FacilityRequest_table")
@Data
//<<< DDD / Aggregate Root
public class FacilityRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long facilityRequestId;

    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;

    private Integer quantity;

    @PostPersist
    public void onPostPersist() {
        FacilityCreated facilityCreated = new FacilityCreated(this);
        facilityCreated.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        FacilityModified facilityModified = new FacilityModified(this);
        facilityModified.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        FacilityDeleted facilityDeleted = new FacilityDeleted(this);
        facilityDeleted.publishAfterCommit();
    }

    public static FacilityRequestRepository repository() {
        FacilityRequestRepository facilityRequestRepository = ResourcemanagementApplication.applicationContext.getBean(
            FacilityRequestRepository.class
        );
        return facilityRequestRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseFacility(ReservationCreated reservationCreated) {
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getFacilityRequestId(), Map.class);
        System.out.println(reservationMap);

        repository().findById(((Integer) reservationMap.get("id")).longValue()).ifPresent(facilityRequest->{
            
            facilityRequest.setQuantity(facilityRequest.getQuantity() - 1); // do something
            repository().save(facilityRequest);

            FacilityDecreased facilityDecreased = new FacilityDecreased(facilityRequest);
            facilityDecreased.publishAfterCommit();

         });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
