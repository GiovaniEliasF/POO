package com.doceria.controller;

import com.doceria.model.Ingredient;
import com.doceria.model.Type;
import com.doceria.repository.IngredientDAO;
import com.doceria.repository.TypeDAO;
import com.doceria.repository.DBConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class IngredientsController {

    @FXML private TableView<Ingredient> ingredientTable;
    @FXML private TableColumn<Ingredient, Integer> idColumn;
    @FXML private TableColumn<Ingredient, String> nameColumn;
    @FXML private TableColumn<Ingredient, Integer> quantityColumn;
    @FXML private TableColumn<Ingredient, String> typeColumn;

    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private ComboBox<Type> typeComboBox;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    private IngredientDAO ingredientDAO;
    private TypeDAO typeDAO;
    private ObservableList<Ingredient> ingredientList;

    // Construtor sem argumentos para compatibilidade com JavaFX
    public IngredientsController() {
        // Inicializa os DAOs com a conexão ao banco de dados
        Connection connection = DBConnection.getConnection();
        this.ingredientDAO = new IngredientDAO(connection);
        this.typeDAO = new TypeDAO(connection);
    }

    @FXML
    public void initialize() {
        // Inicialização dos elementos da tela
        ingredientList = FXCollections.observableArrayList();
        ingredientTable.setItems(ingredientList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        typeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTypeName()));

        loadTypes();
        loadIngredients();
    }

    private void loadTypes() {
        try {
            List<Type> types = typeDAO.listAll();
            typeComboBox.setItems(FXCollections.observableArrayList(types));
        } catch (SQLException e) {
            showError("Error Loading Data", "Could not load types from database.");
            e.printStackTrace();
        }
    }

    private void loadIngredients() {
        try {
            ingredientList.clear();
            ingredientList.addAll(ingredientDAO.listAll());
        } catch (SQLException e) {
            showError("Error Loading Data", "Could not load ingredients from database.");
            e.printStackTrace();
        }
    }

    @FXML
    public void addIngredient() {
        String name = nameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        Type selectedType = typeComboBox.getValue();

        if (name.isEmpty() || quantityText.isEmpty() || selectedType == null) {
            showError("Validation Error", "All fields must be filled.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            Ingredient newIngredient = new Ingredient(0, quantity, name, selectedType);
            ingredientDAO.create(newIngredient);
            ingredientList.add(newIngredient);
            clearFields();
        } catch (NumberFormatException e) {
            showError("Validation Error", "Quantity must be a valid number.");
        } catch (SQLException e) {
            showError("Error Adding Ingredient", "Could not add the ingredient to the database.");
        }
    }

    @FXML
    public void updateIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
        if (selectedIngredient == null) {
            showError("Selection Error", "No ingredient selected for update.");
            return;
        }

        String name = nameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        Type selectedType = typeComboBox.getValue();

        if (name.isEmpty() || quantityText.isEmpty() || selectedType == null) {
            showError("Validation Error", "All fields must be filled.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            selectedIngredient.setName(name);
            selectedIngredient.setQtd(quantity);
            selectedIngredient.setType(selectedType);

            ingredientDAO.update(selectedIngredient); // Atualiza no banco de dados
            ingredientTable.refresh(); // Atualiza a tabela
            clearFields();
        } catch (NumberFormatException e) {
            showError("Validation Error", "Quantity must be a valid number.");
        } catch (SQLException e) {
            showError("Error Updating Ingredient", "Could not update the ingredient in the database.");
        }
    }

    @FXML
    public void deleteIngredient() {
        Ingredient selectedIngredient = ingredientTable.getSelectionModel().getSelectedItem();
        if (selectedIngredient == null) {
            showError("Selection Error", "No ingredient selected for deletion.");
            return;
        }

        try {
            ingredientDAO.delete(selectedIngredient.getId()); // Remove do banco de dados
            ingredientList.remove(selectedIngredient); // Remove da lista
        } catch (SQLException e) {
            showError("Error Deleting Ingredient", "Could not delete the ingredient from the database.");
        }
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        typeComboBox.getSelectionModel().clearSelection();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

// Navegação
    public void home(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/mainView.fxml");
    }

    public void typesView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/typesView.fxml");
    }

    public void ingredientsView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/ingredientsView.fxml");
    }

    public void productsView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/productsView.fxml");
    }

    public void loginView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/loginView.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) ingredientTable.getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource(fxmlPath)));
    }

}
