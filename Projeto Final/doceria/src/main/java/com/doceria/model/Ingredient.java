package com.doceria.model;

import java.util.Objects;

public class Ingredient {
    private int id;
    private int qtd;
    private String name;
    private Type type;

    public Ingredient(int id, int qtd, String name, Type type) {
        this.id = id;
        this.qtd = qtd;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
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

    public String getTypeName() {
        return type != null ? type.getName() : null;
    }

    @Override
    public String toString() {
        return name;  // Retorna o nome do ingrediente
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && qtd == that.qtd && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qtd, name, type);
    }
}
