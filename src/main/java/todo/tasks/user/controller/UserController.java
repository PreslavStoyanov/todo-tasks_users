package todo.tasks.user.controller;

import org.springframework.web.bind.annotation.*;
import todo.tasks.user.controller.model.UserInput;
import todo.tasks.user.repository.model.User;
import todo.tasks.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
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
    public List<User> getUsers()
    {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable int id)
    {
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserInput userInput)
    {
        userService.updateUser(id, userInput);
    }
}
