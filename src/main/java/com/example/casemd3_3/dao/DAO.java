package com.example.casemd3_3.dao;

import com.example.casemd3_3.context.DBContext;
import com.example.casemd3_3.entity.Account;
import com.example.casemd3_3.entity.Category;
import com.example.casemd3_3.entity.Product;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public List<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() throws SQLException, ClassNotFoundException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Category(
                        resultSet.getInt("cid"),
                        resultSet.getString("cname")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLastProduct() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM product ORDER BY id DESC LIMIT 1;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getProductByCategoryID(String categoryId) throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE cateID = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM product WHERE id = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> searchProductByName(String name) throws SQLException, ClassNotFoundException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE `name` LIKE ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + name + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Account login(String user, String pass) throws ClassNotFoundException {
        String sql = "SELECT * FROM account WHERE `user` = ? AND pass = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(
                        resultSet.getInt("uID"),
                        resultSet.getString("user"),
                        resultSet.getString("pass"),
                        resultSet.getInt("isSell"),
                        resultSet.getInt("isAdmin")
                );
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Account checkAccountExist(String user) throws ClassNotFoundException {
        String sql = "SELECT * FROM account WHERE `user` = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(
                        resultSet.getInt("uID"),
                        resultSet.getString("user"),
                        resultSet.getString("pass"),
                        resultSet.getInt("isSell"),
                        resultSet.getInt("isAdmin")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void signup(String user, String pass) {
        String sql = "INSERT INTO account(`user`, pass, isSell, isAdmin) VALUES(?, ?, 0, 0);";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    public List<Product> getProductBySellID(int sellID) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE sell_ID = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sellID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                        resultSet.getDouble("price"),
                        resultSet.getString("title"),
                        resultSet.getString("description")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {

        }
        return list;
    }

    public void deleteProduct(String id) {
        String sql = "DELETE FROM product WHERE id = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    public void insertProduct(String name, String image, String price, String title, String description, String category, int sid) throws ClassNotFoundException {
        String sql = "INSERT INTO product(`name`, image, price, title, `description`, cateID, sell_ID) VALUES(?, ?, ?, ? ,? ,? , ?);";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, image);
            preparedStatement.setString(3, price);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, category);
            preparedStatement.setInt(7, sid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void updateProductById(String name, String image, String price, String title, String description, String category, String pid) {
        String sql = "UPDATE product SET `name` = ?, image = ?, price = ?, title = ?, `description` = ?, cateID = ? WHERE id = ?;";
        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, image);
            preparedStatement.setString(3, price);
            preparedStatement.setString(4, title);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, category);
            preparedStatement.setString(7, pid);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    public void placeOrder(String name, String phone, String totalPrice, String vat, String totalPayment, List<Product> cart) throws ClassNotFoundException {
        String orderSql = "INSERT INTO orders (name, phone, total_price, vat, total_payment, dateTime) VALUES (?, ?, ?, ?, ?, ?)";
        String orderItemsSql = "INSERT INTO order_items (order_id, product_id) VALUES (?, ?)";

        try (Connection connection = new DBContext().getConnection();
             PreparedStatement orderStatement = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement orderItemsStatement = connection.prepareStatement(orderItemsSql)) {

            // thêm thông tin vào bangr "orders"
            orderStatement.setString(1, name);
            orderStatement.setString(2, phone);
            orderStatement.setString(3, totalPrice);
            orderStatement.setString(4, vat);
            orderStatement.setString(5, totalPayment);
            LocalDateTime dateTime = LocalDateTime.now();
            orderStatement.setObject(6, dateTime);
            orderStatement.executeUpdate();

            // lấy id của đơn hàng vừa tạo
            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            int orderId = -1;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }

            // thêm thông tin chi tiết vào bảng "order_items"
            for (Product product : cart) {
                orderItemsStatement.setInt(1, orderId);
                orderItemsStatement.setInt(2, product.getId());
                orderItemsStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
