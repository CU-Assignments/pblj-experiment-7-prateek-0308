import java.sql.*;

public class StudentController {
    static final String DB_URL = "jdbc:sqlite:student.db";

    public StudentController() throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Student (" +
                "StudentID INTEGER PRIMARY KEY," +
                "Name TEXT," +
                "Department TEXT," +
                "Marks REAL)");
        conn.close();
    }

    public void addStudent(Student s) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Student VALUES (?, ?, ?, ?)");
        ps.setInt(1, s.id);
        ps.setString(2, s.name);
        ps.setString(3, s.dept);
        ps.setDouble(4, s.marks);
        ps.executeUpdate();
        conn.close();
    }

    public void showAll() throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL);
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Student");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt(1) + ", Name: " + rs.getString(2) + ", Dept: " + rs.getString(3) + ", Marks: " + rs.getDouble(4));
        }
        conn.close();
    }

    public void updateStudent(Student s) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement("UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?");
        ps.setString(1, s.name);
        ps.setString(2, s.dept);
        ps.setDouble(3, s.marks);
        ps.setInt(4, s.id);
        ps.executeUpdate();
        conn.close();
    }

    public void deleteStudent(int id) throws Exception {
        Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Student WHERE StudentID=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        conn.close();
    }
}