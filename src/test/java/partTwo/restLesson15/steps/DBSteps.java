package partTwo.restLesson15.steps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBSteps {

    private String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
    private String login = "root";
    private String password = "root";
    private int userId;
    private int randomNumber = 400 + (int) (Math.random() * 10000);
    private String randomNoteTitle = "Title" + randomNumber;
    private String randomNoteText = "Text" + randomNumber;

    private Connection connection;
    private Statement statement;

    private int getMaxId(String table) {
        int id = -1;
        createConnectionAndStatement(url, login, password);
        try {
            ResultSet result = statement.executeQuery("SELECT MAX(id) AS max_id FROM nfaut." + table + ";");
            result.next();
            id = result.getInt("max_id");
            result.close();
            closeConnectionAndStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    public void createUserWithStandardPassword(String login) {
        userId = getMaxId("users") + 1;
        executeUpdate("INSERT INTO nfaut.users" +
                "(id, login, password)" +
                "VALUES(" + userId + ", '" + login + "', '$2a$10$iJM3uo1MxeVD4qq92yoBpOG3WqHDqyWGth9jb4mhlOK1oqnv20ARq');");
        setUserRole(2);
    }

    public void setUserRole(int role) {
        executeUpdate("INSERT INTO nfaut.users_roles" +
                "(id, user_id, role_id)" +
                "VALUES(nextval('nfaut.users_roles_s'::regclass), " + userId + ", " + role + ");");
    }



    public void addRandomNote(int userId) {
        int noteId = getMaxId("notes") + 1;
        executeUpdate("INSERT INTO nfaut.notes (id, user_id, name, content, priority, archive_flg) VALUES(" +
                noteId + ", " + userId + ", '" + randomNoteTitle + "', '" + randomNoteText + "', 1, false);");
    }

    public void deleteNotes(int userId) {
        executeUpdate("DELETE FROM nfaut.notes WHERE user_id=" + userId + ";");
    }


    public int getLastNoteId(int userId) {
        int id = -1;
        createConnectionAndStatement(url, login, password);
        try {
            ResultSet result = statement.executeQuery("SELECT id FROM nfaut.notes WHERE user_id = " + userId + " AND archive_flg = false ORDER BY id DESC LIMIT 1;");
            result.next();
            id = result.getInt("id");
            result.close();
            closeConnectionAndStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    public int getUserId(String userLogin) {
        int id = -1;
        createConnectionAndStatement(url, login, password);
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM nfaut.users WHERE login = '" + userLogin + "';");
            result.next();
            id = result.getInt("id");
            result.close();
            closeConnectionAndStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    public List<Object> getNoteFields(int noteId) {
        List<Object> fields = new ArrayList<>();
        createConnectionAndStatement(url, login, password);
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM nfaut.notes WHERE id = " + noteId + ";");
            result.next();
            fields.add(result.getString("name"));
            fields.add(result.getString("content"));
            fields.add(result.getString("color"));
            fields.add(result.getInt("priority"));
            fields.add(result.getBoolean("archive_flg"));
            result.close();
            closeConnectionAndStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fields;
    }

    private void executeUpdate(String SQL) {
        createConnectionAndStatement(url, login, password);
        try {
            statement.executeUpdate(SQL);
            closeConnectionAndStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void createConnectionAndStatement(String url, String login, String password) {
        try {
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeConnectionAndStatement() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
