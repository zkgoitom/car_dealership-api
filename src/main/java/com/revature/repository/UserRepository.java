package com.revature.repository;

import com.revature.model.User;
import com.revature.util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DAO<User>{
    @Override
    public User create(User user) {
        String sql = "insert into users(first_name, last_name, username, password) values(?, ?, ?, ?)";


        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString( 1, user.getFName());
            stmt.setString(2, user.getLName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());

            int success = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet results = stmt. executeQuery();

            while(results.next()){

                User user = new User();

                user.setFName(results.getString("first_name"));
                user.setLName(results.getString("last_name"));
                user.setUsername(results.getString("username"));
                user.setPassword((results.getString("password")));
                user.setId(results.getInt("id"));

                users.add(user);

            }

        }catch (SQLException e){
            e.printStackTrace();

        }
        return users;
    }

    @Override
    public User getById(int id) {
        return null;
    }
    @Override
    public User update(User user) {
        return null;
    }
    @Override
    public boolean deleteById(int id) {
        return false;
    }

}
