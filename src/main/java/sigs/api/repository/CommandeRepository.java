package sigs.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sigs.api.model.Commande;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long> {
}
