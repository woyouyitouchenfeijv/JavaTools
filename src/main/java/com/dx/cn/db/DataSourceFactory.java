package com.dx.cn.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.jdbc.MysqlXADataSource;

import javax.sql.XADataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author w y y t c f j
 * @Description CreateDataSource
 * @Date 2023/10/10
 */
public class DataSourceFactory {

    public static void main(String[] args) throws SQLException {
        MysqlDataSource dataSource = createMysqlDataSource();

        // 使用数据源建立数据库连接
        try (Connection connection = dataSource.getConnection()) {
            // 在这里执行数据库操作
            // 创建 Statement 对象
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM routes";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                // 从结果集中获取数据
                String id = resultSet.getString("id");
                String path = resultSet.getString("path");

                // 在这里处理数据
                System.out.println("ID: " + id + ", path: " + path);
            }
            // 关闭资源
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            // 处理异常
            e.printStackTrace();
        }
    }
    public static MysqlDataSource createMysqlDataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();

        // 设置连接属性
        dataSource.setServerName("localhost"); // MySQL 服务器主机名
        dataSource.setPort(3306); // MySQL 服务器端口号
        dataSource.setDatabaseName("ceshi"); // 数据库名称
        dataSource.setUser("root"); // 数据库用户名
        dataSource.setPassword("rootroot"); // 数据库密码

        // 可选：设置其他连接属性
        dataSource.setUseSSL(false); // 是否使用 SSL 连接
        dataSource.setCharacterEncoding("UTF-8"); // 字符编码

        return dataSource;
    }

    // 获取第一个数据库的 XA 数据源
    public static XADataSource createXADataSource(String serverName, int port, String databaseName, String user, String password ) throws SQLException {
        MysqlXADataSource xaDataSource = new MysqlXADataSource();

        // 设置连接属性
        xaDataSource.setServerName(serverName); // MySQL 服务器主机名
        xaDataSource.setPort(port); // MySQL 服务器端口号
        xaDataSource.setDatabaseName(databaseName); // 数据库名称
        xaDataSource.setUser(user); // 数据库用户名
        xaDataSource.setPassword(password); // 数据库密码

        // 设置其他连接属性
        xaDataSource.setUseSSL(false); // 是否使用 SSL 连接
        xaDataSource.setCharacterEncoding("UTF-8"); // 字符编码

        return xaDataSource;
    }
}
