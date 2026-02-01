import controller.AuthorController;
import controller.BookController;
import controller.CategoryController;
import model.*;
import utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AuthorController authorController = new AuthorController();
        CategoryController categoryController = new CategoryController();
        BookController bookController = new BookController();

        System.out.println("\nCreating authors...");
        Author a1 = new Author(0, "Sh. Murtaza");
        Author a2 = new Author(0, "I. Yesenberlin");

        authorController.create(a1);
        authorController.create(a2);

        System.out.println("\nCreating categories...");
        Category c1 = new Category(0, "Novel");
        Category c2 = new Category(0, "Historical Trilogy");

        categoryController.create(c1);
        categoryController.create(c2);

        System.out.println("\nCreating books...");
        Books book1 = new Books(0, "Ai men Aisha", a1, c1, true);
        Books book2 = new Books(0, "Koshpendiler", a2, c2, true);
        Books book3 = new Books(0, "Qyzyl Zhebe", a1, c2, false);
        Books book4 = new Books(0, "Khan Kene", a2, c2, false);

        bookController.create(book1);
        bookController.create(book2);
        bookController.create(book3);
        bookController.create(book4);

        System.out.println("\nBooks list (sorted):");
        bookController.getAll();


        System.out.println("\nPolymorphism demo:");
        List<BaseEntity> entities = new ArrayList<>();
        entities.add(new Author());
        entities.add(new Category());

        for (BaseEntity e : entities) {
            System.out.println(e.getInfo());
        }

        System.out.println("\nReflection demo:");
        ReflectionUtils.inspect(new Books());
    }
}
