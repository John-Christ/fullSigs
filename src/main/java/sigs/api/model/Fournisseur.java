package sigs.api.model;


// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "fournisseur")
public class Fournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nom;
    @Column
    // @JsonIgnore
    private String email;


    @Column
    private String tel;

    @Column
    private String adresse;
    @Column
    // @JsonIgnore
    private String ville;


    @Column
    private String pays;



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



    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }





    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


}


