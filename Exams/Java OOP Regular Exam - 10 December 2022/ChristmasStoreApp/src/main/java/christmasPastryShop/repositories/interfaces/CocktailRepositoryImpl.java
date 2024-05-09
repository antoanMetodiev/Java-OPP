package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl implements CocktailRepository<Cocktail> {
    private Collection<Cocktail> model;

    public CocktailRepositoryImpl() {
        this.model = new ArrayList<>();
    }

    @Override
    public Cocktail getByName(String name) {
        return this.model.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return Collections.unmodifiableCollection(this.model);
    }

    @Override
    public void add(Cocktail cocktail) {
        this.model.add(cocktail);
    }
}
