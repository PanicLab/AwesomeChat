package com.github.awesomechat.chat.repositories;

import com.github.awesomechat.chat.models.User;
import com.github.awesomechat.chat.utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;
    private PasswordHasher passwordHasher;

    @Autowired
    UserRepositoryImpl(DataSource ds, PasswordHasher ph) {
        this.dataSource = ds;
        this.passwordHasher = ph;
    }

    @Override
    public boolean isExist(User user) {
        try (Connection conn = dataSource.getConnection()) {

            String sql = "SELECT * FROM CHAT_USERS WHERE NAME = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getFullName().toUpperCase());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isPasswordValid(User user, String password) {

        String pass = "";
        try (Connection conn = dataSource.getConnection()) {

            String sql = "SELECT DISTINCT PASSWORD FROM CHAT_USERS WHERE NAME = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getFullName().toUpperCase());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                pass = rs.getString("PASSWORD");
            }
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return password.equals(pass);
    }

    @Override
    public void createUser(User user, String password) {
        String sql = "INSERT INTO CHAT_USERS (NAME, SALT, PASSWORD) VALUES (?, ?, ?)";

        String salt = passwordHasher.getSalt();

        try (
        Connection con = dataSource.getConnection();
        PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, user.getFullName().toUpperCase());
            statement.setString(2, salt);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
