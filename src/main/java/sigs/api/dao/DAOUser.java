package sigs.api.dao;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sigs.api.model.Role;

@Entity
@Table(name = "utilisateur")
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;


    @Column
    private String role;


   // @Column
    // private String role;
  // @ManyToMany
   //@JoinColumn(name = "role_id")
  // private Role role;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> assignedRoles = new HashSet<>();







    @Column
    private String nom;
    @Column
    private String prenom;


    @Column
    private String email;


    @Column
    private String tel;








    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



   public Set<Role> getAssignedRoles() {
       return assignedRoles;
   }

    public void setAssignedRoles(Set<Role> assignedRoles) {
        this.assignedRoles = assignedRoles;
    }






    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }








    @Override
    public String toString() {
        return "DAOUser [id=" + id + ", username=" + username +
                ", password=" + password + ", nom=" + nom + ", prenom=" + prenom +
                ", email=" + email + ", tel=" + tel + " , role=" + role + "]";
    }

}