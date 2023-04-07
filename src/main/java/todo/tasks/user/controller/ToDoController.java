package todo.tasks.user.controller;

import org.springframework.web.bind.annotation.*;
import todo.tasks.user.controller.model.ToDoInput;
import todo.tasks.user.repository.model.ToDo;
import todo.tasks.user.repository.model.ToDoStatus;
import todo.tasks.user.service.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class ToDoController
{

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService)
    {
        this.toDoService = toDoService;
    }

    @PostMapping
    public void createToDo(@RequestBody ToDoInput toDoInput)
    {
        toDoService.createToDo(toDoInput);
    }

    @PutMapping("{id}")
    public void updateToDo(@PathVariable int id, @RequestParam ToDoStatus status)
    {
        toDoService.updateToDo(id, status);
    }

    @DeleteMapping("{id}")
    public void deleteToDo(@PathVariable int id)
    {
        toDoService.deleteToDo(id);
    }

    @GetMapping
    public List<ToDo> getUserToDos(@RequestParam int userId)
    {
        return toDoService.getUserToDos(userId);
    }
}
