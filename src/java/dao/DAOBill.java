/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author DELL
 */
import cart.Cart;
import entity.Bill;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;

public class DAOBill extends DBConnect {

    public boolean updateBillStatus(Bill pro) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE [SE1704].[dbo].[B] SET status=? WHERE bid= ?");
                ptm.setInt(1, pro.getStatus());
                ptm.setString(2, pro.getBid());

                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<Bill> getBill() throws SQLException {
        List<Bill> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[B]");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String bid = rs.getString("bid");
                    String dateCreate = rs.getString("dateCreate");
                    String recAddress = rs.getString("recAddress");
                    String recPhone = rs.getString("recPhone");
                    String note = rs.getString("note");
                    double totalMoney = rs.getDouble("totalMoney");
                    int status = rs.getInt("status");
                    String cid = rs.getString("cid");
                    list.add(new Bill(bid, dateCreate, recAddress, recPhone, note, totalMoney, status, cid));
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

    public List<Bill> searchBill(String dateCreate) throws SQLException, ClassNotFoundException {
        List<Bill> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[B] WHERE dateCreate = ?");
                ptm.setString(1, dateCreate);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bid = rs.getString("bid");
                    String recAddress = rs.getString("recAddress");
                    String recPhone = rs.getString("recPhone");
                    String note = rs.getString("note");
                    double totalMoney = rs.getDouble("totalMoney");
                    int status = rs.getInt("status");
                    String cid = rs.getString("cid");
                    list.add(new Bill(bid, dateCreate, recAddress, recPhone, note, totalMoney, status, cid));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public boolean createOrder(String cid, float total, Cart cart, String recAddress, String recPhone, String note) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            Date nowDate = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            String strNowDate = formater.format(nowDate);
            int status = 0;
            int count = 0;
            ptm = conn.prepareStatement("SELECT MAX(bid) AS [bid] FROM [SE1704].[dbo].[B]");
            rs = ptm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            count++;
            ptm = null;
            ptm = conn.prepareStatement("INSERT INTO [SE1704].[dbo].[B] (bid,dateCreate,recAddress,recPhone,note,totalMoney,status,cid) VALUES (?,?,?,?,?,?,?,?)");
            ptm.setInt(1, count);
            ptm.setString(2, strNowDate);
            ptm.setString(3, recAddress);
            ptm.setString(4, recPhone);
            ptm.setString(5, note);
            ptm.setFloat(6, total);
            ptm.setInt(7, status);
            ptm.setString(8, cid);
            check = ptm.executeUpdate() > 0;
            if (check) {
                ptm = null;
                rs = null;
                ptm = conn.prepareStatement("SELECT MAX(bid) AS [bid] FROM [SE1704].[dbo].[B]");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int billId = rs.getInt("bid");
                    int subtotal = 0;
                    for (Product pro : cart.getList()) {
                        ptm = null;
                        rs = null;
                        count = 0;
                        ptm = conn.prepareStatement("select max([billDetailId]) from [SE1704].[dbo].[BDetail]");
                        rs = ptm.executeQuery();
                        if (rs.next()) {
                            count = rs.getInt(1);
                        }
                        count++;
                        ptm = null;
                        rs = null;
                        ptm = conn.prepareStatement("INSERT INTO [SE1704].[dbo].[BDetail] (billDetailId,pid,buyQuantity,buyPrice,subtotal,billId) values (?,?,?,?,?,?)");
                        ptm.setInt(1, count);
                        ptm.setString(2, pro.getPid());
                        ptm.setInt(3, pro.getQuantity());
                        ptm.setDouble(4, pro.getPrice());
                        ptm.setInt(5, subtotal);
                        ptm.setInt(6, billId);
                        check = ptm.executeUpdate() > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public int AddBill(Bill bi) {
        int n = 0;
        String sql = "INSERT INTO [Bill]\n"
                + "           ([bid]\n"
                + "           ,[dateCreate]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[note]\n"
                + "           ,[totalMoney]\n"
                + "           ,[status])\n"
                + "           ,[cid]\n"
                + "     VALUES('" + bi.getBid() + "','" + bi.getDateCreate()
                + "','" + bi.getRecAddress() + "','" + bi.getRecPhone()
                + "','" + bi.getNote() + "','" + bi.getTotalMoney()
                + "'," + bi.getStatus() + bi.getCid() + ")";
        try {
            // taoj lenh
            Statement state = conn.createStatement();
            // chay va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addBillByPre(Bill bi) {
        int n = 0;
        String sql = "INSERT INTO [Bill]\n"
                + "           ([bid]\n"
                + "           ,[dateCreate]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[note]\n"
                + "           ,[totalMoney]\n"
                + "           ,[status])\n"
                + "           ,[cid]\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, bi.getBid());
            pre.setString(2, bi.getDateCreate());
            pre.setString(3, bi.getRecAddress());
            pre.setString(4, bi.getRecPhone());
            pre.setString(5, bi.getNote());
            pre.setDouble(6, bi.getTotalMoney());
            pre.setInt(7, bi.getStatus());
            pre.setString(8, bi.getCid());
            // run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateBill(Bill bi) {
        int n = 0;
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [dateCreate] = ?\n"
                + "      ,[recAddress] = ?\n"
                + "      ,[recPhone] = ?\n"
                + "      ,[note] = ?\n"
                + "      ,[totalMoney] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cid] = ?\n"
                + " WHERE [bid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, bi.getDateCreate());
            pre.setString(2, bi.getRecAddress());
            pre.setString(3, bi.getRecPhone());
            pre.setString(4, bi.getNote());
            pre.setDouble(5, bi.getTotalMoney());
            pre.setInt(6, bi.getStatus());
            pre.setString(7, bi.getCid());
            pre.setString(8, bi.getBid());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "select * from Bill where username=? and "
                + " password=? and status=1";
//        sql="select * from Customer where username='"+user+
//                "' and password='"+pass+"'";
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

    public Vector<Bill> getAllBill(String sql) {
        Vector<Bill> vector = new Vector<Bill>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Customer");
        try {
            while (rs.next()) {
                String bid = rs.getString("bid");
                String dateCreate = rs.getString(2);
                String recAddress = rs.getString(3);
                String recPhone = rs.getString(4);
                String note = rs.getString(5);
                double totalMoney = rs.getDouble("money");
                int sta = rs.getInt("status");
                String cid = rs.getString(8);
                Bill bi = new Bill(bid, dateCreate, recAddress, recPhone,
                        note, totalMoney, sta, cid);
                vector.add(bi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public void displayAll() {
        String sql = "select * from Bill";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String bid = rs.getString("bid");
                String dateCreate = rs.getString(2);
                String recAddress = rs.getString(3);
                String recPhone = rs.getString(4);
                String note = rs.getString(5);
                double totalMoney = rs.getDouble("money");
                int sta = rs.getInt("status");
                String cid = rs.getString(8);
                Bill bi = new Bill(bid, dateCreate, recAddress, recPhone,
                        note, totalMoney, sta, cid);
                System.out.println(bi);
            }
//TYPE_SCROLL_SENSITIVE: Scroll: con tro 2 chieu
// sensitive: threadSafe
//UPDATABLE: cho phep sua
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeBill(String id) {
        int n = 0;
        String sql = "delete from Bill where bid='" + id + "'";
        try {
            //note: Customer --1---n--> Bill --> khong xoa duoc
            // neu cid ton tai tren Bill (foreign key contrain)
            ResultSet rs = this.getData("Select * from Bill where cid='" + id + "'");
            if (rs.next()) {
                // co ton tai
                // thong bao
                n = -1; // khong xoa duoc vi contrain
                // servlet call update status of customer =0
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOBill dao = new DAOBill();
        dao.displayAll();
    }
}
