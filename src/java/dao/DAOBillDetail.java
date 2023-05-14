/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author FPT
 */
import entity.BillDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;

public class DAOBillDetail extends DBConnect {

    public List<BillDetail> getProductsById(String billId) throws SQLException {
        List<BillDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[BDetail] WHERE billId = ?");
                stm.setString(1, billId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String billDetailId = rs.getString("billDetailId");
                    String pid = rs.getString("pid");
                    int buyQuantity = rs.getInt("buyQuantity");
                    double buyPrice = rs.getDouble("buyPrice");
                    double subtotal = rs.getDouble("subtotal");
                    list.add(new BillDetail(billDetailId, pid, billId, buyQuantity, buyPrice, subtotal));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public int AddBillDetail(BillDetail bide) {
        int n = 0;
        String sql = "INSERT INTO [Product]\n"
                + "           ([bid]\n"
                + "           ,[pid]\n"
                + "           ,[buyQuantity]\n"
                + "           ,[buyPrice]\n"
                + "           ,[subtotal]\n"
                + "     VALUES('" + bide.getBid() + "','" + bide.getPid()
                + "','" + bide.getBuyQuantity() + "'," + bide.getBuyPrice()
                + "','" + bide.getSubtotal() + ")";
        try {
            // taoj lenh
            Statement state = conn.createStatement();
            // chay va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addBillDetailByPre(BillDetail bide) {
        int n = 0;
        String sql = "INSERT INTO [BillDetail]\n"
                + "           ([bid]\n"
                + "           ,[pid]\n"
                + "           ,[buyQuantity]\n"
                + "           ,[buyPrice]\n"
                + "           ,[subtotal]\n"
                + "     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, bide.getBid());
            pre.setString(2, bide.getPid());
            pre.setInt(3, bide.getBuyQuantity());
            pre.setDouble(4, bide.getBuyPrice());
            pre.setDouble(5, bide.getSubtotal());
            // run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateBillDetail(BillDetail bide) {
        int n = 0;
        String sql = "UPDATE [dbo].[BillDetail]\n"
                + "   SET [pid] = ?\n"
                + "      ,[buyQuantity] = ?\n"
                + "      ,[buyPrice] = ?\n"
                + "      ,[subtotal] = ?\n"
                + " WHERE [bid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, bide.getPid());
            pre.setInt(2, bide.getBuyQuantity());
            pre.setDouble(3, bide.getBuyPrice());
            pre.setDouble(4, bide.getSubtotal());
            pre.setString(5, bide.getBid());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "select * from BillDetail where username=? and "
                + " password=? and status=1";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Vector<BillDetail> getAllBillDetail(String sql) {
        Vector<BillDetail> vector = new Vector<BillDetail>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                String bid = rs.getString("bid");
                String pid = rs.getString("pid");
                int buyquantity = rs.getInt("buyQuantity");
                double buyprice = rs.getDouble("buyPrice");
                double subtotal = rs.getDouble("subtotal");
                BillDetail bide = new BillDetail(bid, pid, buyquantity, buyprice, subtotal);
                vector.add(bide);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public void displayAll() {
        String sql = "select * from BillDetail";
        try {
            //        Statement state=conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
//                ResultSet.CONCUR_READ_ONLY);
//default: TYPE_FORWARD_ONLY con tro chi di xuong
//CONCUR_READ_ONLY: ket qua la chi doc
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
//                 dataType varName=rs.getDataType("fieldName|index);
//                 index start 1
                String bid = rs.getString("bid");
                String pid = rs.getString("pid");
                int buyquantity = rs.getInt("buyQuantity");
                double buyprice = rs.getDouble("buyPrice");
                double subtotal = rs.getDouble("subtotal");
                BillDetail bide = new BillDetail(bid, pid, buyquantity, buyprice, subtotal);
                System.out.println(bide);
            }
//TYPE_SCROLL_SENSITIVE: Scroll: con tro 2 chieu
// sensitive: threadSafe
//UPDATABLE: cho phep sua
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeBillDetail(String id) {
        int n = 0;
        String sql = "delete from BillDetail where bid='" + id + "'";
        try {
            ResultSet rs = this.getData("Select * from BillDetail where bid='" + id + "'");
            if (rs.next()) {
                n = -1;
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOBillDetail dao = new DAOBillDetail();
        dao.displayAll();
    }
}
