import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();
        while (true) {
            System.out.println("\n1. Add Student\n2. Show Students\n3. Update Student\n4. Delete Student\n5. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt(); sc.nextLine();
            if (ch == 1) {
                System.out.print("ID: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Dept: ");
                String dept = sc.nextLine();
                System.out.print("Marks: ");
                double marks = sc.nextDouble();
                controller.addStudent(new Student(id, name, dept, marks));
                System.out.println("Student Added.");
            } else if (ch == 2) {
                controller.showAll();
            } else if (ch == 3) {
                System.out.print("ID: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("New Name: ");
                String name = sc.nextLine();
                System.out.print("New Dept: ");
                String dept = sc.nextLine();
                System.out.print("New Marks: ");
                double marks = sc.nextDouble();
                controller.updateStudent(new Student(id, name, dept, marks));
                System.out.println("Student Updated.");
            } else if (ch == 4) {
                System.out.print("ID to delete: ");
                int id = sc.nextInt();
                controller.deleteStudent(id);
                System.out.println("Deleted.");
            } else if (ch == 5) break;
        }
    }
}