/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Luz et Chenkai
 */
public class EcheanceSimplifiee {
    
    private LocalDateTime dateDeadLine;
    private double montant;
/**
 * Constructeur de la clase EcheanceSimplifiee
 * @param dateDeadLine  c'est la date à laquelle l'échéance doit être payé
 * @param montant  c'est le montant à payer dans l'échéance 
 */
    public EcheanceSimplifiee(LocalDateTime dateDeadLine, double montant) {
        this.dateDeadLine = dateDeadLine;
        this.montant = montant;
    }

    public EcheanceSimplifiee() {
    }

    public LocalDateTime getDateDeadLine() {
        return dateDeadLine;
    }

    public double getMontant() {
        return montant;
    }

    public void setDateDeadLine(LocalDateTime dateDeadLine) {
        this.dateDeadLine = dateDeadLine;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
        
    
}
