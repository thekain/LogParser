/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv5LogType;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cnp.ak
 */
public class OplataBeelineKz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<RecordsArray> arrayList = new ArrayList<>();
        RecordsArray arrayObject;
        List<String> fileList = new ArrayList<>();

        String folderPath = "E:\\LOGS\\MTOPUP01\\logs_app05\\20150703logs\\oplatabeelinelogscatalina\\";
        String logFileName = "E:\\LOGS\\LogsReadyForWork\\catalinabeeline.rootlogger.log";
        String logFullFileName = "E:\\LOGS\\LogsReadyForWork\\catalinabeeline.rootlogger.fulllog.log";
        String mask = "catalina.out";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS");

        Date date = new Date();

        String currentfilename = "";

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getName();
                if (filename.contains(mask)) {
//                    System.out.println(listOfFiles[i].getName());
                    fileList.add(listOfFiles[i].getName());
                }
            }
        }

        for (int qi = 0; qi < fileList.size(); qi++) {

//            System.out.println("E:\\LOGS\\OFFICE01\\amadeus\\"+fileList.get(qi));
            try {
                //BufferedReader brOFFICE01Amadeus = new BufferedReader(new FileReader("E:\\LOGS\\OFFICE01\\amadeus\\providersws.log.2015-06-30.log"));
                BufferedReader brOFFICE01Amadeus = new BufferedReader(new FileReader(folderPath + fileList.get(qi)));
                currentfilename = fileList.get(qi);
                //System.out.println(currentfilename);
//            BufferedWriter bwOFFICE01Amadeus = new BufferedWriter(new FileWriter("E:\\LOGS\\LogsReadyForWork\\amadeus.providers.log"));
//                BufferedWriter bwOFFICE01Amadeus = new BufferedWriter(new FileWriter(logFileName));

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
                        System.out.println("1" + string01);
                        System.out.println("1" + string02);
                        System.out.println("1" + stringoriginal);
                    } else {
                        string02 = string01;
                        stringoriginal = string01;
                        System.out.println("2" + string01);
                        System.out.println("2" + string02);
                        System.out.println("2" + stringoriginal);
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

                    for (int i = 1; i < index04 - 1; i++) {
                        stringToParse += string01.charAt(i);
                    }

                    String parsedString[] = stringToParse.split(" ");
                    //System.out.println(parsedString[0] + " " + parsedString[1] + " " + parsedString[2] + " " + parsedString[3]);
                    System.out.println("3:" + parsedString[0]);
                    System.out.println("3:" + parsedString[1]);

                    try {
                        date = dateFormat.parse(parsedString[0] + " " + parsedString[1]);
                    } catch (Exception dateConverter) {
                        //    dateConverter.printStackTrace();
                        date = new Date();
                    }

                    arrayObject = new RecordsArray(
                            date,
                            parsedString[2],
                            parsedString[3],
                            string01.substring(index04),
                            currentfilename);
                    arrayList.add(arrayObject);
                    //       bwOFFICE01Amadeus.write(string01);
                    //     bwOFFICE01Amadeus.write('\n');
                    regString = "";
                    stringToParse = "";
                }
                //bwOFFICE01Amadeus.close();
            } catch (Exception allExceptions) {
                //allExceptions.printStackTrace();
            }

        }

        try {
            BufferedWriter fulllog = new BufferedWriter(new FileWriter(logFullFileName));

            Collections.sort(arrayList, new Comparator<RecordsArray>() {
                public int compare(RecordsArray m1, RecordsArray m2) {
                    return m1.dateString.compareTo(m2.dateString);
                }
            });

            for (int wi = 0; wi < arrayList.size(); wi++) {
                fulllog.write(dateFormat.format(arrayList.get(wi).dateString) + "\t" + arrayList.get(wi).fileName + "\t" + arrayList.get(wi).messageType + "\t" + arrayList.get(wi).sourceString + "\t" + arrayList.get(wi).textString);
                //fulllog.write(dateFormat.format(arrayList.get(wi).dateString) + " " + arrayList.get(wi).fileName + " " + arrayList.get(wi).messageType + " " + arrayList.get(wi).sourceString + " " + arrayList.get(wi).textString);
                fulllog.write('\n');
            }
            fulllog.close();
        } catch (Exception fullogException) {

        }

    } //Main end
} //package end
