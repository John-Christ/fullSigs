package sigs.api.model;



// import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "compte_fournisseur")
public class CompteFournisseur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column
    private String nom;


    @Column
    // @JsonIgnore
    private String numero_compte;


    @Column
    private String date;

    @Column
    private String montant_acompte;



    public String getNumero_compte() {
        return numero_compte;
    }

    public void setNumero_compte(String numero_compte) {
        this.numero_compte = numero_compte;
    }

    public String getMontant_acompte() {
        return montant_acompte;
    }

    public void setMontant_acompte(String montant_acompte) {
        this.montant_acompte = montant_acompte;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


