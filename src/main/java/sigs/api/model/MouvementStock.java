package sigs.api.model;


// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "mouvement_stock")
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nom;
    @Column
    // @JsonIgnore
    private String statut;


    @Column
    private String qte;

    @Column
    private String type_mouvement;



    @Column
    private String date;




    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }




    public String getType_mouvement() {
        return type_mouvement;
    }

    public void setType_mouvement(String type_mouvement) {
        this.type_mouvement = type_mouvement;
    }



    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }





    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }







    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





}



