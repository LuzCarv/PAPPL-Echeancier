/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.DetteSimplifiee;
import models.EcheanceSimplifiee;
import models.Redevable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Luz
 */
public class www {
    
    
    public void ecrireEcheancier(DetteSimplifiee detSim){
       
        File originalWb = new File("Test1.xlsx"); 
        File clonedWb = new File("test2.xlsx");
        
        try {
            Files.copy(originalWb.toPath(), clonedWb.toPath());
            
            FileInputStream inputStream = new FileInputStream(new File("test2.xlsx"));
            Workbook livre = WorkbookFactory.create(inputStream);
            Sheet feuil = livre.getSheetAt(0);
            Row ligne = feuil.createRow(14);
            Cell cell = ligne.createCell(0);
            
            cell.setCellValue("Dear, " + detSim.getRedev());

            
            ArrayList<EcheanceSimplifiee> echeanceDet = detSim.getEs();
            feuil.shiftRows(28, feuil.getLastRowNum(), echeanceDet.size());


            CellStyle cellStyle1 = livre.createCellStyle();
            cellStyle1.setBorderLeft(BorderStyle.MEDIUM);

            CellStyle cellStyle2 = livre.createCellStyle();
            cellStyle2.setBorderLeft(BorderStyle.THIN);
            cellStyle2.setBorderRight(BorderStyle.THIN);

            CellStyle cellStyle3 = livre.createCellStyle();
            cellStyle3.setBorderRight(BorderStyle.MEDIUM);

            CellStyle cellStyle4 = livre.createCellStyle();
            cellStyle4.setBorderLeft(BorderStyle.MEDIUM);
            cellStyle4.setBorderBottom(BorderStyle.MEDIUM);

            CellStyle cellStyle5 = livre.createCellStyle();
            cellStyle5.setBorderLeft(BorderStyle.THIN);
            cellStyle5.setBorderRight(BorderStyle.THIN);
            cellStyle5.setBorderBottom(BorderStyle.MEDIUM);

            CellStyle cellStyle6 = livre.createCellStyle();
            cellStyle6.setBorderRight(BorderStyle.MEDIUM);
            cellStyle6.setBorderBottom(BorderStyle.MEDIUM);

            int i= 28;
            int j = 1;
            double sum=0;

            for(EcheanceSimplifiee echeSimp: echeanceDet){
                sum = sum +echeSimp.getMontant();
                ligne = feuil.createRow(i);

                cell = ligne.createCell(0);
                cell.setCellStyle(cellStyle1);
                cell.setCellValue("Deadline " + j);

                cell = ligne.createCell(3);
                cell.setCellValue(echeSimp.getDateDeadLine());
                cell.setCellStyle(cellStyle2);

                cell = ligne.createCell(4);
                cell.setCellValue(echeSimp.getMontant());
                cell.setCellStyle(cellStyle3);


                i++;
                j++;
            }
            
            ligne = feuil.createRow(i+1);
            cell = ligne.createCell(0);
            cell.setCellStyle(cellStyle4);

            cell = ligne.createCell(3);
            cell.setCellStyle(cellStyle5);

            cell = ligne.createCell(4);
            cell.setCellValue(sum);
            cell.setCellStyle(cellStyle6);

            i=28;
            for(EcheanceSimplifiee echeSimp: echeanceDet){
                feuil.addMergedRegion(new CellRangeAddress(i,i,0,2));
                i=i+1;   
            }

            OutputStream fileOut = new FileOutputStream("test2.xlsx");
            livre.write(fileOut);
            livre.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public DetteSimplifiee lireExcel(){
        DetteSimplifiee detSimp = new DetteSimplifiee();
        try
        {
            FileInputStream file = new FileInputStream(new File("test3.xlsx"));
 
            XSSFWorkbook livre = new XSSFWorkbook(file);
            XSSFSheet feuil = livre.getSheetAt(0);
            Boolean trouve=false;
            String nom =null;
            String date[]=null;
            String Libelle="";
            int rowEcheance=0;

            for (int j = feuil.getFirstRowNum(); j <= feuil.getLastRowNum(); j++) {
                try{
                    XSSFRow row = feuil.getRow(j);
                       if(trouve){
                           break;
                       }
                    for (int k = row.getFirstCellNum(); k < row.getLastCellNum(); k++) {
                       XSSFCell cell = row.getCell(k);
                          if(cell.getStringCellValue().equals("Datee")) {
                             rowEcheance=j+1; 
                          }
                          if(cell.getStringCellValue().equals("Libellé")) {
                             XSSFRow row1 = feuil.getRow(j+1);
                             int colonne = cell.getColumnIndex();
                             Libelle = row1.getCell(colonne).getStringCellValue();
                             
                          }
                          String mot = "Dear";
                          if(cell.getStringCellValue().toLowerCase().contains(mot.toLowerCase())) {
                             nom = cell.getStringCellValue();
                          }
                          mot = "Nantes le"; // ya
                          if(cell.getStringCellValue().toLowerCase().contains(mot.toLowerCase())) {
                             date = cell.getStringCellValue().split(" ");
                          }
                    } 
                    }catch(java.lang.NullPointerException e){
                            
                    }catch(java.lang.IllegalStateException e){
                        
                    }
                
            }
            
            
            Redevable redv = new Redevable();
            if(nom != null){          
                Matcher m = Pattern.compile("[^A-Za-z ]+").matcher(nom);
                if (m.find()) {
                   redv.setNom(nom.substring(m.end()).trim()); 
                }else if(nom.toLowerCase().contains("dear")){
                    redv.setNom(nom.substring(nom.toLowerCase().indexOf("dear") + 4).trim());
                }else{
                    redv.setNom(nom);
                }
                
            }
            
            detSimp.setRedev(redv);
            detSimp.setLibelle(Libelle);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try{
                int l = date.length;
                if(date[l-1] != null){
                    detSimp.setDateCreation(LocalDate.parse(date[l-1],formatter));
                }
            }catch(java.lang.NullPointerException e){
                
            }
            
            XSSFRow row = feuil.getRow(rowEcheance);
            double sum=0;
            ArrayList<EcheanceSimplifiee> echeances = new ArrayList<>();
            while(rowEcheance != 0 && !(row.getCell(0).getStringCellValue().equals("")) ){
                EcheanceSimplifiee echeance = new EcheanceSimplifiee();
                echeance.setDateDeadLine(row.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                echeance.setMontant(row.getCell(4).getNumericCellValue());
                echeances.add(echeance);
                sum = sum + row.getCell(4).getNumericCellValue();
                rowEcheance = rowEcheance +1;
                row = feuil.getRow(rowEcheance);
                
            }
            detSimp.setMontant(sum);
            detSimp.setEs(echeances);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return detSimp;    
    }        
}
