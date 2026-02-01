package repository;

import exception.DatabaseOperationException;
import model.Author;
import model.Books;
import model.Category;
import utils.DatabaseConnection;
import repository.interfaces.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements CrudRepository<Books>{
    public void create(Books book) {
        String sql = "INSERT INTO books(name, author_id, category_id, available) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, book.getName());
            ps.setInt(2, book.getAuthor().getId());
            ps.setInt(3, book.getCategory().getId());
            ps.setBoolean(4, book.isAvailable());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public List<Books> getAll() {
        List<Books> list = new ArrayList<>();
        String sql = """
            SELECT b.id, b.name, b.available,
                   a.id AS a_id, a.name AS a_name,
                   c.id AS c_id, c.name AS c_name
            FROM books b
            JOIN authors a ON b.author_id = a.id
            JOIN category c ON b.category_id = c.id
        """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Author author = new Author(rs.getInt("a_id"), rs.getString("a_name"));
                Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
                list.add(new Books(
                        rs.getInt("id"),
                        rs.getString("name"),
                        author,
                        category,
                        rs.getBoolean("available")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public Books getById(int id) {
        String sql = """
            
            SELECT b.id, b.name, b.available,
                   a.id AS a_id, a.name AS a_name,
                   c.id AS c_id, c.name AS c_name
            FROM books b
            JOIN authors a ON b.author_id = a.id
            JOIN category c ON b.category_id = c.id
            WHERE b.id = ?
            GROUP BY b.id, b,name
            HAVING available = false
        """;
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Author author = new Author(rs.getInt("a_id"), rs.getString("a_name"));
                Category category = new Category(rs.getInt("c_id"), rs.getString("c_name"));
                return new Books(
                        rs.getInt("id"),
                        rs.getString("name"),
                        author,
                        category,
                        rs.getBoolean("available")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    @Override
    public void update(int id, Books book) {
        String sql = "UPDATE books SET name=?, author_id=?, category_id=? WHERE id=?";

        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, book.getName());
            ps.setInt(2, book.getAuthor().getId());
            ps.setInt(3, book.getCategory().getId());
            ps.setInt(4, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id=?";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }
}
