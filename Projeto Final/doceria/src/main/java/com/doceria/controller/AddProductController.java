package com.doceria.controller;

import com.doceria.model.Ingredient;
import com.doceria.repository.IngredientDAO;
import com.doceria.repository.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddProductController {

    @FXML private ComboBox<Ingredient> ingredientComboBox;
    @FXML private TableView<Ingredient> ingredientTable;
    @FXML private TableColumn<Ingredient, Integer> ingredientIdColumn;
    @FXML private TableColumn<Ingredient, String> ingredientNameColumn;

    private final IngredientDAO ingredientDAO;
    private ObservableList<Ingredient> selectedIngredients;
    private static ArrayList<Ingredient> ingredientsArrayList = new ArrayList<>();

    public AddProductController() {
        Connection connection = DBConnection.getConnection();
        this.ingredientDAO = new IngredientDAO(connection);
    }

    @FXML
    public void initialize() {
        selectedIngredients = FXCollections.observableArrayList();
        ingredientTable.setItems(selectedIngredients);

        ingredientNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadIngredients();
    }

    private void loadIngredients() {
        try {
            ingredientComboBox.setItems(FXCollections.observableArrayList(ingredientDAO.listAll()));
        } catch (SQLException e) {
            showError("Error Loading Ingredients");
        }
    }

    @FXML
    private void addIngredient() {
        Ingredient selectedIngredient = ingredientComboBox.getValue();
        if (selectedIngredient == null) {
            showError("Select an ingredient!");
            return;
        }

        if (!selectedIngredients.contains(selectedIngredient)) {
            selectedIngredients.add(selectedIngredient);
        } else {
            showError("Ingredient already added!");
        }
    }

    @FXML
    private void removeIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
        if (selectedIngredient != null) {
            selectedIngredients.remove(selectedIngredient);
        } else {
            showError("Select an ingredient to remove!");
        }
    }

    @FXML
    private void conclude(ActionEvent event) {
        try {
            if (selectedIngredients.isEmpty()) {
                showError("Add at least one ingredient!");
                return;
            }

            // Salva a lista de ingredientes
            ingredientsArrayList = new ArrayList<>(selectedIngredients);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ingredients added successfully!");
            alert.showAndWait();

            // Redireciona para a tela de produtos
            switchScene(event, "/com/doceria/productsView.fxml");
        } catch (Exception e) {
            showError("Error saving ingredients!");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    public static ArrayList<Ingredient> exportIngredientsList() {
        return ingredientsArrayList;
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) ingredientTable.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
        stage.show();
    }
}
