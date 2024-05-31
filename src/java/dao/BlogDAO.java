/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.BlogDTO;

/**
 *
 * @author quoch
 */
public class BlogDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Blog> getAllBlog() {
        ArrayList<Blog> listBlog = new ArrayList<>();
        try {
            String sql = "select * from [dbo].[Blog]";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listBlog.add(new Blog(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getDate(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBlog;
    }

    public ArrayList<BlogDTO> getAllBlogDTO() {
        ArrayList<BlogDTO> listBlog = new ArrayList<>();
        try {
            String sql = "SELECT Blog.BlogId, Blog.Title, Blog.[Content], Blog.ImageURL, Blog.Summary, Account.Name, Blog.Status, Blog.CreateDate, Blog.UpdateDate\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  Blog ON Account.AccountId = Blog.UpdateBy";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listBlog.add(new BlogDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getDate(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBlog;
    }

    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();

        for (BlogDTO bt : dao.getAllBlogDTO()) {
            System.out.println(bt.toString());
        }
    }
}
