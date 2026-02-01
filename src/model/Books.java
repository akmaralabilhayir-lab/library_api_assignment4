package model;

import exception.InvalidInputException;
import model.interfaces.Validatable;

public class Books extends BaseEntity  {
    private Author author;
    private Category category;
    private boolean available;
    public Books(int id, String name, Author author, Category category, boolean available) {
        super(id, name);
        this.author = author;
        this.category = category;
        this.available = available;
        validate();
    }
    public Books() {
    }
    public Author getAuthor() { return author; }
    public Category getCategory() { return category; }
    public boolean isAvailable() { return available; }

    public String getInfo() {
        return "Book[id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author.getName() +
                ", category=" + category.getName() +
                ", available=" + available +
                ']';
    }

    public void validate() {
        if (name == null || name.trim().isEmpty())
            throw new InvalidInputException("Book name must not be empty");
        if (author == null)
            throw new InvalidInputException("Author must not be null");
        if (category == null)
            throw new InvalidInputException("Category must not be null");
    }
}
