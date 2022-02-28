/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author FASIH
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class koneksi {

   private static Connection con;
   public static Connection koneksidb() throws SQLException{
       try {
           DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//           con = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6460162","sql6460162","lp82NHiTVV");
           con = DriverManager.getConnection("jdbc:mysql://localhost/kopikuy","root","");
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Online Database: koneksi gagal atau Lag "+e.getMessage());
       }
      
       return con;
   }
}
