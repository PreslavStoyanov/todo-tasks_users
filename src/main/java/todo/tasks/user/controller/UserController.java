package todo.tasks.user.controller;

import org.springframework.web.bind.annotation.*;
import todo.tasks.user.repository.model.User;
import todo.tasks.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController
{

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestParam String name)
    {
        userService.createUser(name);
    }

    @GetMapping
    public User getUserById(@RequestParam int id)
    {
        return userService.getUserById(id);
    }
}
