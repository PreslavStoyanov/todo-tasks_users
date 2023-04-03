package todo.tasks.user.controller.model;

import todo.tasks.user.repository.model.ToDoStatus;

public record ToDoInput(int userId, String name, String summary, ToDoStatus status)
{
}
