package New;
import java.util.ArrayList;
import java.util.Scanner;

public class LIBRARY_MANAGEMENT_SYSTEM {
 public static void main(String[] args) {

     Scanner sc = new Scanner(System.in);
     Library library = new Library();

     while (true) {
         System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
         System.out.println("1. Add Book");
         System.out.println("2. View Books");
         System.out.println("3. Add User");
         System.out.println("4. Borrow Book");
         System.out.println("5. Return Book");
         System.out.println("6. Exit");
         System.out.print("Enter Choice: ");
         int choice = sc.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter Book ID: ");
                 int id = sc.nextInt();
                 sc.nextLine();

                 System.out.print("Enter Book Title: ");
                 String title = sc.nextLine();

                 System.out.print("Enter Author Name: ");
                 String author = sc.nextLine();

                 library.addBook(new Book(id, title, author));
                 break;

             case 2:
                 library.viewBooks();
                 break;

             case 3:
                 System.out.print("Enter User ID: ");
                 int uid = sc.nextInt();
                 sc.nextLine();

                 System.out.print("Enter User Name: ");
                 String uname = sc.nextLine();

                 library.addUser(new User(uid, uname));
                 break;

             case 4:
                 System.out.print("Enter Book ID to Borrow: ");
                 int borrowId = sc.nextInt();
                 library.borrowBook(borrowId);
                 break;

             case 5:
                 System.out.print("Enter Book ID to Return: ");
                 int returnId = sc.nextInt();
                 library.returnBook(returnId);
                 break;

             case 6:
                 System.out.println("Exiting... Thank you!");
                 System.exit(0);

             default:
                 System.out.println("Invalid Choice!");
         }
     }
 }
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    public void borrowBook() { this.isAvailable = false; }
    public void returnBook() { this.isAvailable = true; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " 
                + (isAvailable ? "Available" : "Issued");
    }
}




class User {
    private int userId;
    private String userName;

    public User(int id, String name) {
        this.userId = id;
        this.userName = name;
    }

    public int getUserId() { return userId; }
    public String getUserName() { return userName; }

    @Override
    public String toString() {
        return userId + " | " + userName;
    }
}



class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    // Add book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book Added Successfully!");
    }

    
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No Books Available.");
            return;
        }
        System.out.println("\n---- Book List ----");
        for (Book b : books) {
            System.out.println(b);
        }
    }

   
    public void addUser(User user) {
        users.add(user);
        System.out.println("User Added Successfully!");
    }

   
    public void borrowBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (b.isAvailable()) {
                    b.borrowBook();
                    System.out.println("Book Issued Successfully!");
                } else {
                    System.out.println("Book already issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }



    public void returnBook(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                if (!b.isAvailable()) {
                    b.returnBook();
                    System.out.println("Book Returned Successfully!");
                } else {
                    System.out.println("This book was not issued!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }
}
