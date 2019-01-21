package id.gdplabs.hellosts.repository;

import id.gdplabs.hellosts.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
