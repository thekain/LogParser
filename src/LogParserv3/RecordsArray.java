/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv3;

import LogParserv2.*;

/**
 *
 * @author Ohana
 */
public class RecordsArray {

    String dateString;
    String timeString;
    String messageType;
    String sourceString;
    String textString;

    public RecordsArray(String dateString, String timeString, String messageType, String sourceString, String textString) {
        this.dateString = dateString;
        this.timeString = timeString;
        this.messageType = messageType;
        this.sourceString = sourceString;
        this.textString = textString;
    }

}
