/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv8Works;

import LogParserv7RecordTypes.*;
import LogParserv6RecordTypes.*;
import LogParserv5LogType.*;
import LogParserv4.*;
import LogParserv3.*;
import LogParserv2.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ohana
 */
public class RecordsArray {

    Date dateString;
    String messageType;
    String sourceString;
    String textString;
    String fileName;

    public RecordsArray(Date dateString, String messageType, String sourceString, String textString,String fileName) {
        this.dateString = dateString;
        this.messageType = messageType;
        this.sourceString = sourceString;
        this.textString = textString;
        this.fileName = fileName;
    }

}
