/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hailt
 */
public class AccountDAO  {
    Connection connection = null;
     PreparedStatement st = null;
       ResultSet rs = null;
       
       public Account checkLogin(String email, String password){
           String sql = "select * from Account where [Email] = ? and [Password] = ?";
           try{
               connection = new DBContext().connection;
               st = connection.prepareStatement(sql);
               st.setString(1, email);
               st.setString(2, password);
               rs = st.executeQuery();
               while(rs.next()){
                   return new Account(rs.getInt(1),
                           rs.getString(2), 
                           rs.getString(3), 
                           rs.getString(4),
                           rs.getBoolean(5), 
                           rs.getString(6), 
                           rs.getString(7), 
                           rs.getString(8),
                           rs.getInt(9), 
                           rs.getDate(10), 
                           rs.getDate(11), 
                           rs.getDate(12), 
                           rs.getInt(13));
               }
           } catch(Exception e){
           }
        return null;
       }
  public Account registerAccount(String email, String password, String fullName , boolean gender , String phone, String address){
        try {
            LocalDate curDate = LocalDate.now();
            String date = curDate.toString();
            String imageAvatar = "anh_mac_dinh.jpg";
            String sql = "INSERT INTO Account (Email, Password, Name, Gender, Phone, Address, imageAvatar, Status, lastDateLogin, createDate, updateDate, roleId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            connection = new DBContext().connection;
            st = connection.prepareStatement(sql);
            st.setString(1,email);
            st.setString(2, password);
            st.setString(3, fullName);
            st.setBoolean(4, gender );
            st.setString(5, phone);
            st.setString(6, address);
            st.setString(7, imageAvatar );
            st.setInt(8, 1);
            st.setString(9, date);
            st.setString(10, date) ;
            st.setString(11, date);
            st.setInt(12, 2);
            st.executeUpdate();
            return new Account(0, email, password, fullName, gender, phone, address, imageAvatar, 1, java.sql.Date.valueOf(date), java.sql.Date.valueOf(date), java.sql.Date.valueOf(date), 2);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
  }
  
  public boolean checkAccountExist(String email){
        try {
            String sql = "select * from Account where Email = ?";
            connection = new DBContext().connection;
            st = connection.prepareStatement(sql);
            st.setString(1, email);
            rs = st.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
  }
  
  public void UpdateLastDateLogin(String email){
        try {
            LocalDate curDate = LocalDate.now();
            String date = curDate.toString();
            String sql = "UPDATE Account SET LastDateLogin = ? WHERE Email = ?";
            connection = new DBContext().connection;
            st = connection.prepareStatement(sql);
            st.setString(1, date);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
  }

  

}
