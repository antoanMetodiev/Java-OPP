package goldDigger.repositories;

import goldDigger.models.spot.Spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SpotRepository implements Repository<Spot> {
    private Collection<Spot> spots;

    public SpotRepository() {
        this.spots = new ArrayList<>();
    }

    @Override
    public Collection<Spot> getCollection() {
        return Collections.unmodifiableCollection(this.spots);
    }

    @Override
    public void add(Spot entity) {
        this.spots.add(entity);
    }

    @Override
    public boolean remove(Spot entity) {
        return this.spots.removeIf(e -> e.getName().equals(entity.getName()));
    }

    @Override
    public Spot byName(String name) {
        return this.spots.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
