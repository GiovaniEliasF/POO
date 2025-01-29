package com.doceria.repository;

import com.doceria.model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDAO {
    private final Connection connection;

    public TypeDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Type type) throws SQLException {
        String sql = "INSERT INTO type (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, type.getName());
            stmt.executeUpdate();

            // Recupera o ID gerado
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    type.setId(rs.getInt(1));
                }
            }
        }
    }

    public Type read(int id) throws SQLException {
        String sql = "SELECT * FROM type WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Type(rs.getInt("id"), rs.getString("name"));
                }
            }
        }
        return null;
    }

    public void update(Type type) throws SQLException {
        String sql = "UPDATE type SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, type.getName());
            stmt.setInt(2, type.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM type WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Type> listAll() throws SQLException {
        List<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM type";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                types.add(new Type(rs.getInt("id"), rs.getString("name")));
            }
        }
        return types;
    }
}
