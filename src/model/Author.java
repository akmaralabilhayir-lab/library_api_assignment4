package model;
import exception.InvalidInputException;

public class Author extends BaseEntity {
    public Author() {

    }
    public Author(int id, String name) {
        super(id, name);
        validate();
    }


    public String getInfo() {
        return "Author{name='" + name + "'}";
    }
    public void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Author name must not be empty");
        }
    }}
