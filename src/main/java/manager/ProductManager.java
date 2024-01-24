package manager;

import db.DBConnectionProvider;

import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    CategoryManager categoryManager = new CategoryManager();

    public Product addToDb(Product product) {
        int price = product.getPrice();
        int quantity = product.getQuantity();
        int category_id = product.getCategory().getId();
        String sql = "INSERT INTO product (name,description,price,quantity,category_id) VALUES(?,?," + price + "," + quantity + "," + category_id + ")";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                product.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from product");
            while (resultSet.next()) {
                productList.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product getById(int id) {
        Product product = new Product();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from product WHERE Id = " + id);
            if (resultSet.next()) {
                product = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product updateById(int id, Product product) {
        String name = product.getName();
        String description = product.getDescription();
        int price = product.getPrice();
        int quantity = product.getQuantity();
        int category_id = product.getCategory().getId();
        String sql = "update  product set name = ? ,description = ?,price = ?,quantity = ?,category_id = ? where id = ";
        try (PreparedStatement statement = connection.prepareStatement(sql + id)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, price);
            statement.setInt(4, quantity);
            statement.setInt(5, category_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void deleteById(int id) {
        String sql = "delete from product where id = ";
        try (PreparedStatement statement = connection.prepareStatement(sql + id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSumProducts() {
        int sum = 0;
        String sql = "SELECT sum(quantity) from product  ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public int getMaxPriceOfProducts() {
        int maxPrice = 0;
        String sql = "SELECT MAX(price) from product";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maxPrice = resultSet.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxPrice;
    }

    public int getAvgPriceOfProducts() {
        int avgPrice = 0;
        String sql = "SELECT AVG(price) from product";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                avgPrice = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgPrice;
    }

    public int getMinPriceOfProducts() {
        int minPrice = 0;
        String sql = "SELECT MIN(price) from product";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                minPrice = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minPrice;
    }


    private Product getFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getInt("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setCategory(categoryManager.getById(resultSet.getInt("category_id")));
        return product;
    }
}






