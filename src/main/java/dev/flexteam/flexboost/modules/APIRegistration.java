package dev.flexteam.flexboost.modules;

import dev.flexteam.flexboost.ConnectToDB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class APIRegistration {
    private ConnectToDB connect = new ConnectToDB();
    private Connection connection;
    public APIRegistration() throws SQLException {
        connection = connect.getConnection();
    }
    //Вначале проверям, если пользователя нет, то проводим все манипуляции по вставке его в таблицу, иначе возвращаем
    //JSON c result: false. При выполнении запроса отлавливаем ошибки, если ошибка есть, то записываем её в JSON и сращу возвращаем
    //если всё норм, возвращается JSON с true
    public Map<String, String> addUserToDB(String email, String password) throws SQLException {
        Map<String, String> JSONResult = new HashMap<String, String>();
        if(!checkIfUserAlreadyExist(email)){
            PreparedStatement prSt = connection.prepareStatement("INSERT INTO user_credentials VALUES (?, ?)");
            prSt.setString(1, email);
            prSt.setString(2, password);
            try {
                prSt.execute(prSt.toString());
            } catch (SQLException e){
                JSONResult.put("Result:", e.toString());
                return JSONResult;
            }
            JSONResult.put("Result:", "true");
        }
        else{
            JSONResult.put("Result:", "false");
        }
        return JSONResult;
    }

    //Проверяем не занят ли логин
    private boolean checkIfUserAlreadyExist(String email) throws SQLException {
        Statement statement = connection.createStatement();

        PreparedStatement prSt = connection.prepareStatement("SELECT * FROM user_credentials WHERE email = ?");
        prSt.setString(1, email);

        //Функция execute возвращает ложь, если результат пустой (пользователя с такой почтой нет)
        boolean result = statement.execute(prSt.toString());
        return result;
    }
}
