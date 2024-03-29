package harpoonDiver.models.diving;

import harpoonDiver.models.diver.Diver;
import harpoonDiver.models.divingSite.DivingSite;

import java.util.Collection;

public class DivingImpl implements Diving {

    @Override
    public void searching(DivingSite divingSite, Collection<Diver> divers) {

        Collection<String> fishes = divingSite.getSeaCreatures();

        for (Diver diver : divers) {

            while (diver.canDive() && fishes.iterator().hasNext()) {

                // Тук пазим създанието, а на следващия ред го изтриваме.
                String currentFish = fishes.iterator().next();
                fishes.remove(currentFish);

                // Стреляме и намаляме oxygen-a с метода shoot(),
                // а след това добавяме създанието в наш collection.
                diver.shoot();
                diver.getSeaCatch().getSeaCreatures().add(currentFish);
            }

            if (fishes.isEmpty()) {
                return;
            }
        }
    }
}
