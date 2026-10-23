import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Prepared   Statement;

public class Main {

    public static void main(String[] args) {

        String url = System.getenv().getOrDefault(
                "DB_URL",
                "jdbc:mysql://localhost:3306/test"
        );

        String user = System.getenv().getOrDefault(
                "DB_USER", "root"
        );

        String password = System.getenv().getOrDefault(
                "DB_PASSWORD", "admin@2026"
        );

        try {
            
            Connection con = DriverManager.getConnection(
                    url, user, password
            );

            System.out.println("Database connected successfully!");

            String insertSQL =
                    "INSERT INTO Student (Student_ID, STUDENT_NAME) VALUES (?, ?)";

            PreparedStatement insertStmt =
                    con.prepareStatement(insertSQL);

            insertStmt.setInt(1, 6);
            insertStmt.setString(2, "Dhruv");

            int insertResult = insertStmt.executeUpdate();

            if (insertResult > 0) {
                System.out.println("Student inserted successfully!");
            }

            String updateSQL =
                    "UPDATE Student SET STUDENT_NAME = ? WHERE Student_ID = ?";

            PreparedStatement updateStmt =
                    con.prepareStatement(updateSQL);

            updateStmt.setString(1, "Dhruv Jotaniya");
            updateStmt.setInt(2, 6);

            int updateResult = updateStmt.executeUpdate();

            if (updateResult > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("No student found to update.");
            }

            insertStmt.close();
            updateStmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}