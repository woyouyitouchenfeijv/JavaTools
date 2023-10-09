package com.dx.cn.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author w y y t c f j
 * @Description DataSourceLock
 * @Date 2023/10/10
 */
public class DataSourceLock {

    public static void MysqlLock() throws SQLException {
        MysqlDataSource dataSource = DataSourceFactory.createMysqlDataSource();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM your_table WHERE your_condition FOR UPDATE")) {
            // 开始事务
            connection.setAutoCommit(false);
            // 执行查询
            statement.executeQuery();
            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            // 处理异常
        }
    }
}
