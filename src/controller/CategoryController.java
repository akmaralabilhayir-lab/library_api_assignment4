package controller;

import model.BaseEntity;
import model.Category;
import service.CategoryService;

public class CategoryController {
    private CategoryService service = new CategoryService();
    public void create(Category category) {
        service.add(category);
        System.out.println("Category created:");
        category.printInfo();
    }

    public void getAll() {
        service.getAll().forEach(BaseEntity::printInfo);
    }

    public void getById(int id) {
        Category category = service.getById(id);
        category.printInfo();
    }

    public void update(int id, Category category) {
        service.update(id, category);
        System.out.println("Category updated:");
        category.printInfo();
    }

    public void delete(int id) {
        service.delete(id);
        System.out.println("Category deleted with id=" + id);
    }
}
