package sigs.api.model;



// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nom;
    @Column
   // @JsonIgnore
    private String description;


    @Column
    private String date_creation;

    @Column
    private String seuil;
    @Column
   // @JsonIgnore
    private String qte;






    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }



    public String getSeuil() {
        return seuil;
    }

    public void setSeuil(String seuil) {
        this.seuil = seuil;
    }





    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

