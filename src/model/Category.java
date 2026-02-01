package model;
import exception.InvalidInputException;

public class Category extends BaseEntity {
    public Category(int id, String name) {
        super(id, name);
        validate();
    }

    public Category() {

    }
    public String getInfo() {
        return "Category{id=" + id + ", name='" + name + "'}";
    }
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Category name must not be empty");
        }
    }
}
