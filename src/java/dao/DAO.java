package dao;

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Category;
import model.Product;
//import model.Product;

public class DAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

//    public ArrayList<Product> getAllProduct() {
//        ArrayList<Product> listProduct = new ArrayList<>();
//        String sql = "select * from Product";
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                listProduct.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
//                        rs.getDouble(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
//            }
//        } catch (Exception e) {
//        }
//        return listProduct;
//    }
    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> list = new ArrayList<>();
        String sql = "select *\n"
                + "from Account";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                boolean gender = rs.getBoolean(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                String imageAvatar = rs.getString(8);
                boolean status = rs.getBoolean(9);
                Date lastDateLogin = rs.getDate(10);
                Date createDate = rs.getDate(11);
                Date updateDate = rs.getDate(12);
                int roleId = rs.getInt(13);

                Account s = new Account(accountId, email, password, fullName, gender, phone, address, imageAvatar, status, lastDateLogin, createDate, updateDate, roleId);

                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Account getAccountByAId(String accountId) {
        String sql = "select *\n"
                + "from Account\n"
                + "where AccountId = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId1 = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                boolean gender = rs.getBoolean(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                String imageAvatar = rs.getString(8);
                boolean status = rs.getBoolean(9);
                Date lastDateLogin = rs.getDate(10);
                Date createDate = rs.getDate(11);
                Date updateDate = rs.getDate(12);
                int roleId = rs.getInt(13);

                Account s = new Account(accountId1, email, password, fullName, gender, phone, address, imageAvatar, status, lastDateLogin, createDate, updateDate, roleId);
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editAccount(int roleId, int status, int accountId) throws SQLException, ClassNotFoundException {
        try {
            String sql = "update [dbo].[Account]\n"
                    + "set RoleId = ?, [Status] = ?\n"
                    + "where [AccountId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            ps.setInt(2, status);
            ps.setInt(3, accountId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAccount(String aid) throws SQLException, ClassNotFoundException {
        try {
            String sql = "delete from [dbo].[Account]\n"
                    + "where [AccountId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, aid);
            ps.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getProductByRestaurantId(int restaurantId) throws SQLException, Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Product]\n"
                + "WHERE [RestaurantId] = ?";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String description = rs.getString(4);
            String imageURL = rs.getString(5);
            int categoryId = rs.getInt(6);
            int restaurantId1 = rs.getInt(7);
            boolean isSale = rs.getBoolean(8);
            int quantity = rs.getInt(9);
            Date createDate = rs.getDate(10);
            Date updateDate = rs.getDate(11);
            boolean status = rs.getBoolean(12);
            Product s = new Product(productId, name, price, description, imageURL, 
                    categoryId, restaurantId1, isSale, quantity, createDate, 
                    updateDate, status);
            list.add(s);
        }
        return list;
    }

    public ArrayList<Category> getAllCategory() throws SQLException, Exception {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Category]";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int categoryId = rs.getInt(1);
            String name = rs.getString(2);
            Date createDate = rs.getDate(3);
            Date updateDate = rs.getDate(4);
            Category s = new Category(categoryId, name, createDate, updateDate);
            list.add(s);
        }
        return list;
    }

        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            DAO dao = new DAO();
            ArrayList<Product> list = new ArrayList<>();
            list = dao.getProductByRestaurantId(3);
            System.out.println(list);
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
