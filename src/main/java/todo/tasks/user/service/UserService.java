package todo.tasks.user.service;

import org.springframework.stereotype.Service;
import todo.tasks.user.controller.model.UserInput;
import todo.tasks.user.repository.UserRepository;
import todo.tasks.user.repository.model.User;

import java.util.List;

@Service
public class UserService
{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void createUser(String name)
    {
        userRepository.createUser(name);
    }

    public User getUserById(int id)
    {
        return userRepository.getUserById(id);
    }

    public List<User> getUsers()
    {
        return userRepository.getUsers();
    }

    public void updateUser(int id, UserInput userInput)
    {
        userRepository.updateUserById(id, userInput.name());
    }
}
