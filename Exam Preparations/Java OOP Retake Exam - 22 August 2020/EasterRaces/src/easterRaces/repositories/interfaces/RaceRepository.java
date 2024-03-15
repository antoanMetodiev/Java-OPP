package easterRaces.repositories.interfaces;

import easterRaces.entities.racers.Race;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RaceRepository implements Repository<Race> {
    private Collection<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        return this.models.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.models);
    }

    @Override
    public void add(Race model) {
        if (model != null) {
            this.models.add(model);
        }
    }

    @Override
    public boolean remove(Race model) {
        return this.models.remove(model);
    }
}
