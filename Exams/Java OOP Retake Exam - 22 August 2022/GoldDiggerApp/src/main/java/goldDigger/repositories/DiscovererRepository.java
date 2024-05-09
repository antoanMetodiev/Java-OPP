package goldDigger.repositories;

import goldDigger.models.disocverer.Discoverer;

import java.util.*;

public class DiscovererRepository implements Repository<Discoverer> {
    private Collection<Discoverer> discoverers;

    public DiscovererRepository() {
        this.discoverers = new ArrayList<>();
    }

    @Override
    public Collection<Discoverer> getCollection() {
        return Collections.unmodifiableCollection(this.discoverers);
    }

    @Override
    public void add(Discoverer entity) {
        this.discoverers.add(entity);
    }

    @Override
    public boolean remove(Discoverer entity) {
        return this.discoverers.removeIf(e -> e.getName().equals(entity.getName()));
    }

    @Override
    public Discoverer byName(String name) {
        return this.discoverers.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
