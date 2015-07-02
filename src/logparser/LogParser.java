/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logparser;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author cnp.ak
 */
public class LogParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            BufferedReader brOFFICE01Amadeus = new BufferedReader(new FileReader("E:\\LOGS\\OFFICE01\\amadeus\\providersws.log.2015-06-30.log"));
            BufferedWriter bwOFFICE01Amadeus = new BufferedWriter(new FileWriter("E:\\LOGS\\LogsReadyForWork\\amadeus.providers.log"));

            String string01 = "";
            String string02 = "";
            String stringoriginal = "";
            String stringDate = "";
            String regString = "";

            int index01;
            int index02;
            int index03;
            int index04;

            while ((string01 = brOFFICE01Amadeus.readLine()) != null) {
//                System.out.println(string01); //Просто тестовый вывод всего файла в консоль. DEBUG

                if (string01.charAt(0) == '<' && string01.charAt(string01.length() - 1) == '>') {
                    string02 = stringoriginal + string01;
                } else {
                    string02 = string01;
                    stringoriginal = string01;
                }
//                System.out.println(string02); //Просто тестовый вывод всего файла в консоль. DEBUG
                index01 = string02.indexOf(' ');
                index02 = string02.indexOf(' ', index01 + 1);
                index03 = string02.indexOf(' ', index02 + 1);
                index04 = string02.indexOf(' ', index03 + 1);

//                System.out.println(string02); //Просто тестовый вывод всего файла в консоль. DEBUG
//                System.out.print(index01 + " " + index02 + " " + index03 + " " + index04); //Вывод индексов
                string01 = string02.replaceAll("  ", " ");
                string02 = string01.replaceAll("\t", "--");
                string01 = string02;
//                string01 = string02;
                //string02 = string01.charAt(index01)="A";
                regString += string01.charAt(1);
                if (regString.matches("\\d+")) {
                    stringDate = "";
                    for (int i = 0; i < index02; i++) {
                        stringDate += string01.charAt(i);
                    }
                }
                //System.out.println("DATE:" + stringDate);
                if (string01.charAt(0) != '[' && string01.charAt(0) != '<') {
                    string02 = stringDate + string01;
                    //System.out.println(string001);
                    
                }
                
                
                System.out.println(string01);
                
                if (index01 != -1) {
                    string02 = string01.substring(0, index01) + '\t' + string01.substring(index01 + 1);
                }
                if (index02 != -1) {
                    string01 = string02.substring(0, index02) + '\t' + string02.substring(index02 + 1);
                }
                if (index03 != -1) {
                    string02 = string01.substring(0, index03) + '\t' + string01.substring(index03);
                }
                if (index04 != -1) {
                    string01 = string02.substring(0, index04) + '\t' + string02.substring(index04 + 1);
                }
                string02 = string01.replaceAll("\t\t", "\t");
                bwOFFICE01Amadeus.write(string02);
                bwOFFICE01Amadeus.write('\n');
            }
            bwOFFICE01Amadeus.close();
        } catch (Exception allExceptions) {
            allExceptions.printStackTrace();
        }

    }

}
