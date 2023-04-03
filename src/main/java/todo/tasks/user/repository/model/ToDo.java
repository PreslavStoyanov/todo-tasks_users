package todo.tasks.user.repository.model;

import java.time.LocalDate;

public record ToDo(int id, int userId, String name, String summary, String status, LocalDate createdAt)
{
}
