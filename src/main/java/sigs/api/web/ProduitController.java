package sigs.api.web;

import java.util.List;

import sigs.api.model.Produit;
import sigs.api.repository.ProduitRepository;
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
public class ProduitController
{


    private final ProduitRepository repository;

    ProduitController(ProduitRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/produits")
    List<Produit> all() {
        return (List<Produit>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/produit-add")
    Produit newProduit(@RequestBody Produit newProduit) {
        return repository.save(newProduit);
    }

    // Single item

    @GetMapping("/produit/{id}")
    Produit one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/produit-update/{id}")
    Produit replaceProduit(@RequestBody Produit newProduit, @PathVariable Long id) {

        return repository.findById(id)
                .map(produit -> {
                    produit.setNom(newProduit.getNom());
                    produit.setDescription(newProduit.getDescription());
                    produit.setDate_creation(newProduit.getDate_creation());
                    produit.setSeuil(newProduit.getSeuil());
                    produit.setQte(newProduit.getQte());

                    return repository.save(produit);
                })
                .orElseGet(() -> {
                    newProduit.setId(id);
                    return repository.save(newProduit);
                });
    }

    @DeleteMapping("/produit-delete/{id}")
    void deleteProduit(@PathVariable Long id) {
        repository.deleteById(id);
    }






}

