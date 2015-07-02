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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cnp.ak
 */
public class LogParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<RecordsArray> arrayList = new ArrayList<>();
        RecordsArray arrayObject;
        try {
            //BufferedReader brOFFICE01Amadeus = new BufferedReader(new FileReader("E:\\LOGS\\OFFICE01\\amadeus\\providersws.log.2015-06-30.log"));
            BufferedReader brOFFICE01Amadeus = new BufferedReader(new FileReader("E:\\LOGS\\OFFICE01\\amadeus\\rootlogger.log.2015-07-01.log"));

//            BufferedWriter bwOFFICE01Amadeus = new BufferedWriter(new FileWriter("E:\\LOGS\\LogsReadyForWork\\amadeus.providers.log"));
            BufferedWriter bwOFFICE01Amadeus = new BufferedWriter(new FileWriter("E:\\LOGS\\LogsReadyForWork\\amadeus.rootlogger.log "));

            String string01 = "";
            String string02 = "";
            String stringoriginal = "";
            String stringDate = "";
            String regString = "";
            String stringToParse = "";

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

                index01 = string02.indexOf(' ');
                index02 = string02.indexOf(' ', index01 + 1);
                index03 = string02.indexOf(' ', index02 + 1);
                index04 = string02.indexOf(' ', index03 + 1);

                regString += string02.charAt(1);
                if (regString.matches("\\d+")) {
                    stringDate = "";
                    for (int i = 0; i < index04; i++) {
                        stringDate += string01.charAt(i);
                    }
                    string01 = string02;
                } else {
                    string01 = stringDate + " " + string02;
                }

                index01 = string01.indexOf(' ');
                index02 = string01.indexOf(' ', index01 + 1);
                index03 = string01.indexOf(' ', index02 + 1);
                index04 = string01.indexOf(' ', index03 + 1);

                //String splittedString[] = tempString.split("\t");                
                //System.out.println(index01 + " " + index02 + " " + index03 + " " + index04); //Вывод индексов
                for (int i = 1; i < index04 - 1; i++) {
                    stringToParse += string01.charAt(i);
                }
//                System.out.println(stringToParse);
                String parsedString[] = stringToParse.split(" ");
//                System.out.print("DATE: "+parsedString[0]);
//                System.out.print(" TIME: "+parsedString[1]);
                //System.out.println(parsedString[2]);
//                System.out.println(" MESSAGE: "+parsedString[3]);

//                if (parsedString[2].equals("DEBUG")) {
//                    System.out.println(stringToParse);
//                }
                arrayObject = new RecordsArray(
                        parsedString[0],
                        parsedString[1],
                        parsedString[2],
                        parsedString[3],
                        string01.substring(index04));
                arrayList.add(arrayObject);

                bwOFFICE01Amadeus.write(string01);
                bwOFFICE01Amadeus.write('\n');
                regString = "";
                stringToParse = "";
            }
            bwOFFICE01Amadeus.close();
        } catch (Exception allExceptions) {
            allExceptions.printStackTrace();
        }

//        for (int i = 0; i < arrayList.size(); i++) {
//            System.out.println(arrayList.get(i).dateString + arrayList.get(i).timeString + arrayList.get(i).textString);
//        }
        
        
    }
}
