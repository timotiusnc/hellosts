package id.gdplabs.hellosts.web;

import id.gdplabs.hellosts.Ingredient;
import id.gdplabs.hellosts.Ingredient.Type;
import id.gdplabs.hellosts.Taco;
import id.gdplabs.hellosts.repository.IngredientRepository;
import id.gdplabs.hellosts.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller //just an annotation, equivalent with @Component, @RestController, @Repository. The difference is just for documentation purpose
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository designRepo;
    private final DesignProps designProps;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo, DesignProps designProps) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
        this.designProps = designProps;
    }

    @GetMapping
    public String showDesignForm(Model model, @AuthenticationPrincipal User user) {
        log.info(user.toString());
        log.info("Design props page size {}", designProps.getPageSize());

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }
}
