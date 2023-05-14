/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author FPT
 */
import entity.Category;
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

public class DAOCategory extends DBConnect {
    
    public List<Category> getListAllCategory() throws SQLException {
        List<Category> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBConnect.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement("SELECT * FROM [SE1704].[dbo].[Category]");
                rs = stm.executeQuery();
                while (rs.next()) {
                    int cateid = rs.getInt("cateid");
                    String cateName = rs.getString("cateName");
                    int status = rs.getInt("status");
                    list.add(new Category(cateid, cateName, status));
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
    
    public int AddCategory(Category cat) {
        int n = 0;
        String sql = "INSERT INTO [Category]\n"
                + "           ([cateid]\n"
                + "           ,[cateName]\n"
                + "           ,[status]\n"
                + "     VALUES('" + cat.getCateId() + "','" + cat.getCateName()
                + "','" + cat.getTatus() + ")";
        try {
            // taoj lenh
            Statement state = conn.createStatement();
            // chay va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int addCategoryByPre(Category cat) {
        int n = 0;
        String sql = "INSERT INTO [Category]\n"
                + "           ([cateid]\n"
                + "           ,[cateName]\n"
                + "           ,[status]\n"
                + "     VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setInt(1, cat.getCateId());
            pre.setString(2, cat.getCateName());
            pre.setInt(3, cat.getTatus());
            // run
            n = pre.executeUpdate();

//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateCategory(Category cat) {
        int n = 0;
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [cateName] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [cateId] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, cat.getCateName());
            pre.setInt(2, cat.getTatus());
            pre.setInt(3, cat.getCateId());
            n = pre.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public boolean login(String user, String pass) {
        String sql = "select * from Category where username=? and "
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
    
    public Vector<Category> getAllCategory(String sql) {
        Vector<Category> vector = new Vector<Category>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                int cateId = rs.getInt("cateId");
                String cateName = rs.getString("cateName");
                int Status = rs.getInt("Status");
                Category cat = new Category(cateId, cateName, Status);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
        
    }
    
    public void displayAll() {
        String sql = "select * from Category";
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
                int cateId = rs.getInt("cateId");
                String cateName = rs.getString("cateName");
                int Status = rs.getInt("Status");
                Category cat = new Category(cateId, cateName, Status);
                System.out.println(cat);
            }
//TYPE_SCROLL_SENSITIVE: Scroll: con tro 2 chieu
// sensitive: threadSafe
//UPDATABLE: cho phep sua
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int removeCategory(String id) {
        int n = 0;
        String sql = "delete from Category where cateId='" + id + "'";
        try {
            ResultSet rs = this.getData("Select * from Category where cateId='" + id + "'");
            if (rs.next()) {
                n = -1;
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        dao.displayAll();
    }
}
