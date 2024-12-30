package meetingroom.domain;

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

        FacilityDecreased facilityDecreased = new FacilityDecreased(this);
        facilityDecreased.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

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
        //implement business logic here:

        /** Example 1:  new item 
        FacilityRequest facilityRequest = new FacilityRequest();
        repository().save(facilityRequest);

        FacilityDecreased facilityDecreased = new FacilityDecreased(facilityRequest);
        facilityDecreased.publishAfterCommit();
        */

        
        // if reservationCreated.facilityRequestIdmeetingRoomId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getFacilityRequestId(), Map.class);
        // Map<Long, Object> reservationMap = mapper.convertValue(reservationCreated.getMeetingRoomId(), Map.class);

        // repository().findById(reservationCreated.getFacilityRequestId()).ifPresent(facilityRequest->{
            
        //     facilityRequest.setQuantity(facilityRequest.getQuantity() - 1); // do something
        //     repository().save(facilityRequest);

        //     FacilityDecreased facilityDecreased = new FacilityDecreased(facilityRequest);
        //     facilityDecreased.publishAfterCommit();

        //  });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
