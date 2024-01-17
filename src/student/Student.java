
package student;

import db.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Student {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    
    // get table max rows
    public int getMax()
    {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from student");
            while (rs.next())
            {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id + 1;
    }
    
    // insert data into student table
    public void insert(int id, String name, String date, String gender, String email, String phone, String address, String city, String country, String imagePath)
    {
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setString(8, city);
            ps.setString(9, country);
            ps.setString(10, imagePath);
            
            if (ps.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "New student have been added successfully");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
