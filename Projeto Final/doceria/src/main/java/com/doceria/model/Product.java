package com.doceria.model;

import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private Type type;
    private int qtd;
    private ArrayList <Ingredient> ingredients;

    public Product(int id, String name, Type type, int qtd, ArrayList<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.qtd = qtd;
        this.ingredients = ingredients;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getTypeName() {
        return type != null ? type.getName() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && qtd == product.qtd && Objects.equals(name, product.name) && Objects.equals(type, product.type) && Objects.equals(ingredients, product.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, qtd, ingredients);
    }
}
