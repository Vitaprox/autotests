package pageObject16.steps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBSteps {

    private int number = 100 + (int) (Math.random() * 10000);
    private String expectedNoteTitle = "Title" + number;
    private String expectedNoteText = "Text" + number;

    public void addRandomNote(int userId){
        executeQuery("INSERT INTO nfaut.notes (id, user_id, name, content, priority, archive_flg) VALUES(561, "
                + userId + ", '" + expectedNoteTitle + "', '" + expectedNoteText + "', 1, false);");
    }

    public void deleteNotes(int userId) {
        executeQuery("DELETE FROM nfaut.notes WHERE user_id=" + userId + ";");
    }

    private void executeQuery(String SQL) {
        String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
        String login = "root";
        String password = "root";
        try {
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(SQL);
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
