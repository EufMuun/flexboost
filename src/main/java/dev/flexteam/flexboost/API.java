package dev.flexteam.flexboost;
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
    public Map<String, Boolean> registerUser(@RequestBody @NotNull userCredentials userCredentials) throws SQLException {
        String userEMAIL = userCredentials.getEmail();
        String userPassword = userCredentials.getPassword();
        boolean isAddSucessed = new APIRegistration().addUserToDB(userEMAIL, userPassword);
        Map<String, Boolean> JSONResult = new HashMap<String, Boolean>();
        JSONResult.put("Result:", isAddSucessed);
        return JSONResult;
    }
    @PostMapping("/login")
    public void loginUser(){

    }

    //тут тупа для проверки это, пон (оно работает)
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello, World! eeeee!";
    }
}
