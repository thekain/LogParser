/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv7RecordTypes;

import LogParserv6RecordTypes.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cnp.ak
 */
public class GetStringType {

    String inputString;

    public GetStringType() {

    }

    public int GetStringType(String inputString) {
        try {
            this.inputString = inputString;

            String string01 = "";
            String string02 = "";
            String string03 = "";
            String regString = "";
            String cleanString = "";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS");
            Date date = new Date();

            String recordDate = "";
            String recordTime = "";

            for (int i = 0; i < 15; i++) {
                string01 = inputString.replaceAll("  ", " ");
                string02 = string01.replaceAll("\t", " ");
                string01 = string02.replaceAll("\\]", "");
                string02 = string01.replaceAll("\\[", "");
                string02 = string01.replaceAll("\\ <", "<");
                string01 = string02.replaceAll(" at", "at");
                inputString = string01;
            }
            //String parsedString[] = string02.split(" ");

//            for (int i = 0; i < parsedString.length; i++) {
                //    System.out.println(parsedString[0]);
                //for (int j = 0; j < parsedString[i].length(); j++) {
                System.out.println(inputString);
                //}
  //          }
    //        System.out.println("");
            //System.out.println(parsedString[0] + " " + parsedString[1]);
//            regString = "";
//
//        if (regString.charAt(1)=='\\d+') {
//                System.out.println("re:"+parsedString[0].replaceAll("[", " "));
//                try {
//                    date = dateFormat.parse(string01 + " " + parsedString[1]);
//                    System.out.println(date);
//                } catch (Exception dateConverter) {
//                    dateConverter.printStackTrace();
//                }
//            }
        } catch (Exception aaa) {
            aaa.printStackTrace();
        }
        return 1;
    }
}
