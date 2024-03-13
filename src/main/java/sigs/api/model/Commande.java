package sigs.api.model;


// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column
    private String date;


    @Column
    private String date_livraison;

    @Column
   // @JsonIgnore
    private String ref_cmd;


    @Column
    private String mnt_ttc;

    @Column
    private String duree;
    
    
    @Column
    // @JsonIgnore
    private String nom;


    @Column
    private String description;


    @Column
    private String solde;




    @Column
    private String statut;



    @Column
    private String qte;



    @Column
    private String prix_unitaire;




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }



    public String getRef_cmd() {
        return ref_cmd;
    }

    public void setRef_cmd(String ref_cmd) {
        this.ref_cmd = ref_cmd;
    }



    public String getMnt_ttc() {
        return mnt_ttc;
    }

    public void setMnt_ttc(String mnt_ttc) {
        this.mnt_ttc = mnt_ttc;
    }





    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getStatut() {
        return statut;
    }

        public void setStatut(String statut) {
        this.statut = statut;
    }



    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }



    public String getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(String prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }



}

