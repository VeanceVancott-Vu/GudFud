package gutfud.dacs.DAO;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnect {
    private String status;
    private String url = "jdbc:mysql://localhost:3306/GutFud";
    private String user = "root";
    private String pass = "08112005";
    Connection con;

    public DBconnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(this.url, this.user, this.pass);
            this.status = "Connected";
        } catch (Exception var2) {
            this.status = "Error";
            var2.printStackTrace();
        }

    }

    public Connection getCon() {
        return this.con;
    }

    public ResultSet queryDB(String query) throws Exception {
        Statement st = this.getCon().createStatement();
        return st.executeQuery(query);
    }

    public int updateDB(String query) throws Exception {
        Statement st = this.getCon().createStatement();
        return st.executeUpdate(query);
    }

    public void close() throws SQLException {
        this.con.close();
    }
}
