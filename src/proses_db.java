
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win 10
 */
public class proses_db {
 String dbURL = "jdbc:mysql://localhost/agenda";
    String user = "root";
    String pass ="";
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    
    public proses_db() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(proses_db.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         try {
            con = (Connection) DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(proses_db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     

    void tambah(String id_agenda, String agenda, Date tanggal, String tempat) throws SQLException {
         String sql = "insert into tb_agenda (nama_agenda, tanggal, tempat) values (?, ?, ?)";

    pst = con.prepareStatement(sql);

    // Set parameter untuk query

    pst.setString(1, agenda);

    // Mengonversi java.util.Date menjadi java.sql.Date dengan konstruktor
    if (tanggal != null) {
        pst.setDate(2, new java.sql.Date(tanggal.getTime()));  // Konversi menggunakan getTime()
    } else {
        pst.setNull(2, java.sql.Types.DATE); // Jika tanggal null, set null pada parameter tanggal
    }

    pst.setString(3, tempat);

     // Menjalankan query
     int executeUpdate = pst.executeUpdate(); // Mengeksekusi query untuk menambah data ke database
          
        
    }
    
    public ResultSet lihat(){
        String sql ="select * from tb_agenda";
     try {
         st = con.createStatement();
         rs = st.executeQuery(sql);
     } catch (SQLException ex) {
         Logger.getLogger(proses_db.class.getName()).log(Level.SEVERE, null, ex);
     }
     return rs;
        
    }
    
    
    
}
