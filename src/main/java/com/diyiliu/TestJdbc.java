package com.diyiliu;

import com.diyiliu.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Description: TestJdbc
 * Author: DIYILIU
 * Update: 2017-10-20 08:56
 */
public class TestJdbc {

    public static void main(String[] args) {
        SpringUtil.init();

        JdbcTemplate jdbcTemplate = SpringUtil.getBean("jdbcTemplate");

        String sql = "INSERT INTO user(name)VALUES (?)";
        jdbcTemplate.update(sql, new Object[]{"Lily"}, new int[]{Types.VARCHAR});
    }


    public static void test() {
        String sql = "INSERT INTO user(name)VALUES (?)";

        DataSource dataSource = SpringUtil.getBean("dataSource");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, "Tom");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
