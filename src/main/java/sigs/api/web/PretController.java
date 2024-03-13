package sigs.api.web;


import java.util.List;

import sigs.api.model.Pret;
import sigs.api.repository.PretRepository;
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
public class PretController
{


    private final PretRepository repository;

    PretController(PretRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/prets")
    List<Pret> all() {
        return (List<Pret>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/pret-add")
    Pret newPret(@RequestBody Pret newPret) {
        return repository.save(newPret);
    }

    // Single item

    @GetMapping("/pret/{id}")
    Pret one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/pret-update/{id}")
    Pret replacePret(@RequestBody Pret newPret, @PathVariable Long id) {

        return repository.findById(id)
                .map(pret -> {
                    pret.setNom(newPret.getNom());
                    pret.setDate_sortie(newPret.getDate_sortie());
                    pret.setDate_retour(newPret.getDate_retour());
                    pret.setDescription(newPret.getDescription());
                    pret.setDirection(newPret.getDirection());
                    pret.setStatut(newPret.getStatut());
                    pret.setQte(newPret.getQte());
                    return repository.save(pret);
                })
                .orElseGet(() -> {
                    newPret.setId(id);
                    return repository.save(newPret);
                });
    }

    @DeleteMapping("/pret-delete/{id}")
    void deletePret(@PathVariable Long id) {
        repository.deleteById(id);
    }











}


