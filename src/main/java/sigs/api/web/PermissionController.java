package sigs.api.web;


import java.util.List;

import sigs.api.model.Permission;
import sigs.api.repository.PermissionRepository;
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
public class PermissionController
{


    private final PermissionRepository repository;

    PermissionController(PermissionRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/permissions")
    List<Permission> all() {
        return (List<Permission>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/permission-add")
    Permission newPermission(@RequestBody Permission newPermission) {
        return repository.save(newPermission);
    }

    // Single item

    @GetMapping("/permission/{id}")
    Permission one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RestApiNotFoundException(id));
    }

    @PutMapping("/permission-update/{id}")
    Permission replacePermission(@RequestBody Permission newPermission, @PathVariable Long id) {

        return repository.findById(id)
                .map(permission -> {
                    permission.setAction(newPermission.getAction());


                    return repository.save(permission);
                })
                .orElseGet(() -> {
                    newPermission.setId(id);
                    return repository.save(newPermission);
                });
    }

    @DeleteMapping("/permission-delete/{id}")
    void deletePermission(@PathVariable Long id) {
        repository.deleteById(id);
    }






}

