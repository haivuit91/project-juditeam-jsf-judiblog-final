/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.dao.service.CategoryDAOService;
import model.entities.Category;

/**
 *
 * @author ThanhTuan
 */
public class CategoryDAO implements CategoryDAOService {

    private static CategoryDAO categoryDAO;

    public static CategoryDAO getInstance() {
        if (categoryDAO == null) {
            categoryDAO = new CategoryDAO();
        }
        return categoryDAO;
    }

    @Override
    public Category getCategoryByID(int categoryID) throws Exception {
        Category category = new Category();
        String sql = "select * from tblCategory where categoryID =? and isActive = 'true' ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setInt(1, categoryID);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            category.setCatID(rs.getInt("categoryID"));
            category.setCatName(rs.getString("categoryName"));
            category.setActive(true);
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) throws Exception {
        Category category = new Category();
        String sql = "select * from tblCategory where categoryName =? and isActive = 'true' ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, categoryName);
        ResultSet rs = sm.executeQuery();
        while (rs.next()) {
            category.setCatID(rs.getInt("categoryID"));
            category.setCatName(rs.getString("categoryName"));
            category.setActive(true);
        }
        return category;
    }

    @Override
    public boolean createCategory(Category category) throws Exception {
        String sql = "insert into tblCategory (categoryName,isActive) values(?,?)";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, category.getCatName());
        sm.setBoolean(2, category.isActive());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean removeCategory(Category category) throws Exception {
        String sql = "delete tblCategory where categoryID = ? ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setInt(1, category.getCatID());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean updateCategoryByID(Category category) throws Exception {
        String sql = "update tblCategory set categoryName =? ,isActive = ? where categoryID=? ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setString(1, category.getCatName());
        sm.setBoolean(2, category.isActive());
        sm.setInt(3, category.getCatID());
        return sm.executeUpdate() == 1;
    }

    @Override
    public boolean updateCategoryByName(Category category) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activeCategory(Category category) throws Exception {
        String sql = "update tblCategory set isActive = ? where categoryID= ? ";
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql);
        sm.setBoolean(1,category.isActive());
        sm.setInt(2, category.getCatID());
        return sm.executeUpdate() == 1;
    }

}
