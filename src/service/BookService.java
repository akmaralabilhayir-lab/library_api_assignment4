package service;
import exception.ResourceNotFoundException;
import model.Books;
import repository.BookRepository;
import java.util.List;
import utils.SortingUtils;

public class BookService {
    private  BookRepository repo = new BookRepository();
    public void add(Books book) {
        book.validate();
        repo.create(book);
    }

    public List<Books> getAll() {
        return repo.getAll();
    }

    public Books getById(int id) {
        Books book = repo.getById(id);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found with id=" + id);
        }
        return book;
    }

    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }

    private final BookRepository BookRepository = new BookRepository();
    public List<Books> getAllBooksSorted() {
        List<Books> books = BookRepository.getAll();
        SortingUtils.sortByToString(books);
        return books;
    }
}
