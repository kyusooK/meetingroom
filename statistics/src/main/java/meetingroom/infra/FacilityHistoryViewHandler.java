package meetingroom.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import meetingroom.config.kafka.KafkaProcessor;
import meetingroom.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class FacilityHistoryViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private FacilityHistoryRepository facilityHistoryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenUsingFacilityAnalyzed_then_CREATE_1(
        @Payload UsingFacilityAnalyzed usingFacilityAnalyzed
    ) {
        try {
            if (!usingFacilityAnalyzed.validate()) return;

            // view 객체 생성
            FacilityHistory facilityHistory = new FacilityHistory();
            // view 객체에 이벤트의 Value 를 set 함
            facilityHistory.setFacilityName(
                usingFacilityAnalyzed.getFacilityName()
            );
            facilityHistory.setFacilityCount(
                String.valueOf(usingFacilityAnalyzed.getFacilityCount())
            );
            // view 레파지 토리에 save
            facilityHistoryRepository.save(facilityHistory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
