package sigs.api.web;


import java.util.List;

import sigs.api.model.Role;
import sigs.api.repository.RoleRepository;
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
public class RoleController
{


    private final RoleRepository repository;

    RoleController(RoleRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/roles")
    List<Role> all() {
        return (List<Role>) repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/role-add")
    Role newRole(@RequestBody Role newRole) {
        return repository.save(newRole);
    }

    // Single item

    @GetMapping("/role/{roleId}")
    Role one(@PathVariable Long roleId) {

        return repository.findById(roleId)
                .orElseThrow(() -> new RestApiNotFoundException(roleId));
    }

    @PutMapping("/role-update/{roleId}")
    Role replaceRole(@RequestBody Role newRole, @PathVariable Long roleId) {

        return repository.findById(roleId)
                .map(role -> {
                    role.setLabel(newRole.getLabel());
                    role.setStatut(newRole.getStatut());
                    role.setDescription(newRole.getDescription());
                    role.setUserSet(newRole.getUserSet());
                    role.setAssignedPrivileges(newRole.getAssignedPrivileges());



                    return repository.save(role);
                })
                .orElseGet(() -> {
                    newRole.setRoleId(roleId);
                    return repository.save(newRole);
                });
    }

    @DeleteMapping("/role-delete/{roleId}")
    void deleteRole(@PathVariable Long roleId) {
        repository.deleteById(roleId);
    }






}

