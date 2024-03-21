package climbers.core;

import climbers.common.ConstantMessages;
import climbers.common.ExceptionMessages;
import climbers.models.climber.Climber;
import climbers.models.climber.RockClimber;
import climbers.models.climber.WallClimber;
import climbers.models.climbing.ClimbingImpl;
import climbers.models.mountain.Mountain;
import climbers.models.mountain.MountainImpl;
import climbers.repositories.ClimberRepository;
import climbers.repositories.MountainRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private ClimberRepository climberRepository;
    private MountainRepository mountainRepository;
    private int conqueredMountains = 0;

    public ControllerImpl() {
        this.climberRepository = new ClimberRepository();
        this.mountainRepository = new MountainRepository();
    }

    @Override
    public String addClimber(String type, String climberName) {
        Climber climber = null;
        if (type.equals("RockClimber")) {
            climber = new RockClimber(climberName);
        } else if (type.equals("WallClimber")) {
            climber = new WallClimber(climberName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.CLIMBER_INVALID_TYPE);
        }

        this.climberRepository.add(climber);
        return String.format(ConstantMessages.CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {
        Mountain mountain = new MountainImpl(mountainName);
        for (String peak : peaks) {
            mountain.getPeaksList().add(peak);
        }
        this.mountainRepository.add(mountain);
        return String.format(ConstantMessages.MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {
        Climber climber = this.climberRepository.byName(climberName);
        if (climber == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CLIMBER_DOES_NOT_EXIST, climberName));
        }
        this.climberRepository.remove(climber);
        return String.format(ConstantMessages.CLIMBER_REMOVE, climberName);
    }

    @Override
    public String startClimbing(String mountainName) {
        Mountain mountain = this.mountainRepository.byName(mountainName);
        if (this.climberRepository.getCollection().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_CLIMBERS);
        }
        int peakCount = mountain.getPeaksList().size();
        List<Climber> climbersCanClimb = this.climberRepository.getCollection().stream()
                .filter(Climber::canClimb).collect(Collectors.toList());

        new ClimbingImpl().conqueringPeaks(mountain, climbersCanClimb);
        int removedClimbers = this.climberRepository.getCollection().stream()
                .filter(e -> e.getRoster().getPeaks().size() < peakCount).collect(Collectors.toList()).size();

        this.conqueredMountains++;
        return String.format(ConstantMessages.PEAK_CLIMBING, mountainName, removedClimbers);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder(String.format(ConstantMessages.FINAL_MOUNTAIN_COUNT, conqueredMountains))
                .append(System.lineSeparator());

        sb.append(ConstantMessages.FINAL_CLIMBERS_STATISTICS);
        for (Climber climber : this.climberRepository.getCollection()) {
            sb.append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_CLIMBER_NAME, climber.getName())).append(System.lineSeparator());
            sb.append(String.format(ConstantMessages.FINAL_CLIMBER_STRENGTH, climber.getStrength())).append(System.lineSeparator());

            if (climber.getRoster().getPeaks().isEmpty()) {
                sb.append("None");
            } else {
                sb.append(String.format(ConstantMessages.FINAL_CLIMBER_PEAKS, String.join(", ", climber.getRoster().getPeaks())));
            }
        }
        return sb.toString().trim();
    }
}
