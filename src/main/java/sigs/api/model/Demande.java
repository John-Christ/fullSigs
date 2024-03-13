package sigs.api.model;


// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String date;
    @Column
    //@JsonIgnore
    private String statut;


   // @Column
   // private String role;

    @Column
    private String nom;
    @Column
    // @JsonIgnore
    private String prenom;


    @Column
    private String email;


    @Column
    private String tel;




    public String getDate() {
        return date;
    }

        public void setDate(String date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }



   /* public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

*/



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

}

