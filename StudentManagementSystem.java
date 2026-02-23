package New;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManagement sm = new StudentManagement();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    sm.addStudent(new Student(roll, name, age, course));
                    break;

                case 2:
                    sm.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Roll No to Search: ");
                    int sRoll = sc.nextInt();
                    Student found = sm.searchStudent(sRoll);
                    if (found != null) {
                        System.out.println("Student Found: " + found);
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to Update: ");
                    int uRoll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String uName = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int uAge = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Course: ");
                    String uCourse = sc.nextLine();

                    sm.updateStudent(uRoll, uName, uAge, uCourse);
                    break;

                case 5:
                    System.out.print("Enter Roll No to Delete: ");
                    int dRoll = sc.nextInt();
                    sm.deleteStudent(dRoll);
                    break;

                case 6:
                    System.out.println("Exiting... Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}



class Student {
    private int rollNo;
    private String name;
    private int age;
    private String course;

    public Student(int rollNo, String name, int age, String course) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return rollNo + " | " + name + " | " + age + " | " + course;
    }
}



class StudentManagement {
    ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student Added Successfully!");
    }


    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No Students Found!");
            return;
        }
        System.out.println("\n---- Student List ----");
        for (Student s : students) {
            System.out.println(s);
        }
    }


    public Student searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        return null;
    }


    public void updateStudent(int rollNo, String name, int age, String course) {
        Student s = searchStudent(rollNo);
        if (s != null) {
            s.setName(name);
            s.setAge(age);
            s.setCourse(course);
            System.out.println("Student Updated Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }


    public void deleteStudent(int rollNo) {
        Student s = searchStudent(rollNo);
        if (s != null) {
            students.remove(s);
            System.out.println("Student Deleted Successfully!");
        } else {
            System.out.println("Student Not Found!");
        }
    }
}



