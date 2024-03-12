package handball.core;

import handball.common.ConstantMessages;
import handball.common.ExceptionMessages;
import handball.entities.equipment.ElbowPad;
import handball.entities.equipment.Equipment;
import handball.entities.equipment.Kneepad;
import handball.entities.gameplay.Gameplay;
import handball.entities.gameplay.Indoor;
import handball.entities.gameplay.Outdoor;
import handball.entities.team.Bulgaria;
import handball.entities.team.Germany;
import handball.entities.team.Team;
import handball.repositories.EquipmentRepository;
import handball.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository equipment;
    private Collection<Gameplay> gameplays;

    public ControllerImpl() {
        this.equipment = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        if (gameplayType.equals("Outdoor")) {
            this.gameplays.add(new Outdoor(gameplayName));
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
        } else if (gameplayType.equals("Indoor")) {
            this.gameplays.add(new Indoor(gameplayName));
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_GAMEPLAY_TYPE);
        }
    }

    @Override
    public String addEquipment(String equipmentType) {
        if (equipmentType.equals("Kneepad")) {
            this.equipment.add(new Kneepad());
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
        } else if (equipmentType.equals("ElbowPad")) {
            this.equipment.add(new ElbowPad());
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_EQUIPMENT_TYPE);
        }
    }

    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Gameplay gamePlay = this.gameplays.stream().filter(e -> e.getName().equals(gameplayName))
                .findFirst().orElse(null);

        Equipment equipment1 = this.equipment.findByType(equipmentType);
        
        if (gamePlay != null && equipment1 != null) {
            gamePlay.addEquipment(equipment1);
            this.equipment.remove(equipment1);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
        } else {
            throw new NullPointerException(String.format(ExceptionMessages.NO_EQUIPMENT_FOUND, equipmentType));
        }
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        if (!teamType.equals("Bulgaria") && !teamType.equals("Germany")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TEAM_TYPE);
        }

        Team team;
        if ("Bulgaria".equals(teamType)) {
            team = new Bulgaria(teamName, country, advantage);
        } else {
            team = new Germany(teamName, country, advantage);
        }

        Gameplay gameplay = this.gameplays.stream().filter(e -> e.getName().equals(gameplayName))
                .findFirst().orElse(null);

        if (gameplay.getClass().getSimpleName().equals("Outdoor") && team.getClass().getSimpleName().equals("Bulgaria")) {
            gameplay.addTeam(team);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
        } else if (gameplay.getClass().getSimpleName().equals("Indoor") && team.getClass().getSimpleName().equals("Germany")) {
            
            gameplay.addTeam(team);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
        } else {
            return ConstantMessages.GAMEPLAY_NOT_SUITABLE;
        }
    }

    @Override
    public String playInGameplay(String gameplayName) {
        int teamCount = 0;
        for (Gameplay gameplay : gameplays) {
            if (gameplay.getName().equals(gameplayName)) {
                Collection<Team> team = gameplay.getTeam();
                team.forEach(Team::play);
                teamCount = team.size();
                break;
            }
        }
        return String.format(ConstantMessages.TEAMS_PLAYED, teamCount);
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        List<Gameplay> gameplay = this.gameplays.stream().filter(e -> e.getName()
                .equals(gameplayName)).collect(Collectors.toList());

        int adv = 0;
        for (Gameplay gameplay1 : gameplay) {
            for (Team team : gameplay1.getTeam()) {
                adv += (team.getAdvantage());
            }
        }
        return String.format(ConstantMessages.ADVANTAGE_GAMEPLAY, gameplayName, Integer.parseInt(String.valueOf(adv)));
    }

    @Override
    public String getStatistics() {
        StringBuilder result = new StringBuilder();
        this.gameplays.forEach(result::append);
        return result.toString().trim();
    }
}
