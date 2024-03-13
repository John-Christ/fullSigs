package sigs.api.web;


import java.util.List;

import sigs.api.model.Commande;
import sigs.api.repository.CommandeRepository;
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
public class CommandeController
{


    private final CommandeRepository repository;

    CommandeController(CommandeRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/commandes")
    List<Commande> all() {
        return (List<Commande>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/commande-add")
    Commande newCommande(@RequestBody Commande newCommande) {
        return repository.save(newCommande);
    }

    // Single item

    @GetMapping("/commande/{id}")
    Commande one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/commande-update/{id}")
    Commande replaceCommande(@RequestBody Commande newCommande, @PathVariable Long id) {

        return repository.findById(id)
                .map(commande -> {
                    commande.setNom(newCommande.getNom());
                    commande.setDuree(newCommande.getDuree());
                    commande.setDate(newCommande.getDate());
                    commande.setDate_livraison(newCommande.getDate_livraison());
                    commande.setDescription(newCommande.getDescription());
                    commande.setMnt_ttc(newCommande.getMnt_ttc());
                    commande.setSolde(newCommande.getSolde());
                    commande.setStatut(newCommande.getStatut());
                    commande.setRef_cmd(newCommande.getRef_cmd());
                    commande.setQte(newCommande.getQte());
                    commande.setPrix_unitaire(newCommande.getPrix_unitaire());

                    return repository.save(commande);
                })
                .orElseGet(() -> {
                    newCommande.setId(id);
                    return repository.save(newCommande);
                });
    }

    @DeleteMapping("/commande-delete/{id}")
    void deleteCommande(@PathVariable Long id) {
        repository.deleteById(id);
    }










}



