package Models;

import Controller.DataBase;

import java.util.ArrayList;

public class Category {
    private String name;
    private String specialAttributes;
    private ArrayList<Category> subCategories;
    private Category parentCategory;
    private ArrayList<Product> allProducts;

    public Category(String name, String specialAttributes , Category parentCategory) {
        this.name = name;
        this.specialAttributes = specialAttributes;
        subCategories = new ArrayList<>();
        allProducts = new ArrayList<Product>();
        this.parentCategory = parentCategory;
    }



    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        this.allProducts = allProducts;
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

    public void addProduct(Product product){
        allProducts.add(product);
    }

    public void removeProduct(Product product){
        allProducts.remove(product);
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void addSubCategory(Category category){
        subCategories.add(category);
    }

    public void removeSubCategory(Category category){
        subCategories.remove(category);
    }

    public ArrayList<String> showCategories(){
        return null;
    }

    public Category getCategoryByName(String name) {
        return null;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }



}
