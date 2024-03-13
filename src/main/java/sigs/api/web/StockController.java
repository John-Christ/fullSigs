package sigs.api.web;


import java.util.List;

import sigs.api.model.Stock;
import sigs.api.repository.StockRepository;
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
public class StockController
{


    private final StockRepository repository;

    StockController(StockRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/stocks")
    List<Stock> all() {
        return (List<Stock>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/stock-add")
    Stock newStock(@RequestBody Stock newStock) {
        return repository.save(newStock);
    }

    // Single item

    @GetMapping("/stock/{id}")
    Stock one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/stock-update/{id}")
    Stock replaceStock(@RequestBody Stock newStock, @PathVariable Long id) {

        return repository.findById(id)
                .map(stock -> {
                    stock.setNom(newStock.getNom());
                    stock.setSeuil(newStock.getSeuil());
                    stock.setStatut(newStock.getStatut());
                    stock.setQte(newStock.getQte());
                    return repository.save(stock);
                })
                .orElseGet(() -> {
                    newStock.setId(id);
                    return repository.save(newStock);
                });
    }

    @DeleteMapping("/stock-delete/{id}")
    void deleteStock(@PathVariable Long id) {
        repository.deleteById(id);
    }










}


