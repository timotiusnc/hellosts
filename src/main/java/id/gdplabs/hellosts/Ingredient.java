package id.gdplabs.hellosts;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data // Lombok; generate getters, toString, hashCode. Setters for non-final attr
@RequiredArgsConstructor // Generate ctor with all final and constrained fields (e.g. @NonNull)
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true) // Generate no arg ctor, set to private, set all final attr to null
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
