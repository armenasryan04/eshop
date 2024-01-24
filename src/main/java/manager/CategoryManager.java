package manager;

import db.DBConnectionProvider;
import models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from category");
            while (resultSet.next()) {
                categoryList.add(getFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }


    public Category getById(int id) {
        Category category = new Category();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from category WHERE Id = " + id);
            if (resultSet.next()) {
                category = getFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }


    public Category addToDb(Category category) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO category(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public Category updateById(int id, String name) {
        String sql = "update category set name = ? where id = ";
        try (PreparedStatement statement = connection.prepareStatement(sql + id)) {
            statement.setString(1, name);
            statement.executeUpdate();
            getById(id).setName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(id);
    }

    public void deleteById(int id) {
        if (isHaveProduct(id) == 0) {
            String sql = "delete from category where id = ";
            try (PreparedStatement statement = connection.prepareStatement(sql + id)) {
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("you can't delete this category because you have products here \n please before deleting category delete the products in this category");
        }
    }

    private Category getFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;

    }

    private int isHaveProduct(int id) {
        int productId = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from product WHERE category_id = " + id);
            if (resultSet.next()) {
                productId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productId;
    }
}

