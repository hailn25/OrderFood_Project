package dao;

import dal.DBContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoucherDAO {

    Connection conn = null;
    PreparedStatement ps = null;

    public void insertVoucherAccountDetails(String accountId, String voucherName, String description, int quantity, String releaseDate, String finishDate, String status) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);  // Start transaction

            // Insert into Voucher table
            String sql1 = "INSERT INTO Voucher ([VoucherName], [Description], [Quantity], [ReleaseDate], [FinishDate], [Status]) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            ps1 = conn.prepareStatement(sql1, PreparedStatement.RETURN_GENERATED_KEYS);
            ps1.setString(1, voucherName);
            ps1.setString(2, description);
            ps1.setInt(3, quantity);
            ps1.setString(4, releaseDate);
            ps1.setString(5, finishDate);
            ps1.setString(6, status);
            ps1.executeUpdate();

            // Retrieve the generated VoucherId
            rs = ps1.getGeneratedKeys();
            if (rs.next()) {
                String voucherId = rs.getString(1);

                // Insert into AccountVoucher table
                String sql2 = "INSERT INTO AccountVoucher ([AccountId], [VoucherId]) "
                        + "VALUES (?, ?)";
                ps2 = conn.prepareStatement(sql2);
                ps2.setString(1, accountId);
                ps2.setString(2, voucherId);
                ps2.executeUpdate();
            }

            conn.commit();  // Commit transaction
        } catch (Exception ex) {
            if (conn != null) {
                conn.rollback();  // Rollback transaction in case of error
            }
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps1 != null) {
                ps1.close();
            }
            if (ps2 != null) {
                ps2.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        VoucherDAO dao = new VoucherDAO();
        dao.insertVoucherAccountDetails("6", "Discount12", "12% discount", 4, "2024-07-01", "2024-07-01", "1");
    }
}
