package dev.flexteam.flexboost;
import dev.flexteam.flexboost.modules.APILogin;
import dev.flexteam.flexboost.modules.APIRegistration;
import dev.flexteam.flexboost.objects.userCredentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class API {

    @PostMapping("/registration")
    public Map<String, String> registerUser(@RequestBody @NotNull userCredentials userCredentials) throws SQLException {
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        return new APIRegistration().addUserToDB(userEMAIL, userPassword);
    }
    @PostMapping("/login")
    public Map<String, Boolean> loginUser(@RequestBody @NotNull userCredentials userCredentials) throws SQLException{
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        return new APILogin().loginUser(userEMAIL, userPassword);
    }

    //тут тупа для проверки это, пон (оно работает)
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello, World! eeeee!";
    }
}
