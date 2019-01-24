package id.gdplabs.hellosts.repository;

import id.gdplabs.hellosts.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
