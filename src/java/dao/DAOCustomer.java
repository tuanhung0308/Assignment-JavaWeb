/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author DELL
 */
import entity.Admin;
import entity.Customer;
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

public class DAOCustomer extends DBConnect {
    
    public List<Customer> getCustomerByUsername(String username) throws SQLException {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Customer] WHERE username=?");
                stm.setString(1, username);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String cid = rs.getString("cid");
                    String cname = rs.getString("cname");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    list.add(new Customer(cid, cname, username, password, address, phone, status));
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

    public List<Customer> searchCus(String search) throws SQLException, ClassNotFoundException {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Customer] WHERE cname like ?");
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String cid = rs.getString("cid");
                    String cname = rs.getString("cname");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    list.add(new Customer(cid, cname, username, password, address, phone, status));
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

    public List<Customer> getCustomer() throws SQLException {
        List<Customer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Customer]");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String cid = rs.getString("cid");
                    String cname = rs.getString("cname");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    list.add(new Customer(cid, cname, username, password, address, phone, status));
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

    public boolean deleteCus(String cid) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("DELETE [SE1704].[dbo].[Customer] WHERE cid=?");
                ptm.setString(1, cid);
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

    public boolean updateCus(Customer pro) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE [SE1704].[dbo].[Customer] SET cname=?, username=?, password=?,address=?,phone=?,status=? WHERE cid= ?");
                ptm.setString(1, pro.getCname());
                ptm.setString(2, pro.getUsername());
                ptm.setString(3, pro.getPassword());
                ptm.setString(4, pro.getAddress());
                ptm.setString(5, pro.getPhone());
                ptm.setInt(6, pro.getTatus());
                ptm.setString(7, pro.getCid());
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

    public Customer checkLogin(String username, String password) throws SQLException {
        Customer users = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("select * from Customer WHERE username =? and password=? and status=1");
                ptm.setString(1, username);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String cid = rs.getString("cid");
                    String cname = rs.getString("cname");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    users = new Customer(cid, cname, username, password, address, phone, status);
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
        return users;
    }

    public Admin checkLoginAdmin(String admin, String password) throws SQLException {
        Admin users = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("select * from [SE1704].[dbo].[admin] WHERE admin =? and password=? ");
                ptm.setString(1, admin);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    users = new Admin(admin, password);
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
        return users;
    }

    public boolean checkDuplicateUserName(String userName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT cid FROM [SE1704].[dbo].[Customer] WHERE username=?");
                ptm.setString(1, userName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean checkDuplicateCId(String cid) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT username FROM [SE1704].[dbo].[Customer] WHERE cid=?");
                ptm.setString(1, cid);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean register(Customer pro) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("INSERT INTO [SE1704].[dbo].[Customer] (cid,cname,username,password,address,phone,status) VALUES (?,?,?,?,?,?,?)");
                ptm.setString(1, pro.getCid());
                ptm.setString(2, pro.getCname());
                ptm.setString(3, pro.getUsername());
                ptm.setString(4, pro.getPassword());
                ptm.setString(5, pro.getAddress());
                ptm.setString(6, pro.getPhone());
                ptm.setInt(7, pro.getTatus());

                check = ptm.executeUpdate() > 0 ? true : false;
            }
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

    public int AddCustomer(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [Customer]\n"
                + "           ([cid]\n"
                + "           ,[cname]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[status])\n"
                + "     VALUES('" + cus.getCid() + "','" + cus.getCname()
                + "','" + cus.getUsername() + "','" + cus.getPassword()
                + "','" + cus.getAddress() + "','" + cus.getPhone()
                + "'," + cus.getTatus() + ")";
        try {
            // taoj lenh
            Statement state = conn.createStatement();
            // chay va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCustomerByPre(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [Customer]\n"
                + "           ([cid]\n"
                + "           ,[cname]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[status])\n"
                + "     VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, cus.getCid());
            pre.setString(2, cus.getCname());
            pre.setString(3, cus.getUsername());
            pre.setString(4, cus.getPassword());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getPhone());
            pre.setInt(7, cus.getTatus());
            // run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customer]\n"
                + "   SET [cname] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [cid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getUsername());
            pre.setString(3, cus.getPassword());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getPhone());
            pre.setInt(6, cus.getTatus());
            pre.setString(7, cus.getCid());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "select * from Customer where username=? and "
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

    public Vector<Customer> getAllCustomer(String sql) {
        Vector<Customer> vector = new Vector<Customer>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Customer");
        try {
            while (rs.next()) {
                String id = rs.getString("cid");
                //  String id=rs.getString(1);
                String name = rs.getString(2);
                //  String name = rs.getString("cname");
                String userName = rs.getString(3);
                String pass = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                int sta = rs.getInt("status");
                Customer cus = new Customer(id, name, userName, pass,
                        address, phone, sta);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public void displayAll() {
        String sql = "select * from Customer";
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
                String id = rs.getString("cid");
                //  String id=rs.getString(1);
                String name = rs.getString(2);
                //  String name = rs.getString("cname");
                String userName = rs.getString(3);
                String pass = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                int sta = rs.getInt("status");
                Customer cus = new Customer(id, name, userName, pass,
                        address, phone, sta);
                System.out.println(cus);
            }
//TYPE_SCROLL_SENSITIVE: Scroll: con tro 2 chieu
// sensitive: threadSafe
//UPDATABLE: cho phep sua
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeCustomer(String id) {
        int n = 0;
        String sql = "delete from customer where cid='" + id + "'";
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
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        /*        int n = dao.AddCustomer(new Customer("C02", "John", "abc", "1234567",
                "SG", "09423434", 0));
        if (n > 0) {
            System.out.println("inserted");
        }
        int m = dao.addCustomerByPre(new Customer("C03", "John", "abc1", "1234567",
                "SG", "09423434", 0));
        if (n > 0) {
            System.out.println("inserted");
        }
/*        int n = dao.updateCustomer(new Customer("C03", "Smith", "abc1", "1234567",
               "SG", "09423434", 0));
        if (n > 0) {
           System.out.println("updated");
        }
         */
        dao.displayAll();
    }
}
