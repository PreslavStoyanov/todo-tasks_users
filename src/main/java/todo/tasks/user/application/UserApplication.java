package todo.tasks.user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "todo.tasks.user")
public class UserApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(UserApplication.class, args);
    }

}
