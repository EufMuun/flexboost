package dev.flexteam.flexboost;
import dev.flexteam.flexboost.objects.userCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class API {

    @PostMapping("/registration")
    public void registerUser(@RequestBody userCredentials userCredentials){

    }
    @PostMapping("/login")
    public void loginUser(){

    }

    //тут тупа для проверки это, пон (оно работает)
    @RequestMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}
