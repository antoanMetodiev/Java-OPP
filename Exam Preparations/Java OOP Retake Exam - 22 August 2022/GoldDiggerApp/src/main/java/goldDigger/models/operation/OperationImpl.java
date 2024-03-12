package goldDigger.models.operation;

import goldDigger.models.disocverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation {
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        boolean spotIsEmpty = false;
        for (Discoverer discoverer : discoverers) {

            while (discoverer.canDig()) {
                if (!spot.getExhibits().isEmpty()) {

                    discoverer.dig();
                    for (String exhibit : spot.getExhibits()) {
                        discoverer.getMuseum().getExhibits().add(exhibit);
                        spot.getExhibits().remove(exhibit);
                        break;
                    }
                } else {
                    spotIsEmpty = true;
                    break;
                }
            }

            if (spotIsEmpty) {
                break;
            }
        }
    }
}
