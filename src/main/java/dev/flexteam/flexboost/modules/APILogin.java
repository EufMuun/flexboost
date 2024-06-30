package dev.flexteam.flexboost.modules;
import dev.flexteam.flexboost.ConnectToDB;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class APILogin {
    private ConnectToDB connect = new ConnectToDB();
    private Connection connection;
    public APILogin() throws SQLException {
        connection = connect.getConnection();
    }
    public Map<String, Boolean> loginUser(String email, String password) throws SQLException {
        Map<String, Boolean> JSONResult = new HashMap<String, Boolean>();
        //если email есть в БД
        if(checkIfUserExist(email)){
            PreparedStatement prSt = connection.prepareStatement("SELECT password FROM user_credential WHERE email = ?");
            prSt.setString(1, email);
            //executeQuery возвращает сразу ResultSet (есть ещё просто execute, он возвращает тру или фолс, в зависимости есть ли что-то в ответе)
            ResultSet result = prSt.executeQuery();
            //по умолчанию мы стоим на нулевом результате запроса, а они начинаются с первого.

            result.next();
            //по логике всего приложения, для одного email может быть только один пароль, потому и результат запроса
            //пароля по email будет только один
            String passwordFromDB = result.getString("password");
            if(Objects.equals(password, passwordFromDB)){
                JSONResult.put("Result", true);
                return JSONResult;
            } else{
                JSONResult.put("Result", false);
                return JSONResult;
            }

        }
        //если email нет в БД
        else{
            JSONResult.put("Result", false);
            return JSONResult;
        }
    }

    private boolean checkIfUserExist(String email) throws SQLException {
        Statement statement = connection.createStatement();

        PreparedStatement prSt = connection.prepareStatement("SELECT * FROM user_credentials WHERE email = ?");
        prSt.setString(1, email);

        //Функция execute возвращает ложь, если результат пустой (пользователя с такой почтой нет)
        boolean result = statement.execute(prSt.toString());
        return result;
    }
}
