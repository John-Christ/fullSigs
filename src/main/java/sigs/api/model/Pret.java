package sigs.api.model;


// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pret")
public class Pret {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column
    private String nom;
    @Column
    // @JsonIgnore
    private String description;


    @Column
    private String date_sortie;



    @Column
    private String date_retour;



    @Column
    private String statut;


    @Column
    // @JsonIgnore
    private String qte;


    @Column
    private String direction;







    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }



    public String getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }



    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
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


