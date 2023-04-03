package todo.tasks.user.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import todo.tasks.user.repository.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository
{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate)
    {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        userRowMapper = new UserRowMapper();
    }

    public void createUser(String name)
    {
        String sql = "INSERT INTO users(name) VALUES(:name)";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("name", name);

        namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    public User getUserById(int id)
    {
        String sql = "SELECT * FROM users WHERE id = :id";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, userRowMapper);
    }

    private static class UserRowMapper implements RowMapper<User>
    {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            return new User(
                    rs.getInt("id"),
                    rs.getString("name"));
        }
    }
}
