package harpoonDiver.core;

import harpoonDiver.common.ConstantMessages;
import harpoonDiver.common.ExceptionMessages;
import harpoonDiver.models.diver.DeepWaterDiver;
import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.diver.OpenWaterDiver;
import harpoonDiver.models.diver.WreckDiver;
import harpoonDiver.models.diving.DivingImpl;
import harpoonDiver.models.divingSite.DivingSite;
import harpoonDiver.models.divingSite.DivingSiteImpl;
import harpoonDiver.repositories.DiverRepository;
import harpoonDiver.repositories.DivingSiteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private DiverRepository diverRepository;
    private DivingSiteRepository divingSiteRepository;
    private int divedPlaces = 0;

    public ControllerImpl() {
        this.diverRepository = new DiverRepository();
        this.divingSiteRepository = new DivingSiteRepository();
    }

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver = null;
        if (kind.equals("DeepWaterDiver")) {
            diver = new DeepWaterDiver(diverName);
        } else if (kind.equals("OpenWaterDiver")) {
            diver = new OpenWaterDiver(diverName);
        } else if (kind.equals("WreckDiver")) {
            diver = new WreckDiver(diverName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.DIVER_INVALID_KIND);
        }

        this.diverRepository.getCollection().add(diver);
        return String.format(ConstantMessages.DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        Arrays.stream(seaCreatures).forEach(e -> {
            divingSite.getSeaCreatures().add(e);
        });

        this.divingSiteRepository.getCollection().add(divingSite);
        return String.format(ConstantMessages.DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diver = this.diverRepository.getCollection().stream()
                .filter(e -> e.getName().equals(diverName)).findFirst().orElse(null);

        if (diver == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DIVER_DOES_NOT_EXIST, diverName));
        }

        this.diverRepository.remove(diver);
        return String.format(ConstantMessages.DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        DivingSite divingPlace = this.divingSiteRepository.byName(siteName);
        DivingImpl diving = new DivingImpl();

        List<Diver> worthyDivers = this.diverRepository.getCollection().stream()
                .filter(e -> e.getOxygen() > 30).collect(Collectors.toList());

        if (worthyDivers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SITE_DIVERS_DOES_NOT_EXISTS);
        }
        diving.searching(divingPlace, worthyDivers);
        divedPlaces++;

        int removedDiversCount = getAllDiversCountWithZeroOxygen(worthyDivers);
        return String.format(ConstantMessages.SITE_DIVING, siteName, removedDiversCount);
    }

    private int getAllDiversCountWithZeroOxygen(List<Diver> worthyDivers) {
        return (int) worthyDivers.stream().filter(e -> !e.canDive()).count();
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder(String.format(ConstantMessages.FINAL_DIVING_SITES
                , divedPlaces)).append(System.lineSeparator());
        result.append(ConstantMessages.FINAL_DIVERS_STATISTICS).append(System.lineSeparator());
        
        for (Diver diver : this.diverRepository.getCollection()) {
            result.append(String.format(ConstantMessages.FINAL_DIVER_NAME, diver.getName())).append(System.lineSeparator());
            result.append(String.format(ConstantMessages.FINAL_DIVER_OXYGEN, diver.getOxygen())).append(System.lineSeparator());
            
            String message = "";
            if (diver.getSeaCatch().getSeaCreatures().isEmpty()) {
                message = "None";
            } else {
                message = String.join(ConstantMessages.FINAL_DIVER_CATCH_DELIMITER, diver.getSeaCatch().getSeaCreatures());
            }
            result.append(String.format(ConstantMessages.FINAL_DIVER_CATCH, message)).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
