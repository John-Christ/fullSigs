package sigs.api.web;

import java.util.List;

import sigs.api.model.Categorie;
import sigs.api.repository.CategorieRepository;
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
public class CategorieController
{


    private final CategorieRepository repository;

    CategorieController(CategorieRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/categories")
    List<Categorie> all() {
        return (List<Categorie>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/categorie-add")
    Categorie newCategorie(@RequestBody Categorie newCategorie) {
        return repository.save(newCategorie);
    }

    // Single item

    @GetMapping("/categorie/{id}")
    Categorie one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/categorie-update/{id}")
    Categorie replaceCategorie(@RequestBody Categorie newCategorie, @PathVariable Long id) {

        return repository.findById(id)
                .map(categorie -> {
                    categorie.setNom(newCategorie.getNom());
                    categorie.setDescription(newCategorie.getDescription());

                    return repository.save(categorie);
                })
                .orElseGet(() -> {
                    newCategorie.setId(id);
                    return repository.save(newCategorie);
                });
    }

    @DeleteMapping("/categorie-delete/{id}")
    void deleteCategorie(@PathVariable Long id) {
        repository.deleteById(id);
    }



}


