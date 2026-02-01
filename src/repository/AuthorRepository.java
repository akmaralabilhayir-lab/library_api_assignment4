package repository;

import exception.DatabaseOperationException;
import model.Author;
import utils.DatabaseConnection;
import repository.interfaces.CrudRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements CrudRepository<Author> {
    public void create(Author author) {
        String sql = "INSERT INTO authors(name) VALUES (?)";
        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, author.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                author.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public List<Author> getAll() {
        String sql = "SELECT * FROM authors";
        List<Author> list = new ArrayList<>();
        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                list.add(new Author(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
            return list;
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public Author getById(int id) {
        String sql = "SELECT * FROM authors WHERE id=?";
        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Author(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public void update(int id, Author author) {
        String sql = "UPDATE authors SET name=? WHERE id=?";
        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, author.getName());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM authors WHERE id=?";

        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }

    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM authors WHERE name=?";

        try (
                Connection c = DatabaseConnection.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new DatabaseOperationException(e.getMessage());
        }
    }
}
