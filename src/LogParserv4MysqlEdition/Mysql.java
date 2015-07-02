/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv4MysqlEdition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Ohana
 */
public class Mysql {

    long date;
    String filename;
    String messagetype;
    String source;
    String textString;
    String hash;

    String url = "jdbc:mysql://localhost:3306/";
    String driver = "com.mysql.jdbc.Driver";
    String dbname = "logparser";
    String username = "user";
    String password = "123123123";

    public Mysql(long date, String filename, String messagetype, String source, String textString, String hash) {
        this.date = date;
        this.filename = filename;
        this.messagetype = messagetype;
        this.source = source;
        this.textString = textString;

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbname, username, password);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM logparser.logs");
            while (rs.next()) {
                String sqwl = rs.getString("logfilename");
                System.out.println(sqwl);
            }

            int val = st.executeUpdate("INSERT into logparser.logs VALUES('"+date+"','"+filename+"','"+messagetype+"','"+source+"','"+textString+"','"+hash+"')");
            if (val == 1) {
                System.out.print("Successfully inserted value");
            }

            conn.close();
        } catch (Exception mysqlException) {
            mysqlException.printStackTrace();
        }
    }
}
