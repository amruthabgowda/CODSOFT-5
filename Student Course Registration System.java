package codsoft;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a course
class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolledStudents;

    // Constructor
    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = 0;
    }

    // Method to display course details
    public void displayCourseInfo() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Capacity: " + capacity);
        System.out.println("Enrolled Students: " + enrolledStudents);
        System.out.println("Available Slots: " + (capacity - enrolledStudents));
        System.out.println();
    }

    // Method to check if registration is possible
    public boolean register() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    // Method to remove a student from the course
    public boolean drop() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }
}

// Class to represent a student
class Student {
    String studentId;
    String name;
    List<Course> registeredCourses;

    // Constructor
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Method to register for a course
    public void registerCourse(Course course) {
        if (course.register()) {
            registeredCourses.add(course);
            System.out.println(name + " successfully registered for " + course.title);
        } else {
            System.out.println("Registration failed: " + course.title + " is full.");
        }
    }

    // Method to drop a course
    public void dropCourse(Course course) {
        if (registeredCourses.remove(course) && course.drop()) {
            System.out.println(name + " successfully dropped " + course.title);
        } else {
            System.out.println("Drop failed: " + course.title + " not found in registered courses.");
        }
    }

    // Method to display registered courses
    public void displayRegisteredCourses() {
        System.out.println(name + "'s Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println(course.title);
        }
        System.out.println();
    }
}

// Main class to manage the registration system
public class studentcourseregistrationsystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize courses
        initializeCourses();

        Scanner scanner = new Scanner(System.in);

        // Menu loop
        while (true) {
            System.out.println("Welcome to the Student Course Registration System");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayCourses();
                    break;
                case 2:
                    registerForCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    displayRegisteredCourses(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Method to initialize courses
    private static void initializeCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of programming.", 30));
        courses.add(new Course("MATH101", "Calculus I", "An introduction to calculus.", 25));
        courses.add(new Course("PHY101", "Physics I", "Fundamentals of physics.", 20));
    }

    // Method to display available courses
    private static void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            course.displayCourseInfo();
        }
    }

    // Method to register for a course
    private static void registerForCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        Student student = findOrCreateStudent(studentId, studentName);

        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);

        if (course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    // Method to drop a course
    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        Student student = findStudent(studentId, studentName);

        if (student != null) {
            System.out.print("Enter Course Code to drop: ");
            String courseCode = scanner.nextLine();
            Course course = findCourseByCode(courseCode);
            if (course != null) {
                student.dropCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to display registered courses
    private static void displayRegisteredCourses(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        Student student = findStudent(studentId, studentName);

        if (student != null) {
            student.displayRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    // Helper method to find or create a student
    private static Student findOrCreateStudent(String studentId, String name) {
        for (Student student : students) {
            if (student.studentId.equals(studentId)) {
                return student;
            }
        }
        Student newStudent = new Student(studentId, name);
        students.add(newStudent);
        return newStudent;
    }

    // Helper method to find a student
    private static Student findStudent(String studentId, String name) {
        for (Student student : students) {
            if (student.studentId.equals(studentId) && student.name.equals(name)) {
                return student;
            }
        }
        return null;
    }

    // Helper method to find a course by code
    private static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}


