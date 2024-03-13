package sigs.api.model;



// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

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
    private String seuil;







    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }




    public String getSeuil() {
        return seuil;
    }

    public void setSeuil(String seuil) {
        this.seuil = seuil;
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





}


