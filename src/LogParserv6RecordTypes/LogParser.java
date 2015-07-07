/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv6RecordTypes;

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

public class LogParser {

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

        GetStringType getStringType = new GetStringType();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS");

        Date date = new Date();

        String currentfilename = "";

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
            try {
                String string01 = "";
                int stringType = 0;

                BufferedReader br = new BufferedReader(new FileReader(folderPath + fileList.get(qi)));
                currentfilename = fileList.get(qi);
                System.out.println("Current file for work: " + currentfilename);
                try {
                    while ((string01 = br.readLine()) != null && (string01 = br.readLine()) != "") {
//                    System.out.println(string01);
                        stringType = getStringType.GetStringType(string01);
                        stringNumber += stringType;
                    System.out.println(stringNumber);
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
