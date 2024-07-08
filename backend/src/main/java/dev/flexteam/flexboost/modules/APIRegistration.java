package dev.flexteam.flexboost.modules;



import dev.flexteam.flexboost.ConnectToDB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class APIRegistration {
    private final Connection connection;

    public APIRegistration() throws SQLException {
        ConnectToDB connect = new ConnectToDB();
        connection = connect.getConnection();
    }

    // Вначале проверяем, если пользователя нет, то проводим все манипуляции по вставке его в таблицу, иначе возвращаем
    // JSON c result: false. При выполнении запроса отлавливаем ошибки, если ошибка есть, то записываем её в JSON и сразу возвращаем
    // если всё норм, возвращается JSON с true
    public Map<String, String> addUserToDB(String email, String password) throws SQLException {
        Map<String, String> JSONResult = new HashMap<>();

        // Начало транзакции
        connection.setAutoCommit(false);

        if (!checkIfUserAlreadyExist(email)) {
            try {
                // Вставка в user_credentials
                String insertUserCredentialsSQL = "INSERT INTO flex_schema.user_credentials (email, password) VALUES (?, ?) RETURNING uid";
                PreparedStatement insertUserCredentialsStmt = connection.prepareStatement(insertUserCredentialsSQL);
                insertUserCredentialsStmt.setString(1, email);
                insertUserCredentialsStmt.setString(2, password);
                ResultSet userCredentialsResult = insertUserCredentialsStmt.executeQuery();
                userCredentialsResult.next();
                int userId = userCredentialsResult.getInt("uid");

                // Вставка в user_info
                String insertUserInfoSQL = "INSERT INTO flex_schema.user_info (uid, displayName) VALUES (?, ?)";
                PreparedStatement insertUserInfoStmt = connection.prepareStatement(insertUserInfoSQL);
                insertUserInfoStmt.setInt(1, userId);
                insertUserInfoStmt.setString(2, email);
                insertUserInfoStmt.executeUpdate();

                // Вставка в profile
                String insertProfileSQL = "INSERT INTO flex_schema.profile (profileURL) VALUES (?) RETURNING profileID";
                PreparedStatement insertProfileStmt = connection.prepareStatement(insertProfileSQL);
                insertProfileStmt.setString(1, email);
                ResultSet profileResult = insertProfileStmt.executeQuery();
                profileResult.next();
                int profileId = profileResult.getInt("profileID");

                // Вставка в user_profile
                String insertUserProfileSQL = "INSERT INTO flex_schema.user_profile (uID, profileID) VALUES (?, ?)";
                PreparedStatement insertUserProfileStmt = connection.prepareStatement(insertUserProfileSQL);
                insertUserProfileStmt.setInt(1, userId);
                insertUserProfileStmt.setInt(2, profileId);
                insertUserProfileStmt.executeUpdate();

                // Подтверждение транзакции
                connection.commit();
                JSONResult.put("id", String.valueOf(profileId));
                JSONResult.put("email", email);
                JSONResult.put("author", "true");
                JSONResult.put("result", "true");

            } catch (SQLException e) {
                // Откат транзакции в случае ошибки
                connection.rollback();
                JSONResult.put("Result", "false");
                JSONResult.put("Error", e.getMessage());
            } finally {
                // Возврат к автоматическому подтверждению транзакций
                connection.setAutoCommit(true);
            }
        } else {
            JSONResult.put("Result", "false");
        }

        return JSONResult;
    }

    // Проверяем не занят ли логин
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