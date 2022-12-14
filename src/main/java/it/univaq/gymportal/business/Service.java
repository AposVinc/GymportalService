package it.univaq.gymportal.business;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public abstract class Service {

    protected static final String urlDB;
    protected static final String userDB;
    protected static final String pswDB;

    static {
        System.out.println("Load JDBC Driver");
        loadDriver();

        System.out.println("Init connection params");
        urlDB = "jdbc:mysql://127.0.0.1:8889/gymportal_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        userDB = "gymportal";
        pswDB = "gymportal";
    }

    protected static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //metodo per lo sviluppo, da eliminare
    public void printRS(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }

}
