package dev.flexteam.flexboost.modules;

import dev.flexteam.flexboost.ConnectToDB;

import java.sql.*;

public class APIRegistration {
    private ConnectToDB connect = new ConnectToDB();
    private Connection connection;
    public APIRegistration() throws SQLException {
        connection = connect.getConnection();
    }
    public boolean addUserToDB(String email, String password) throws SQLException {
        if(checkIfUserAlreadyExist(email)){
            Statement statement = connection.createStatement();
            PreparedStatement prSt = connection.prepareStatement("INSERT INTO user_credentials VALUES (?, ?)");
            prSt.setString(1, email);
            prSt.setString(2, password);
            statement.execute(prSt.toString());
        }
        else{
            return false;
        }
        return true;
    }
    private boolean checkIfUserAlreadyExist(String email) throws SQLException {
        Statement statement = connection.createStatement();

        PreparedStatement prSt = connection.prepareStatement("SELECT * FROM user_credentials WHERE email = ?");
        prSt.setString(1, email);

        boolean result = statement.execute(prSt.toString());
        return result;
    }
}
