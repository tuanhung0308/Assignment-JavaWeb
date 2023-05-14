/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author FPT
 */
import entity.Product;
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

public class DAOProduct extends DBConnect {

    public boolean register(Product pro) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("INSERT INTO [SE1704].[dbo].[Product] (pid,pname,quantity,price,image,description,status,cateId) VALUES (?,?,?,?,?,?,?,?)");
                ptm.setString(1, pro.getPid());
                ptm.setString(2, pro.getPname());
                ptm.setInt(3, pro.getQuantity());
                ptm.setDouble(4, pro.getPrice());
                ptm.setString(5, pro.getImage());
                ptm.setString(6, pro.getDescription());
                ptm.setInt(7, pro.getStatus());
                ptm.setInt(8, pro.getCateId());

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

    public boolean deletePro(String pid) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("DELETE [SE1704].[dbo].[Product] WHERE pid=?");
                ptm.setString(1, pid);
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

    public boolean updatePro(Product pro) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE [SE1704].[dbo].[Product] SET pname=?, quantity=?, price=?,image=?,description=?,status=?,cateId=? WHERE pid= ?");
                ptm.setString(1, pro.getPname());
                ptm.setInt(2, pro.getQuantity());
                ptm.setDouble(3, pro.getPrice());
                ptm.setString(4, pro.getImage());
                ptm.setString(5, pro.getDescription());
                ptm.setInt(6, pro.getStatus());
                ptm.setInt(7, pro.getCateId());
                ptm.setString(8, pro.getPid());
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

    public boolean checkDuplicateCId(String pid) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT pname FROM [SE1704].[dbo].[Product] WHERE pid=?");
                ptm.setString(1, pid);
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

    public List<Product> getProductsById(int cateId) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Product] WHERE cateId=?");
                stm.setInt(1, cateId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("pid");
                    String pname = rs.getString("pname");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    list.add(new Product(pid, pname, quantity, price, image, description, status, cateId));
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

    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Product]");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("pid");
                    String pname = rs.getString("pname");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateId = rs.getInt("cateId");
                    list.add(new Product(pid, pname, quantity, price, image, description, status, cateId));
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

    public List<Product> getProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Product] WHERE status = 1 ");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("pid");
                    String pname = rs.getString("pname");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateId = rs.getInt("cateId");
                    list.add(new Product(pid, pname, quantity, price, image, description, status, cateId));
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

    public List<Product> searchProduct(String search) throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Product] WHERE pname like ? AND status = 1");
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("pid");
                    String pname = rs.getString("pname");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateId = rs.getInt("cateId");
                    list.add(new Product(pid, pname, quantity, price, image, description, status, cateId));
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
    
    public List<Product> searchAllProduct(String search) throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Product] WHERE pname like ?");
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String pid = rs.getString("pid");
                    String pname = rs.getString("pname");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String image = rs.getString("image");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateId = rs.getInt("cateId");
                    list.add(new Product(pid, pname, quantity, price, image, description, status, cateId));
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

    public int AddProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateId])\n"
                + "     VALUES('" + pro.getPid() + "','" + pro.getPname()
                + "','" + pro.getQuantity() + "','" + pro.getPrice()
                + "','" + pro.getImage() + "','" + pro.getDescription()
                + "'," + pro.getStatus() + "','" + pro.getCateId() + ")";
        try {
            // taoj lenh
            Statement state = conn.createStatement();
            // chay va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProductByPre(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateId])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateId());
            // run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [pname] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cateId] = ?\n"
                + " WHERE [pid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setString(5, pro.getDescription());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateId());
            pre.setString(8, pro.getPid());
            // run
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "select * from Product where username=? and "
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

    public Vector<Product> getAllProduct(String sql) {
        Vector<Product> vector = new Vector<Product>();
        ResultSet rs = this.getData(sql);
        //   ResultSet rs = this.getData("select * from Customer");
        try {
            while (rs.next()) {
                String id = rs.getString("pid");
                //  String id=rs.getString(1);
                String pname = rs.getString("pname");
                //  String name = rs.getString("cname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                Product pro = new Product(id, pname, quantity, price,
                        image, description, status, cateId);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public void displayAll() {
        String sql = "select * from Product";
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
                String id = rs.getString("pid");
                String pname = rs.getString("pname");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int status = rs.getInt("status");
                int cateId = rs.getInt("cateId");
                Product pro = new Product(id, pname, quantity, price,
                        image, description, status, cateId);
                System.out.println(pro);
            }
//TYPE_SCROLL_SENSITIVE: Scroll: con tro 2 chieu
// sensitive: threadSafe
//UPDATABLE: cho phep sua
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeProduct(String id) {
        int n = 0;
        String sql = "delete from Product where pid='" + id + "'";
        try {
            ResultSet rs = this.getData("Select * from Product where pid='" + id + "'");
            if (rs.next()) {
                n = -1;
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        dao.displayAll();
    }
}
