package sigs.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import sigs.api.dao.DAOUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;
    @Column
    private String label;
    @Column
    // @JsonIgnore
    private String statut;


    @Column
    private String description;



    @JsonIgnore
    @ManyToMany(mappedBy = "assignedRoles")
    private Set<DAOUser> userSet = new HashSet<>();



    @ManyToMany
    @JoinTable(name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id")
    )
    private Set<Privilege> assignedPrivileges = new HashSet<>();





    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }



    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }



    public Set<DAOUser> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<DAOUser> userSet) {
        this.userSet = userSet;
    }




    public Set<Privilege> getAssignedPrivileges() {
        return assignedPrivileges;
    }

    public void setAssignedPrivileges(Set<Privilege> assignedPrivileges) {
        this.assignedPrivileges = assignedPrivileges;
    }


}


