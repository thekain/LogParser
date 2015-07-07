/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv8WorksMysqlv3;

import LogParserv8WorksMysqlv2.*;
import LogParserv8WorksMysql.*;
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
import LogParserv4MysqlEdition.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        String url = "jdbc:mysql://localhost:3306/";
        String batchStatement = "?rewriteBatchedStatements=true";
        String driver = "com.mysql.jdbc.Driver";
        String dbname = "cnplogs";
        String username = "root";
        String password = "";
        String sqwl = null;
        int idNum;

        //  GetStringType getStringType = new GetStringType();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS");
        SimpleDateFormat dateFormatMysql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbname + batchStatement, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM cnplogs.logs");

            while (rs.next()) {
                sqwl = rs.getString("id");
            }
            System.out.println(sqwl);
            conn.close();
        } catch (Exception readSQL) {
        }

        if (sqwl == null) {
            idNum = 0;
        } else {
            idNum = Integer.parseInt(sqwl) + 1;
        }

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String filename = listOfFiles[i].getName();
                if (filename.contains(mask)) {
                    fileList.add(listOfFiles[i].getName());
                }
            }
        }
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbname + batchStatement, username, password);
            Statement st = conn.createStatement();
//            for (int qi = 0; qi < fileList.size(); qi++) {
//                System.out.println(fileList.get(qi));
//            }
//                    System.out.println("");
            for (int qi = 0; qi < fileList.size(); qi++) {
                try {
                    String string01 = "";
                    int stringType = 0;
                    BufferedReader br = new BufferedReader(new FileReader(folderPath + fileList.get(qi)));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName, true));
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
                                    stringOutput = dateFormat.format(date) + "╚" + latest + "╚" + currentfilename;
                                    //System.out.println("DATA DIGITS: " + stringOutput);
                                } else //System.out.println(parsedString[0]);
                                if (parsedString[0].equals("Jan")
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
                                    stringOutput = dateFormat.format(date) + "╚" + latest + "╚" + currentfilename;
                                    //System.out.println("DATA WORD: " + stringOutput);
                                } else {
                                    stringWithDate = dateFormat.format(date) + "╚" + latest + "╚" + currentfilename;
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

                                String parsedString2[] = stringOutput.split("╚");
                                //    System.out.println("Parsed2: " + parsedString2[1]);

                                //ms = new Mysql(dateFormatMysql.format(date), parsedString2[1], currentfilename, stringOutput.hashCode());
                                int val = st.executeUpdate("INSERT into cnplogs.logs VALUES('" + idNum + "','" + dateFormatMysql.format(date) + "','" + parsedString2[1] + "','" + currentfilename + "','" + stringOutput.hashCode() + "')");
                                if (val == 1) {
                                    //System.out.print("Successfully inserted value");
                                    idNum++;
                                }
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

            conn.close();
        } catch (Exception mysqlException) {
            mysqlException.printStackTrace();
        }
    } //Main end
} //package end
