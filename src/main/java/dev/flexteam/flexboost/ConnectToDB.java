package dev.flexteam.flexboost;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectToDB {

    //Сюда надо будет вписать url и пароль созданной БД.
    private final String URL = "";
    private final String USER = "";
    private final String PASSWORD = "";
    private Connection connection;


    public Connection getConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e){
            return null;
        }
        return connection;
    }
}
