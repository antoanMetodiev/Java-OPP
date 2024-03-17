package football.core;

import football.common.ConstantMessages;
import football.common.ExceptionMessages;
import football.entities.field.ArtificialTurf;
import football.entities.field.Field;
import football.entities.field.NaturalGrass;
import football.entities.player.Men;
import football.entities.player.Player;
import football.entities.player.Women;
import football.entities.supplement.Liquid;
import football.entities.supplement.Powdered;
import football.entities.supplement.Supplement;
import football.repositories.SupplementRepository;
import football.repositories.SupplementRepositoryImpl;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller {
    private SupplementRepository supplement;
    private Collection<Field> fields;

    public ControllerImpl() {
        this.supplement = new SupplementRepositoryImpl();
        this.fields = new ArrayList<>();
    }

    @Override
    public String addField(String fieldType, String fieldName) {
        Field field = null;

        if (fieldType.equals("ArtificialTurf")) {
            field = new ArtificialTurf(fieldName);
        } else if (fieldType.equals("NaturalGrass")) {
            field = new NaturalGrass(fieldName);
        } else {
            throw new NullPointerException(ExceptionMessages.INVALID_FIELD_TYPE);
        }

        this.fields.add(field);
        return String.format(String.format(ConstantMessages.SUCCESSFULLY_ADDED_FIELD_TYPE, fieldType));
    }

    @Override
    public String deliverySupplement(String type) {
        Supplement currentSupplement = null;

        if (type.equals("Powdered")) {
            currentSupplement = new Powdered();
        } else if (type.equals("Liquid")) {
            currentSupplement = new Liquid();
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SUPPLEMENT_TYPE);
        }

        this.supplement.add(currentSupplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForField(String fieldName, String supplementType) {
        Field field = this.fields.stream().filter(e -> e.getName().equals(fieldName)).findFirst().orElse(null);
        Supplement receivedSupplement = this.supplement.findByType(supplementType);

        if (receivedSupplement == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NO_SUPPLEMENT_FOUND, supplementType));
        }

        this.supplement.remove(receivedSupplement);
        field.addSupplement(receivedSupplement);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_SUPPLEMENT_IN_FIELD, supplementType, fieldName);
    }

    @Override
    public String addPlayer(String fieldName, String playerType, String playerName, String nationality, int strength) {
        Field field = this.fields.stream().filter(e -> e.getName().equals(fieldName)).findFirst().orElse(null);
        Player player = null;

        if (playerType.equals("Men")) {
            player = new Men(playerName, nationality, strength);
        } else if (playerType.equals("Women")) {
            player = new Women(playerName, nationality, strength);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }

        if (field.getClass().getSimpleName().equals("NaturalGrass") && player.getClass().getSimpleName().equals("Men")) {
            field.addPlayer(player);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        } else if (field.getClass().getSimpleName().equals("ArtificialTurf") && player.getClass().getSimpleName().equals("Women")) {
            field.addPlayer(player);
            return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PLAYER_IN_FIELD, playerType, fieldName);
        } else {
            return String.format(ConstantMessages.FIELD_NOT_SUITABLE);
        }
    }

    @Override
    public String dragPlayer(String fieldName) {
        Field field = this.fields.stream().filter(e -> e.getName().equals(fieldName)).findFirst().orElse(null);
        field.drag();
        return String.format(ConstantMessages.PLAYER_DRAG, field.getPlayers().size());
    }

    @Override
    public String calculateStrength(String fieldName) {
        Field field = this.fields.stream().filter(e -> e.getName().equals(fieldName)).findFirst().orElse(null);
        int totalStrength = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(ConstantMessages.STRENGTH_FIELD, fieldName, totalStrength);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        this.fields.forEach(e -> sb.append(e.getInfo()));
        return sb.toString().trim();
    }
}
