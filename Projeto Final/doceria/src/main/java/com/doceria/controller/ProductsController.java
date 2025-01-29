package com.doceria.controller;

import com.doceria.model.Ingredient;
import com.doceria.model.Product;
import com.doceria.model.Type;
import com.doceria.repository.IngredientDAO;
import com.doceria.repository.ProductDAO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsController {

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> idColumn;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, String> typeColumn;
    @FXML private TableColumn<Product, Integer> quantityColumn;
    @FXML private TableColumn<Product, String> ingredientsColumn; // Nova coluna

    @FXML private TextField nameField;
    @FXML private TextField quantityField;
    @FXML private ComboBox<Type> typeComboBox;

    private final ProductDAO productDAO;
    private final TypeDAO typeDAO;
    private final IngredientDAO ingredientDAO;
    private ObservableList<Product> productList;

    public ProductsController() {
        Connection connection = DBConnection.getConnection();
        this.ingredientDAO = new IngredientDAO(connection);
        this.productDAO = new ProductDAO(connection, ingredientDAO);
        this.typeDAO = new TypeDAO(connection);
    }

    @FXML
    public void initialize() {
        productList = FXCollections.observableArrayList();
        productTable.setItems(productList);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Corrigido para evitar NullPointerException na exibição do tipo
        typeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTypeName()));


        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("qtd"));

        ingredientsColumn.setCellValueFactory(cellData -> {
            List<Ingredient> ingredients = cellData.getValue().getIngredients();
            String ingredientNames = ingredients.stream()
                    .map(Ingredient::getName)
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(ingredientNames);
        });

        loadTypes();
        loadProducts();
    }

    private void loadTypes() {
        try {
            List<Type> types = typeDAO.listAll();
            typeComboBox.setItems(FXCollections.observableArrayList(types));
        } catch (SQLException e) {
            showError("Error Loading Types");
        }
    }

    private void loadProducts() {
        try {
            productList.clear();
            List<Product> products = productDAO.listAll();

            // Para cada produto, carregamos a lista de ingredientes
            for (Product product : products) {
                ArrayList<Ingredient> ingredients = ingredientDAO.findByProductId(product.getId());
                product.setIngredients(ingredients);
            }

            productList.addAll(products);
        } catch (SQLException e) {
            showError("Error Loading Products");
        }
    }

    @FXML
    private void addProduct() {
        String name = nameField.getText();
        String quantityText = quantityField.getText();
        Type type = typeComboBox.getValue();
        ArrayList<Ingredient> selectedIngredients = AddProductController.exportIngredientsList();

        if (name.isEmpty() || quantityText.isEmpty() || type == null) {
            showError("All fields must be filled!");
            return;
        }

        if (selectedIngredients.isEmpty()) {
            showError("You must add at least one ingredient!");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            Product product = new Product(0, name, type, quantity, selectedIngredients);
            productDAO.create(product);
            loadProducts();
            clearFields();

            // Esvaziar a lista de ingredientes exportada do AddProductController
            selectedIngredients.clear();

        } catch (NumberFormatException e) {
            showError("Quantity must be a number!");
        } catch (SQLException e) {
            showError("Error adding product!");
        }
    }

    @FXML
    private void updateProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showError("Select a product to update!");
            return;
        }

        String name = nameField.getText();
        String quantityText = quantityField.getText();
        Type type = typeComboBox.getValue();

        if (name.isEmpty() || quantityText.isEmpty() || type == null) {
            showError("All fields must be filled!");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            selectedProduct.setName(name);
            selectedProduct.setQtd(quantity);
            selectedProduct.setType(type);
            productDAO.update(selectedProduct);
            loadProducts();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Quantity must be a number!");
        } catch (SQLException e) {
            showError("Error updating product!");
        }
    }

    @FXML
    private void deleteProduct() {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            showError("Select a product to delete!");
            return;
        }

        try {
            productDAO.delete(selectedProduct.getId());
            loadProducts();
        } catch (SQLException e) {
            showError("Error deleting product!");
        }
    }

    private void clearFields() {
        nameField.clear();
        quantityField.clear();
        typeComboBox.getSelectionModel().clearSelection();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
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

    public void addProductsView(ActionEvent event) throws IOException {
        switchScene(event, "/com/doceria/addProductsView.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        Stage stage = (Stage) productTable.getScene().getWindow();
        stage.getScene().setRoot(FXMLLoader.load(getClass().getResource(fxmlPath)));
    }
}
