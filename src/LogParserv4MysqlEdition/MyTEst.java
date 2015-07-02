/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv4MysqlEdition;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ohana
 */
public class MyTEst {

    public static void main(String[] args) {
        Mysql ms;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss,SSS");
        Date date = new Date();
        try {
            date = dateFormat.parse("23-06-2015 19:15:08,553");
        } catch (Exception ddd) {
        }
        try {
            ms = new Mysql(date.getTime(), "paymentpages.log.2015-06-23.log", "INFO", "ProvidersWebService:428", "init beelineService", "asdqs");
        } catch (Exception ddda) {
        }
    }
}
