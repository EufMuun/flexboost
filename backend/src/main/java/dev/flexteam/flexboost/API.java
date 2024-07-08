package dev.flexteam.flexboost;
import dev.flexteam.flexboost.modules.APILogin;
import dev.flexteam.flexboost.modules.APIRegistration;
import dev.flexteam.flexboost.modules.loadProfileInfoAPI;
import dev.flexteam.flexboost.objects.Post;
import dev.flexteam.flexboost.objects.userCredentials;
import org.springframework.web.bind.annotation.*;
import io.minio.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class API {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/registration")
    public Map<String, String> registerUser(@RequestBody userCredentials userCredentials) throws SQLException {
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        return new APIRegistration().addUserToDB(userEMAIL, userPassword);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody userCredentials userCredentials) throws SQLException{
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        return new APILogin().loginUser(userEMAIL, userPassword);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/loadprofileinfo")
    public Map<String, String> loadprofileinfo(@RequestParam(name = "profileURL") String profileURL) throws SQLException{

        return new loadProfileInfoAPI().loadProfileInfo(profileURL);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/loadpost")
    public Map<String, String> loadPost(@RequestParam("file") MultipartFile file, @RequestBody userCredentials userCredentials){

        return null;
    }


    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello, World! eeeee! eeeeeee";
    }
}
