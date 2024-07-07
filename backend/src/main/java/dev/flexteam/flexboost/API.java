package dev.flexteam.flexboost;
import dev.flexteam.flexboost.modules.APILogin;
import dev.flexteam.flexboost.modules.APIRegistration;
import dev.flexteam.flexboost.objects.Post;
import dev.flexteam.flexboost.objects.userCredentials;
import org.springframework.web.bind.annotation.*;
import io.minio.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class API {

    @PostMapping("/registration")
    public Map<String, String> registerUser(@RequestBody userCredentials userCredentials) throws SQLException {
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        Map<String, String> JSONResult = new HashMap<String, String>();
        return new APIRegistration().addUserToDB(userEMAIL, userPassword);
    }
    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody userCredentials userCredentials) throws SQLException{
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        return new APILogin().loginUser(userEMAIL, userPassword);
    }

    @PostMapping("/loadpost")
    public Map<String, String> loadPost(@RequestParam("file") MultipartFile file, @RequestBody userCredentials userCredentials){

        return null;
    }


    //тут тупа для проверки это, пон (оно работает)
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello, World! eeeee! eeeeeee";
    }
}
