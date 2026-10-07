import com.pravin.library.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class IssueDAO {
    public void issueBook(int bookID, int studentID){
        String checkBook = "SELECT quantity FROM Books WHERE book_id = ?";
        String checkStudent = "SELECT student_id FROM Students WHERE student_id = ?";

        String insertIssue = "INSERT INTO IssueRecords (book_id, student_id, issue_date, return_date) VALUES (?, ?,?, NULL)";

        String updateBook = "UPDATE Books SET quantity = quantity - 1 WHERE book_id = ?";

        try (Connection connection = DBConnection.getConnection()){
            connection.setAutoCommit(false);

            try(PreparedStatement statement = connection.prepareStatement(checkBook)){
                statement.setInt(1, bookID);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()){
                    System.out.println("Book Not Found");
                    connection.rollback();
                    return;
                }
                int quantity = resultSet.getInt("quantity");

                if(quantity <= 0){
                    System.out.println("Book is not available!");
                    connection.rollback();
                    return;
                }
            }
            try(PreparedStatement statement = connection.prepareStatement(checkStudent)) {
                statement.setInt(1, studentID);

                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("Student not found!");
                    connection.rollback();
                    return;
                }
            }
            try(PreparedStatement statement = connection.prepareStatement(insertIssue)){
                statement.setInt(1, bookID);
                statement.setInt(2, studentID);
                statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                statement.executeUpdate();
            }
            try(PreparedStatement statement = connection.prepareStatement(updateBook)) {
                statement.setInt(1, bookID);
                statement.executeUpdate();
            }
                connection.commit();
                System.out.println("Book issued successfully!");
            }
            catch (SQLException e){
                System.out.println("Failed to update book!");
                e.printStackTrace();
            }
        }

        public void returnBook(int bookID, int studentID){
        String checkIssue = "SELECT issue_id FROM IssueRecords " + "WHERE book_id = ? AND student_id = ? AND return_date IS NULL";
        String updateIssue = "UPDATE IssueRecords " + "SET return_date =?" + "WHERE issue_id = ?";
        String updateBook = "UPDATE  Books SET quantity = quantity + 1" + "WHERE book_id = ?";

        Connection connection = null;
        try {
            connection = DBConnection.getConnection();
            connection.setAutoCommit(false);
            int issueID;
            try (PreparedStatement statement = connection.prepareStatement(checkIssue)) {
                statement.setInt(1, bookID);
                statement.setInt(2, studentID);

                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) {
                    System.out.println("No active issue found for this book and student! ");
                    connection.rollback();
                    return;
                }
                issueID = resultSet.getInt("issue_id");
            }
            try(PreparedStatement statement = connection.prepareStatement(updateIssue)){
                statement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                statement.setInt(2, issueID);
                statement.executeUpdate();
            }
            try(PreparedStatement statement = connection.prepareStatement(updateBook)){
                statement.setInt(1, bookID);
                statement.executeUpdate();
            }
            connection.commit();
            System.out.println("Book returned successfully!");
        }
         catch (SQLException e){
            System.out.println("Failed to return  book!");
            if(connection != null){
                try{
                    connection.rollback();
                }
                catch (SQLException rollbackException){
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
         }
        finally {
            if(connection != null){
                try {
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
