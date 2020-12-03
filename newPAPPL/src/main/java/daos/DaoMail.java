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
    public void enregistrerMail (String message, String jour, boolean type) throws IOException {
        
        
            String nomFichier= "InfoMail";
            File f = new File(nomFichier);
            ArrayList<String> lignes = new ArrayList();
            if (f.exists()){
                BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
                for (int i=1;i<5;i++){
                    String ligne = fichier.readLine();
                    if(ligne == null){
                        ligne = "";
                    }
                    lignes.add(ligne);
                }
            }else{
                String defautMessageR = "Vous avez une échéance a payer";
                String defautMessageA = "Un redevable n'a pas payée son échéance";
                String defautJour = "5";
                lignes.add(defautJour);
                lignes.add(defautMessageR);
                lignes.add(defautJour);
                lignes.add(defautMessageA);
                
            }
            //true pour redevable et false pour agent
            
            BufferedWriter bufferedWriter;
            bufferedWriter = new BufferedWriter(new FileWriter(nomFichier));
            if (type){
                
                bufferedWriter.write(jour);
                bufferedWriter.newLine();
                bufferedWriter.write(message);   
                bufferedWriter.newLine();
                System.out.println("ttttttttttt"+ lignes.get(3));
                bufferedWriter.write(lignes.get(2));
                bufferedWriter.newLine();
                bufferedWriter.write(lignes.get(3));
                bufferedWriter.newLine();
            }else{
               
                bufferedWriter.write(lignes.get(0));
                bufferedWriter.newLine();
                bufferedWriter.write(lignes.get(1));
                bufferedWriter.newLine();
                bufferedWriter.write(jour);
                bufferedWriter.newLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
            }
            
            
            bufferedWriter.flush();
            bufferedWriter.close();
       
 
    }
    
    public String lireInformationMail(boolean type) throws  IOException{
            String nomFichier= "InfoMail";         
            BufferedReader fichier = new BufferedReader(new FileReader(nomFichier));
            
             String ligne;
            if(type){
                
                
                fichier.readLine();
                ligne = fichier.readLine();
                 
            }else{
                fichier.readLine();
                fichier.readLine();
                fichier.readLine();
                ligne = fichier.readLine();
           
            }
            
            return ligne; 
    }
}
