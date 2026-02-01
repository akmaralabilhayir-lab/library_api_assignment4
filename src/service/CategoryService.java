package service;

import exception.ResourceNotFoundException;
import model.Category;
import repository.CategoryRepository;
import java.util.List;

public class CategoryService {
    private CategoryRepository repo = new CategoryRepository();
    public void add(Category category) {
        category.validate();
        repo.create(category);
    }

    public List<Category> getAll() {
        return repo.getAll();
    }

    public Category getById(int id) {
        Category category = repo.getById(id);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found with id=" + id);
        }
        return category;
    }

    public void update(int id, Category category) {
        getById(id);
        category.validate();
        repo.update(id, category);
    }

    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }
}
