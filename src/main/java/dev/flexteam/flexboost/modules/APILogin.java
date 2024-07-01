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
    public Map<String, String> loginUser(String email, String password) throws SQLException {
        Map<String, String> JSONResult = new HashMap<String, String>();
        //если email есть в БД
        if(checkIfUserExist(email)){
            PreparedStatement prSt = connection.prepareStatement("SELECT (pass = crypt('?', pass)) as password_match FROM user_credentials uc WHERE eMail = ?");
            prSt.setString(1, email);
            //executeQuery возвращает сразу ResultSet (есть ещё просто execute, он возвращает тру или фолс, в зависимости есть ли что-то в ответе)

            ResultSet result;
            try {
                result = prSt.executeQuery();
            } catch(SQLException e){
                JSONResult.put("Result", e.toString());
                return JSONResult;
            }
            //по умолчанию мы стоим на нулевом результате запроса, а они начинаются с первого.

            result.next();
            //по логике всего приложения, для одного email может быть только один пароль, потому и результат запроса
            //пароля по email будет только один
            String passwordFromDB = result.getString("password");
            if(Objects.equals(password, passwordFromDB)){
                //если пароль верный
                JSONResult.put("Result", "true");
                return JSONResult;
            } else{
                //если пароль не верный
                JSONResult.put("Result", "false");
                return JSONResult;
            }

        }
        //если email нет в БД
        else{
            JSONResult.put("Result", "false");
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
