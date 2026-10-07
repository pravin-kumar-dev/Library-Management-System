import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BookDAO bookDAO = new BookDAO();
        StudentDAO studentDAO = new StudentDAO();
        IssueDAO issueDAO = new IssueDAO();

        while (true) {
            System.out.println("\n====== LIBRARY MANAGEMENT SYSTEM ======");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Student");
            System.out.println("5. View Students");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book title: ");
                    String title = input.nextLine();

                    System.out.print("Enter Book author name: ");
                    String author = input.nextLine();

                    System.out.print("Enter quantity: ");
                    int quantity = input.nextInt();

                    Book book = new Book(title, author, quantity);
                    bookDAO.addBook(book);

                    break;

                case 2:
                    bookDAO.viewALLBooks();
                    break;

                 case 3:
                     System.out.print("Enter book title to search: ");
                     String keyword = input.nextLine();
                     bookDAO.searchBook(keyword);
                     break;

                  case 4:
                      System.out.print("Enter student name: ");
                      String name = input.nextLine();

                      System.out.print("Enter student email: ");
                      String email = input.nextLine();

                   Student student = new Student(name,email);
                   studentDAO.addStudent(student);
                    break;

                   case 5:
                       studentDAO.viewStudent();
                        break;

                   case 6:
                       System.out.print("Enter book ID");
                       int bookID = input.nextInt();

                       System.out.print("Enter student ID");
                       int studentID = input.nextInt();
                       input.nextLine();

                       issueDAO.issueBook(bookID,studentID);
                       break;

                    case 7:
                        System.out.print("Enter book ID: ");
                        int returnBookID = input.nextInt();

                        System.out.print("Enter student ID: ");
                        int returnStudentID = input.nextInt();
                        input.nextLine();

                        issueDAO.returnBook(returnBookID,returnStudentID);
                        break;

                case 8:
                    System.out.println("Thank you for using Library Management System!");
                    input.close();
                    return;

                    default:
                        System.out.println("Invalid choice. Try again!");
            }

        }

    }
}
