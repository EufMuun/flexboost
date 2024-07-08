package dev.flexteam.flexboost.modules;

import dev.flexteam.flexboost.ConnectToDB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class APIRegistration {
    private final Connection connection;
    public APIRegistration() throws SQLException {
        ConnectToDB connect = new ConnectToDB();
        connection = connect.getConnection();
    }
    //Вначале проверям, если пользователя нет, то проводим все манипуляции по вставке его в таблицу, иначе возвращаем
    //JSON c result: false. При выполнении запроса отлавливаем ошибки, если ошибка есть, то записываем её в JSON и сращу возвращаем
    //если всё норм, возвращается JSON с true
    public Map<String, String> addUserToDB(String email, String password) throws SQLException {
        Map<String, String> JSONResult = new HashMap<String, String>();
        Boolean isConValid = connection.isValid(5);
        if (!checkIfUserAlreadyExist(email)) {

            PreparedStatement prSt = connection.prepareStatement("INSERT INTO flex_schema.user_credentials (email,password) VALUES (?, ?)");
            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //String passwordEncoded = passwordEncoder.encode(password);
            prSt.setString(1, email);
            prSt.setString(2, password);
            try {
                prSt.executeQuery();
            } catch (SQLException e){
                JSONResult.put("Result:", "true");
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
    private boolean checkIfUserAlreadyExist(String email) {
        String selectQuery = "SELECT * FROM flex_schema.user_credentials WHERE email = ?";
        try (PreparedStatement prSt = connection.prepareStatement(selectQuery)) {
            prSt.setString(1, email);
            try (ResultSet rs = prSt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
