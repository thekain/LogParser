/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogParserv8WorksMysqlv3;

import LogParserv8WorksMysqlv2.*;
import LogParserv8WorksMysql.*;
import LogParserv4MysqlEdition.*;
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

    String date;
    String message;
    String source;
    int hash;

    String url = "jdbc:mysql://localhost:3306/";
    String batchStatement = "?rewriteBatchedStatements=true";
    String driver = "com.mysql.jdbc.Driver";
    String dbname = "cnplogs";
    String username = "root";
    String password = "";
    String sqwl;
    int idNum;

    public Mysql(String date, String message, String source, int hash) {
        this.date = date;
        this.message = message;
        this.source = source;
        this.hash = hash;

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbname + batchStatement, username, password);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM cnplogs.logs");
            while (rs.next()) {
                sqwl = rs.getString("id");
            }
            //  System.out.println(sqwl);
            if (sqwl == null) {
                idNum = 0;
            } else {
                idNum = Integer.parseInt(sqwl) + 1;
            }

            int val = st.executeUpdate("INSERT into cnplogs.logs VALUES('" + idNum + "','" + date + "','" + message + "','" + source + "','" + hash + "')");
            if (val == 1) {
                //System.out.print("Successfully inserted value");
            }

            conn.close();
        } catch (Exception mysqlException) {
            mysqlException.printStackTrace();
        }
    }
}
