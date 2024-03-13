package sigs.api.web;


import java.util.List;

import sigs.api.model.SousCategorie;
import sigs.api.repository.SousCategorieRepository;
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
public class SousCategorieController
{


    private final SousCategorieRepository repository;

    SousCategorieController(SousCategorieRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/sous-categories")
    List<SousCategorie> all() {
        return (List<SousCategorie>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/sous-categorie-add")
    SousCategorie newSousCategorie(@RequestBody SousCategorie newSousCategorie) {
        return repository.save(newSousCategorie);
    }

    // Single item

    @GetMapping("/sous-categorie/{id}")
    SousCategorie one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/sous-categorie-update/{id}")
    SousCategorie replaceSousCategorie(@RequestBody SousCategorie newSousCategorie, @PathVariable Long id) {

        return repository.findById(id)
                .map(sousCategorie -> {
                    sousCategorie.setNom(newSousCategorie.getNom());
                    sousCategorie.setDescription(newSousCategorie.getDescription());

                    return repository.save(sousCategorie);
                })
                .orElseGet(() -> {
                    newSousCategorie.setId(id);
                    return repository.save(newSousCategorie);
                });
    }

    @DeleteMapping("/sous-categorie-delete/{id}")
    void deleteSousCategorie(@PathVariable Long id) {
        repository.deleteById(id);
    }



}



