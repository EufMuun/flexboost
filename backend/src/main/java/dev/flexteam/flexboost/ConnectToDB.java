package dev.flexteam.flexboost;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectToDB {

    //Сюда надо будет вписать url и пароль созданной БД.
    private final String URL = "jdbc:postgresql://db:5432/flexdb";
    private final String USER = "flexdev";
    private final String PASSWORD = "flexdev";
    private Connection connection;


    public Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
