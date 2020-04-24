package Models;

import java.util.ArrayList;

public class Category {
    private String name;
    private String specialAttribute;
    private ArrayList<Category> subCategories;
    private Category parentCategory;
    private ArrayList<Product> allProducts;

    public Category(String name, String specialAttribute) {
        this.name = name;
        this.specialAttribute = specialAttribute;
        subCategories = new ArrayList<>();
        allProducts = new ArrayList<Product>();
        this.parentCategory = null;
    }

    public String getName() {
        return name;
    }

    public String getSpecialAttribute() {
        return specialAttribute;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialAttribute(String specialAttribute) {
        this.specialAttribute = specialAttribute;
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
}
