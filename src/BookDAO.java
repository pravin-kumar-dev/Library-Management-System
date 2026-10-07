import com.pravin.library.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO {
    public void addBook(Book book) {
        String sql = "INSERT INTO Books (title,author,quantity) VALUES (?, ?, ?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getQunatity());

            int rows  = statement.executeUpdate();

            if(rows>0){
                System.out.println("Book added successfully");
            }
        }
        catch(SQLException e){
            System.out.println("Failed to add book!");
            e.printStackTrace();
        }
    }

    public void viewALLBooks() {
        String sql = "SELECT * FROM Books";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\n====== ALL BOOKS ======");
            while (resultSet.next()){
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                int quantity = resultSet.getInt("quantity");

                System.out.println("ID  :  " + id);
                System.out.println("Title :  " + title);
                System.out.println("Author :  " + author);
                System.out.println("Quantity :  " + quantity);
                System.out.println("----------------------------");
            }
        }
        catch(SQLException e){
            System.out.println("Failed to view all books!");
            e.printStackTrace();
        }
    }
    public void searchBook(String keyword) {
        String sql = "SELECT * FROM Books WHERE title LIKE ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()){
                boolean found = false;
                System.out.println("\n====== SEARCH RESULTS ======");

                while (resultSet.next()){
                    found = true;
                    int id = resultSet.getInt("book_id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    int quantity = resultSet.getInt("quantity");

                    System.out.println("ID  :  " + id);
                    System.out.println("Title :  " + title);
                    System.out.println("Author :  " + author);
                    System.out.println("Quantity :  " + quantity);
                    System.out.println("----------------------------");
                }
                if(!found){
                    System.out.println("No books found!");
                }
            }
        }
        catch(SQLException e){
            System.out.println("Failed to search book!");
            e.printStackTrace();
        }
    }
}
