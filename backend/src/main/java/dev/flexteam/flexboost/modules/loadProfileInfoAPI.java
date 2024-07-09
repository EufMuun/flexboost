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
        connection = connect.getConnection();
    }
    public Map<String, String> loadProfileInfo(String profileURL) throws SQLException {
        Map<String, String>JSONResult = new HashMap<String, String>();
        PreparedStatement prSt = connection.prepareStatement("select p.bio, p.description, p.profileurl, ui.displayname, p.banner_pic_link, p.pfplink from flex_schema.user_credentials uc join flex_schema.user_info ui on ui.uid = uc.uid join flex_schema.user_profile up on uc.uid = up.uid join flex_schema.profile p on p.profileid = up.profileid where p.profileurl = ?");
        prSt.setString(1, profileURL);
        ResultSet resultSet = prSt.executeQuery();
        resultSet.next();


        String bio = resultSet.getString("bio");

        String description = resultSet.getString("description");

        String profileURLdb = resultSet.getString("profileurl");

        String displayname = resultSet.getString("displayname");

        String bannerPicLink = resultSet.getString("banner_pic_link");

        String pfplink = resultSet.getString("pfplink");


        JSONResult.put("displayname", displayname);
        JSONResult.put("url_prof", profileURL);
        JSONResult.put("micro_bio", bio);
        JSONResult.put("about_info", description);
        JSONResult.put("img_banner", bannerPicLink);
        JSONResult.put("img_avatar", pfplink);


        return JSONResult;
    }
}
