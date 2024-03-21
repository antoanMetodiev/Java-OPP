package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;

public class ClimbingImpl implements Climbing {

    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        climbers.forEach(climber -> {
            while (climber.canClimb() && mountain.getPeaksList().iterator().hasNext()) {
                String hill = mountain.getPeaksList().iterator().next();
                mountain.getPeaksList().remove(hill);

                climber.climb();
                climber.getRoster().getPeaks().add(hill);
            }
        });
    }
}
