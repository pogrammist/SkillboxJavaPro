import java.sql.*;

public class Start {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String password = "runmysql";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CONCAT(name, ' - ', count(*)/12) " +
                    "AS \"subscription average count of courses\" " +
                    "FROM Courses JOIN Subscriptions ON id = course_id group by month(course_id), name");
            while (resultSet.next()) {
                String courseName = resultSet.getString("subscription average count of courses");
                System.out.println(courseName);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
