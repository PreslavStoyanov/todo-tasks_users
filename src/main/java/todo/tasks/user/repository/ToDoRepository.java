package todo.tasks.user.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import todo.tasks.user.controller.model.ToDoInput;
import todo.tasks.user.repository.model.ToDo;
import todo.tasks.user.repository.model.ToDoStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ToDoRepository
{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final ToDoRowMapper toDoRowMapper;

    public ToDoRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        toDoRowMapper = new ToDoRowMapper();
    }

    public void createToDo(int userId, String name, String summary, ToDoStatus status)
    {
        String sql = """
                INSERT INTO todos(user_id, name, summary, status)
                VALUES (:user_id, :name, :summary, :status)
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("name", name)
                .addValue("summary", summary)
                .addValue("status", status.name());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateToDoStatus(int id, ToDoStatus status)
    {
        String sql = "UPDATE todos SET status = :status WHERE id = :id";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("status", status.name());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public List<ToDo> getUserToDos(int userId)
    {
        String sql = """
                SELECT * FROM todos t
                JOIN users u ON u.id = t.user_id
                WHERE u.id = :user_id
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("user_id", userId);

        return namedParameterJdbcTemplate.query(sql, sqlParameterSource, toDoRowMapper);
    }

    public void deleteToDo(int id)
    {
        String sql = "DELETE FROM todos WHERE id = :id";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public void updateToDo(int id, int userId, String name, String summary, ToDoStatus status)
    {
        String sql = """
                UPDATE todos
                SET user_id = :userId,
                    name = :name,
                    summary = :summary,
                    status = :status
                WHERE id = :id
                """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("user_id", userId)
                .addValue("name", name)
                .addValue("summary", summary)
                .addValue("status", status.name());

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    private static class ToDoRowMapper implements RowMapper<ToDo>
    {

        @Override
        public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            return new ToDo(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("name"),
                    rs.getString("summary"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
        }
    }
}
