package service;

import exception.DuplicateResourceException;
import exception.ResourceNotFoundException;
import model.interfaces.Validatable;
import model.Author;
import repository.AuthorRepository;

import java.util.List;

public class AuthorService {

    private final AuthorRepository repository = new AuthorRepository();
    public void create(Author author) {
        Validatable.requireNonNull(author, "Author cannot be null");
        author.validate();
        if (repository.existsByName(author.getName())) {
            throw new DuplicateResourceException("Author already exists");
        }
        repository.create(author);
    }

    public List<Author> getAll() {
        return repository.getAll();
    }

    public Author getById(int id) {
        Author author = repository.getById(id);
        if (author == null) {
            throw new ResourceNotFoundException("Author not found");
        }

        return author;
    }

    public void update(int id, Author author) {
        Validatable.requireNonNull(author, "Author cannot be null");
        author.validate();
        if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("Author not found");
        }
        repository.update(id, author);
    }

    public void delete(int id) {
        if (repository.getById(id) == null) {
            throw new ResourceNotFoundException("Author not found");
        }
        repository.delete(id);
    }
}
