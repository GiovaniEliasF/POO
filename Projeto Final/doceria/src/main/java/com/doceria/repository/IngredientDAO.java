package com.doceria.repository;

import com.doceria.model.Ingredient;
import com.doceria.model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    private final Connection connection;

    public IngredientDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Ingredient ingredient) throws SQLException {
        String sql = "INSERT INTO ingredients (qtd, name, type_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ingredient.getQtd());
            stmt.setString(2, ingredient.getName());
            stmt.setInt(3, ingredient.getType().getId());
            stmt.executeUpdate();
        }
    }

    public Ingredient read(int id) throws SQLException {
        String sql = "SELECT * FROM ingredients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Type type = new Type(rs.getInt("type_id"), ""); // Simples, pode ser expandido
                    return new Ingredient(rs.getInt("id"), rs.getInt("qtd"), rs.getString("name"), type);
                }
            }
        }
        return null;
    }

    public void update(Ingredient ingredient) throws SQLException {
        String sql = "UPDATE ingredients SET qtd = ?, name = ?, type_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ingredient.getQtd());
            stmt.setString(2, ingredient.getName());
            stmt.setInt(3, ingredient.getType().getId());
            stmt.setInt(4, ingredient.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ingredients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Ingredient> listAll() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT i.id, i.name, i.qtd, i.type_id, t.name AS type_name " +
                "FROM ingredients i " +
                "LEFT JOIN type t ON i.type_id = t.id";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int qtd = rs.getInt("qtd");
                int typeId = rs.getInt("type_id");
                String typeName = rs.getString("type_name");

                // Cria o objeto Type
                Type type = new Type(typeId, typeName);

                // Cria o objeto Ingredient
                Ingredient ingredient = new Ingredient(id, qtd, name, type);
                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }

    public ArrayList<Ingredient> findByProductId(int productId) throws SQLException {
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        String sql = """
            SELECT i.id, i.qtd, i.name, i.type_id, t.name AS type_name
            FROM ingredients i
            INNER JOIN product_ingredients pi ON i.id = pi.ingredient_id
            INNER JOIN type t ON i.type_id = t.id
            WHERE pi.product_id = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Type type = new Type(rs.getInt("type_id"), rs.getString("type_name"));
                    Ingredient ingredient = new Ingredient(
                            rs.getInt("id"),
                            rs.getInt("qtd"),
                            rs.getString("name"),
                            type
                    );
                    ingredients.add(ingredient);
                }
            }
        }
        return ingredients;
    }
}
