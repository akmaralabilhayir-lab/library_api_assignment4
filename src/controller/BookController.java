package controller;

import model.Books;
import service.BookService;

public class BookController {
    private BookService service = new BookService();
    public void create(Books book) {
        service.add(book);
        System.out.println("Book created:");
        book.printInfo();
    }

    public void getAll() {
        service.getAll().forEach(book -> book.printInfo());
    }

    public void getById(int id) {
        Books book = service.getById(id);
        book.printInfo();
    }

    public void delete(int id) {
        service.delete(id);
        System.out.println("Book deleted with id=" + id);
    }
}
