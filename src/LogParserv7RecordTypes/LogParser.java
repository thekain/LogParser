/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv7RecordTypes;

import LogParserv6RecordTypes.*;
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
import java.util.Locale;

public class LogParser {

    public static void main(String[] args) {
        List<RecordsArray> arrayList = new ArrayList<>();
        RecordsArray arrayObject;
        List<String> fileList = new ArrayList<>();

        String folderPath = "E:\\LOGS\\MTOPUP01\\logs_app05\\20150703logs\\oplatabeelinelogscatalina\\";
        String logFileName = "E:\\LOGS\\LogsReadyForWork\\catalinabeeline.rootlogger.log";
        String logFullFileName = "E:\\LOGS\\LogsReadyForWork\\catalinabeeline.rootlogger.fulllog.log";
        String mask = ".";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        GetStringType getStringType = new GetStringType();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS");
        //SimpleDateFormat dateFormatwithName = new SimpleDateFormat("MMMM dd, yyyy K:mm:ss a");
        SimpleDateFormat dateFormatwithName = new SimpleDateFormat("MMMM dd, yyyy K:mm:ss a", Locale.ENGLISH);

        String dateString = "";
        Date date = new Date();

        String currentfilename = "";

        String string11 = "";
        String string12 = "";

        String stringWithDate = "";

        String stringOutput = "";

        int stringNumber = 0;

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getName();
                if (filename.contains(mask)) {
                    fileList.add(listOfFiles[i].getName());
                }
            }
        }

        for (int qi = 0; qi < fileList.size(); qi++) {
            System.out.println(fileList.get(qi));
        }
//                    System.out.println("");
        
        
        for (int qi = 0; qi < fileList.size(); qi++) {
            try {
                String string01 = "";
                int stringType = 0;
                BufferedReader br = new BufferedReader(new FileReader(folderPath + fileList.get(qi)));
                BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName,true));
                currentfilename = fileList.get(qi);
                try {
                    while ((string01 = br.readLine()) != null) {
                        string11 = "";
                        string12 = "";
                        string11 = string01.replaceAll("      ", " ");
                        string12 = string11.replaceAll("     ", " ");
                        string11 = string12.replaceAll("    ", " ");
                        string12 = string11.replaceAll("   ", " ");
                        string11 = string12.replaceAll("  ", " ");
                        string12 = string11.replaceAll("\t", " ");
                        string11 = string12.replaceAll("\\]", "");
                        string12 = string11.replaceAll("\\ <", "<");
                        string11 = string12.replaceAll(" at", "at");
                        string12 = string11.replaceAll("\\ <", "<");
                        string11 = string12.replaceAll(" \\[", "");
                        string12 = string11.replaceAll("\\[", "");
                        String latest = string12;
                        String parsedString[] = latest.split(" ");
                        //  System.out.println(latest);
//                        System.out.println(parsedString[0]);
                        String somestring = "";
                        try {
                            somestring = somestring + parsedString[0].charAt(0) + parsedString[0].charAt(1);
                            // System.out.println(somestring);
                            if (somestring.matches("\\d+")) {
                                dateString = (parsedString[0] + " " + parsedString[1]);
                                //System.out.println(latest);
                                date = dateFormat.parse(parsedString[0] + " " + parsedString[1]);
                                stringOutput = dateFormat.format(date) + "||" + latest + "||" + currentfilename;
                                //System.out.println("DATA DIGITS: " + stringOutput);
                            } else //System.out.println(parsedString[0]);
                            if (parsedString[0].equals("Mar")
                                    || parsedString[0].equals("Jan")
                                    || parsedString[0].equals("Feb")
                                    || parsedString[0].equals("Mar")
                                    || parsedString[0].equals("Apr")
                                    || parsedString[0].equals("May")
                                    || parsedString[0].equals("Jun")
                                    || parsedString[0].equals("Jul")
                                    || parsedString[0].equals("Aug")
                                    || parsedString[0].equals("Sep")
                                    || parsedString[0].equals("Oct")
                                    || parsedString[0].equals("Nov")
                                    || parsedString[0].equals("Dec")) {
                                dateString = (parsedString[0] + " " + parsedString[1] + " " + parsedString[2] + " " + parsedString[3] + " " + parsedString[4]);
                                //System.out.println(latest);
                                //System.out.println(parsedString[0] + " " + parsedString[1] + " " + parsedString[2]);
                                date = dateFormatwithName.parse(parsedString[0] + " " + parsedString[1] + " " + parsedString[2] + " " + parsedString[3] + " " + parsedString[4]);
                                stringOutput = dateFormat.format(date) + "||" + latest + "||" + currentfilename;
                                //System.out.println("DATA WORD: " + stringOutput);
                            } else {
                                stringWithDate = dateFormat.format(date) + "||" + latest + "||" + currentfilename;
                                stringOutput = stringWithDate;
                                //System.out.println("NO DATA: " + stringOutput);
                                //System.out.println(stringWithDate);
                            }
                        } catch (Exception empty) {
                            //   empty.printStackTrace();
                        }

                        //System.out.println(stringOutput + " " + stringOutput.hashCode());
                        //System.out.println(dateFormat.format(date));
                        try {
                            bw.write(stringOutput + "||" + stringOutput.hashCode());
                            bw.write('\n');
                        } catch (Exception writeToFile) {
                        }
//                        System.out.println(stringOutput);
                    }

                } catch (Exception stringReadAttempt) {
                    stringReadAttempt.printStackTrace();
                    System.out.println("Can not read string: " + currentfilename);
                }

            } catch (Exception fileReadExeption) {
                fileReadExeption.printStackTrace();
                System.out.println("Can not read file: " + currentfilename);
            } //TRY for every file end
        } //IF for every file end
    } //Main end
} //package end
