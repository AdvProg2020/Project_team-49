package Models;

import Controller.DataBase;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private String name;
    private String specialAttributes;
    private ArrayList<Category> subCategories;
    private Category parentCategory;
    private ArrayList<Product> subProducts;

    public Category(String name, String specialAttributes, Category parentCategory) {
        this.name = name;
        this.specialAttributes = specialAttributes;
        subCategories = new ArrayList<>();
        subProducts = new ArrayList<Product>();
        this.parentCategory = parentCategory;
        if (parentCategory != null)
            parentCategory.addSubCategory(this);
    }

    public ArrayList<Product> getSubProducts() {
        return subProducts;
    }

    public void setSubProducts(ArrayList<Product> subProducts) {
        this.subProducts = subProducts;
    }

    public String getName() {
        return name;
    }

    public String getSpecialAttributes() {
        return specialAttributes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialAttributes(String specialAttributes) {
        this.specialAttributes = specialAttributes;
    }

    public void addProduct(Product product) {
        if (!subProducts.contains(product))
            subProducts.add(product);
    }

    public void removeProduct(Product product) {
        if (subProducts.contains(product))
            subProducts.remove(product);
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void addSubCategory(Category category) {
        if (subCategories.contains(category))
            return;

        subCategories.add(category);
        category.setParentCategory(this);
    }

    public void removeSubCategory(Category category) {
        if (!subCategories.contains(category))
            return;
        subCategories.remove(category);
        category.setParentCategory(null);
    }

    public ArrayList<String> showCategories() {
        return null;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    @Override
    public String toString() {
        String parentName = "";
        if (this.parentCategory == null) {
            parentName = null;
        } else {
            parentName = this.parentCategory.getName();
        }
        return
                "" + name + "!@"
                        + specialAttributes + "!@"
                        + parentName + "!@"
                        ;

    }
}
