import com.pravin.library.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {
    public void addStudent(Student student) {
        String sql = "INSERT INTO students(name, email) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Student added successfully");
            }
        } catch (SQLException e) {
            System.out.println("Failed to add student");
            e.printStackTrace();
        }
    }

    public void viewStudent() {
        String sql = "SELECT * FROM students";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\n====== ALL STUDENTS ======");

            boolean found = false;

            while (resultSet.next()){
                found = true;

                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println("Student ID : " + id);
                System.out.println("Name       : " + name);
                System.out.println("Email      : " + email);
                System.out.println("-------------------------");
            }
            if (!found) {
                System.out.println("No student found");
            }
        }
        catch (SQLException e){
            System.out.println("Failed to view student");
            e.printStackTrace();
        }
    }
}