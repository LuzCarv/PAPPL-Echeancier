/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 96441
 */
public class DaoMail {

    public DaoMail() {
    }
    
    public void enregistrerMail (String messageAvantRedevable, String jourAvantRedevable,String messageApresRedevable,String jourApresRedevable,String messageAvantAgent, String jourAvantAgent,String messageApresAgent,String jourApresAgent) throws IOException {
            String nomFichier= "InfoMail";
            File f = new File(nomFichier);
            ArrayList<String> lignes = new ArrayList();
            lignes.add( messageAvantRedevable);
            lignes.add(jourAvantRedevable);
            lignes.add(messageApresRedevable);
            lignes.add(jourApresRedevable);
            lignes.add( messageAvantAgent);
            lignes.add(jourAvantAgent);
            lignes.add(messageApresAgent);
            lignes.add(jourApresAgent);
           
            BufferedWriter bufferedWriter;
            bufferedWriter = new BufferedWriter(new FileWriter(nomFichier));
            bufferedWriter.write(lignes.get(1));
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(0));   
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(3));
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(2));
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(5));
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(4));
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(7));
            bufferedWriter.newLine();
            bufferedWriter.write(lignes.get(6));
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
       
 
    }
    
    public ArrayList<String> lireInformationMail() throws  IOException{
            String ligne;
            String nomFichier= "InfoMail"; 
            ArrayList<String>lignes=new ArrayList<>();
           if(new File(nomFichier).exists()){
             BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
             for (int i=1;i<9;i++){ 
               ligne = fichier.readLine(); 
               lignes.add(ligne);
            }
            }else{
                String defautMessageRAvant = "Vous avez une échéance a payer";
                String defautMessageRApres ="Vous avez oublie de payer la derniere echeance";
                String defautMessageAAvant = "Un redevable a une échéance a payer";
                String defautMessageAApres = "Un redevable n'a pas paye sa derniere echeance";
                String defautJour = "5";
                lignes.add(defautJour);
                lignes.add(defautMessageRAvant);
                lignes.add(defautJour);
                lignes.add(defautMessageRApres);
                lignes.add(defautJour);
                lignes.add(defautMessageAAvant);
                lignes.add(defautJour);
                lignes.add(defautMessageAApres);
           }
            return lignes; 
    }
}
