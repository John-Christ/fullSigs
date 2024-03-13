package sigs.api.web;


import java.util.List;

import sigs.api.model.Entrepot;
import sigs.api.repository.EntrepotRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import sigs.api.exception.RestApiNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/")
@RestController
public class EntrepotController
{


    private final EntrepotRepository repository;

    EntrepotController(EntrepotRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/entrepots")
    List<Entrepot> all() {
        return (List<Entrepot>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/entrepot-add")
    Entrepot newEntrepot(@RequestBody Entrepot newEntrepot) {
        return repository.save(newEntrepot);
    }

    // Single item

    @GetMapping("/entrepot/{id}")
    Entrepot one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/entrepot-update/{id}")
    Entrepot replaceEntrepot(@RequestBody Entrepot newEntrepot, @PathVariable Long id) {

        return repository.findById(id)
                .map(entrepot -> {
                    entrepot.setNom(newEntrepot.getNom());
                    entrepot.setDescription(newEntrepot.getDescription());
                    entrepot.setAdresse(newEntrepot.getAdresse());

                    return repository.save(entrepot);
                })
                .orElseGet(() -> {
                    newEntrepot.setId(id);
                    return repository.save(newEntrepot);
                });
    }

    @DeleteMapping("/entrepot-delete/{id}")
    void deleteEntrepot(@PathVariable Long id) {
        repository.deleteById(id);
    }






}


