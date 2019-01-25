package id.gdplabs.hellosts.web;

import id.gdplabs.hellosts.Taco;
import id.gdplabs.hellosts.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
//@Controller //just an annotation, equivalent with @Component, @RestController, @Repository. The difference is just for documentation purpose
@RestController
@RequestMapping(path="/design", produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {

    private final TacoRepository tacoRepo;
    private final DesignProps designProps;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepo, DesignProps designProps) {
        this.tacoRepo = tacoRepo;
        this.designProps = designProps;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
