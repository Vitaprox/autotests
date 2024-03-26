package partTwo.restLesson15.steps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBSteps {

    private String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
    private String login = "root";
    private String password = "root";

    private int number = 100 + (int) (Math.random() * 10000);
    private String expectedNoteTitle = "Title" + number;
    private String expectedNoteText = "Text" + number;

    public void addRandomNote(int userId){
        executeUpdate("INSERT INTO nfaut.notes (id, user_id, name, content, priority, archive_flg) VALUES(561, "
                + userId + ", '" + expectedNoteTitle + "', '" + expectedNoteText + "', 1, false);");
    }

    public void deleteNotes(int userId) {
        executeUpdate("DELETE FROM nfaut.notes WHERE user_id=" + userId + ";");
    }

//    public int getIdLastNote(int userId) {
//        int id;
//        ResultSet result = executeQuery("SELECT id FROM nfaut.notes WHERE user_id = 6 AND archive_flg = false ORDER BY id DESC;");
//        try {
//            result.next();
//            System.out.println(result.getInt("id"));
//            id = result.getInt("id");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return id;
//    }

    public int getLastNoteId(int userId) {
        int id = -1;
        try {
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT id FROM nfaut.notes WHERE user_id = " + userId + " AND archive_flg = false ORDER BY id DESC LIMIT 1;");
                result.next();
                System.out.println(result.getInt("id"));
                id = result.getInt("id");
                result.close();
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public int getUserId(String userLogin) {
        int id = -1;
        try {
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM nfaut.users WHERE login = '" + userLogin + "';");
                result.next();
                id = result.getInt("id");
                result.close();
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Object> getNoteFields(int noteId) {
        List<Object> fields = new ArrayList<>();

        try {
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT * FROM nfaut.notes WHERE id = " + noteId + ";");
                result.next();
                System.out.println(result.getInt("id"));
                fields.add(result.getString("name"));
                fields.add(result.getString("content"));
                fields.add(result.getString("color"));
                fields.add(result.getInt("priority"));
                fields.add(result.getBoolean("archive_flg"));
                result.close();
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fields;
    }

    private void executeUpdate(String SQL) {
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
