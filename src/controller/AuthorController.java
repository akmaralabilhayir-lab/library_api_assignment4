package controller;

import model.Author;
import model.BaseEntity;
import service.AuthorService;

public class AuthorController {
    private  AuthorService service = new AuthorService();
    public void create(Author author) {
        service.create(author);
        System.out.println("Author created:");
        author.printInfo();
    }

    public void getAll() {
        service.getAll().forEach(BaseEntity::printInfo);
    }

    public void getById(int id) {
        Author author = service.getById(id);
        author.printInfo();
    }

    public void update(int id, Author author) {
        service.update(id, author);
        System.out.println("Author updated:");
        author.printInfo();
    }

    public void delete(int id) {
        service.delete(id);
        System.out.println("Author deleted with id=" + id);
    }
}
