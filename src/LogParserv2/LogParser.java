/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv2;

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
                string01 = string02.replaceAll("  ", " ");
                string02 = string01.replaceAll("\t", "--");

                regString += string02.charAt(1);
                System.out.println(string02.charAt(1));
                if (regString.matches("\\d+")) {
                    stringDate = "";
                    index02 = string02.indexOf(' ', string02.indexOf(' ') + 1);
                    System.out.println(index02);
                    for (int i = 0; i < index02; i++) {
                        stringDate += string01.charAt(i);
                    }
                    string01 = string02;
                } else {
                    string01 = stringDate + " " + string02;
                }
                System.out.println(string01);
                bwOFFICE01Amadeus.write(string01);
                bwOFFICE01Amadeus.write('\n');
            }
            bwOFFICE01Amadeus.close();
        } catch (Exception allExceptions) {
            allExceptions.printStackTrace();
        }
    }
}
