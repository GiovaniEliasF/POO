package com.doceria.repository;

import com.doceria.model.Ingredient;
import com.doceria.model.Product;
import com.doceria.model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final Connection connection;
    private final IngredientDAO ingredientDAO;

    public ProductDAO(Connection connection, IngredientDAO ingredientDAO) {
        this.connection = connection;
        this.ingredientDAO = ingredientDAO;
    }

    public void create(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, type_id, qtd) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getType().getId());
            stmt.setInt(3, product.getQtd());
            stmt.executeUpdate();

            // Recupera o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1));
                }
            }

            // Associa os ingredientes ao produto
            associateIngredients(product);
        }
    }

    private void associateIngredients(Product product) throws SQLException {
        String sql = "INSERT INTO product_ingredients (product_id, ingredient_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Ingredient ingredient : product.getIngredients()) {
                stmt.setInt(1, product.getId());
                stmt.setInt(2, ingredient.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public Product read(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Type type = new Type(rs.getInt("type_id"), ""); // Simples, pode ser expandido
                    List<Ingredient> ingredients = ingredientDAO.findByProductId(id);
                    return new Product(rs.getInt("id"), rs.getString("name"), type, rs.getInt("qtd"), new ArrayList<>(ingredients));
                }
            }
        }
        return null;
    }

    public void update(Product product) throws SQLException {
        String sql = "UPDATE product SET name = ?, type_id = ?, qtd = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getType().getId());
            stmt.setInt(3, product.getQtd());
            stmt.setInt(4, product.getId());
            stmt.executeUpdate();
        }

        // Atualiza a associação de ingredientes
        removeIngredientsAssociation(product.getId());
        associateIngredients(product);
    }

    private void removeIngredientsAssociation(int productId) throws SQLException {
        String sql = "DELETE FROM product_ingredients WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        // Remove associações antes de deletar o produto
        removeIngredientsAssociation(id);

        String sql = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Product> listAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Type type = new Type(rs.getInt("type_id"), ""); // Simples, pode ser expandido
                List<Ingredient> ingredients = ingredientDAO.findByProductId(rs.getInt("id"));
                products.add(new Product(rs.getInt("id"), rs.getString("name"), type, rs.getInt("qtd"), new ArrayList<>(ingredients)));
            }
        }
        return products;
    }
}