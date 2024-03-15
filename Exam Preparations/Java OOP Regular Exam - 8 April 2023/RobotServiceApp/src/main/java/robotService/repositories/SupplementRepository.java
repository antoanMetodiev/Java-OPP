package robotService.repositories;

import robotService.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class SupplementRepository implements Repository {
    private Collection<Supplement> supplements;

    public SupplementRepository() {
        this.supplements = new ArrayList<>();
    }

    @Override
    public void addSupplement(Supplement supplement) {
        this.supplements.add(supplement);
    }

    @Override
    public boolean removeSupplement(Supplement supplement) {
        // TODO: moje i da gresha!!!!!!!!!!!
        return this.supplements.remove(supplement);
    }

    @Override
    public Supplement findFirst(String type) {
        return this.supplements.stream().filter(e -> e.getClass()
                .getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
