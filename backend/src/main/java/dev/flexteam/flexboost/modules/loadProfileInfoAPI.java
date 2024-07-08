package dev.flexteam.flexboost.modules;

import dev.flexteam.flexboost.ConnectToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class loadProfileInfoAPI {

    ConnectToDB connect = new ConnectToDB();
    Connection connection;
    public loadProfileInfoAPI() throws SQLException {
        Connection connection = connect.getConnection();
    }
    public Map<String, String> loadProfileInfo(String profileURL) throws SQLException {
        Map<String, String>JSONResult = new HashMap<String, String>();
        PreparedStatement prSt = connection.prepareStatement("select uc.uid, uc.email, p.profileid, up.profileid, p.profileurl, ui.displayname from user_credentials uc join user_info ui on ui.uid = uc.uid join user_profile up on uc.uid = up.uid join profile p on p.profileid = up.profileid where p.profileurl = ?;");
        prSt.setString(1, profileURL);
        ResultSet resultSet = prSt.executeQuery();
        resultSet.next();


        String bio = resultSet.getString("bio");
        resultSet.next();

        String description = resultSet.getString("description");
        resultSet.next();

        String profileURLdb = resultSet.getString("profileurl");
        resultSet.next();

        String displayname = resultSet.getString("displayname");
        resultSet.next();


        JSONResult.put("displayname", displayname);
        JSONResult.put("@ (url_prof)", profileURL);
        JSONResult.put("micro_bio", bio);
        JSONResult.put("about_info", description);


        return JSONResult;
    }
}
