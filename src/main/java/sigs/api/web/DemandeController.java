package sigs.api.web;


import java.util.List;

import sigs.api.model.Demande;
import sigs.api.repository.DemandeRepository;
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
public class DemandeController
{


    private final DemandeRepository repository;

    DemandeController(DemandeRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/demandes")
    List<Demande> all() {
        return (List<Demande>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/demande-add")
    Demande newDemande(@RequestBody Demande newDemande) {
        return repository.save(newDemande);
    }

    // Single item

    @GetMapping("/demande/{id}")
    Demande one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/demande-update/{id}")
    Demande replaceDemande(@RequestBody Demande newDemande, @PathVariable Long id) {

        return repository.findById(id)
                .map(demande -> {
                    demande.setNom(newDemande.getNom());
                    demande.setPrenom(newDemande.getPrenom());
                    demande.setEmail(newDemande.getEmail());
                    demande.setTel(newDemande.getTel());
                    demande.setDate(newDemande.getDate());
                        demande.setStatut(newDemande.getStatut());
                    //  demande.setRole(newDemande.getRole());
                    return repository.save(demande);
                })
                .orElseGet(() -> {
                    newDemande.setId(id);
                    return repository.save(newDemande);
                });
    }

    @DeleteMapping("/demande-delete/{id}")
    void deleteDemande(@PathVariable Long id) {
        repository.deleteById(id);
    }









}


