package meetingroom.infra;

import meetingroom.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReservationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Reservation>> {

    @Override
    public EntityModel<Reservation> process(EntityModel<Reservation> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/createreservation"
                )
                .withRel("createreservation")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/modifyreservation"
                )
                .withRel("modifyreservation")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/cancelreservation"
                )
                .withRel("cancelreservation")
        );

        return model;
    }
}
