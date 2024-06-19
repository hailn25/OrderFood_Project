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
import java.util.Date;
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

    public void addBlog(String title, String content, String imageURL, String summary, int updateBy, boolean status, Date createDate, Date updateDate) {
        try {
            String sql = "INSERT INTO [dbo].[Blog] \n"
                    + "    (Title, Content, ImageURL, Summary, UpdateBy, [Status], CreateDate, UpdateDate)\n"
                    + "VALUES \n"
                    + "    (?, ?, ?, ?, ?, ?, ?, ?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, imageURL);
            ps.setString(4, summary);
            ps.setInt(5, updateBy);
            ps.setBoolean(6, status);
            ps.setDate(7, new java.sql.Date(createDate.getTime()));
            ps.setDate(8, new java.sql.Date(updateDate.getTime()));

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editBlog(String title, String content, String imageURL, String summary, int updateBy, boolean status, Date updateDate, int blogId) {
        try {
            String sql = "UPDATE [dbo].[Blog]\n"
                    + "SET \n"
                    + "    Title = ?,\n"
                    + "    Content = ?,\n"
                    + "    ImageURL = ?,\n"
                    + "    Summary = ?,\n"
                    + "    UpdateBy = ?,\n"
                    + "    [Status] = ?,\n"
                    + "    [UpdateDate] = ?\n"
                    + "WHERE \n"
                    + "    BlogId = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, imageURL);
            ps.setString(4, summary);
            ps.setInt(5, updateBy);
            ps.setBoolean(6, status);
            ps.setDate(7, new java.sql.Date(updateDate.getTime()));
            ps.setInt(8, blogId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editStatusBlog(boolean status, Date updateDate, int blogId) {
        try {
            String sql = "UPDATE [dbo].[Blog]\n"
                    + "SET [Status] =  ?,\n"
                    + "    [UpdateDate] = ?\n"
                    + "WHERE BlogId = ?";

            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setDate(2, new java.sql.Date(updateDate.getTime()));
            ps.setInt(3, blogId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteBlog(int blogId) {
        try {
            String sql = "delete from [dbo].[Blog] \n"
                    + "where [BlogId] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Blog getBlogById(int blogId) {
        Blog blog = new Blog();
        try {
            String sql = "select * \n"
                    + "from Blog as b\n"
                    + "where b.BlogId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, blogId);
            rs = ps.executeQuery();

            while (rs.next()) {
                blog = new Blog(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getBoolean(7),
                        rs.getDate(8),
                        rs.getDate(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blog;
    }

    public static void main(String[] args) {
        BlogDAO dao = new BlogDAO();
    }
}
