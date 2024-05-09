package magicGame.models.region;

import magicGame.common.OutputMessages;
import magicGame.models.magicians.Magician;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region {

    @Override
    public String start(Collection<Magician> magicians) {
        List<Magician> wizardsList = magicians.stream().filter(e -> e.getClass().getSimpleName()
                .equals("Wizard")).collect(Collectors.toList());

        List<Magician> blackWidowsList = magicians.stream().filter(e -> e.getClass().getSimpleName()
                .equals("BlackWidow")).collect(Collectors.toList());

        while (!wizardsList.isEmpty() && !blackWidowsList.isEmpty()) {

            Magician blackWidow = blackWidowsList.get(0);
            Magician wizard = wizardsList.get(0);
            int damage = 0;

            if (wizard.getMagic().fire() > 0 && wizard.isAlive()) {
                damage = wizard.getMagic().fire();
                blackWidow.takeDamage(damage);
            }
            if (!blackWidow.isAlive()) {
                wizardsList.remove(wizard);
                continue;
            }

            if (blackWidow.getMagic().fire() > 0 && blackWidow.isAlive()) {
                damage = blackWidow.getMagic().fire();
                wizard.takeDamage(damage);
            }
            if (!wizard.isAlive()) {
                wizardsList.remove(wizard);
            }

        }

        String message = "";
        if (wizardsList.isEmpty()) {
            message = "Black widows win!";
        } else {
            message = "Wizards win!";
        }

        return message;
    }
}
