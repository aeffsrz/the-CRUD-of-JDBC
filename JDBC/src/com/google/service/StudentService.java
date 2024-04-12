package com.google.service;

import com.google.model.Student;
import com.google.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentService {
    public static Connection connection;
    public static PreparedStatement preparedStatement;


    public static ArrayList<Student> readAll() {
        ArrayList<Student> arrayList = new ArrayList<>();
        try {
            connection = JDBCUtils.getConnection();//确定路线
            String sql = "select * from info";
            preparedStatement = connection.prepareStatement(sql);//货物到达

            ResultSet resultSet = preparedStatement.executeQuery(sql);//用resultSet分拣货物

            while (resultSet.next()) {
                arrayList.add(new Student(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3)));
            }

            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    public static Student readById(String id) {
        Student student = new Student();

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from info where id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student.setId(resultSet.getString(1));
                student.setAge(resultSet.getInt(2));
                student.setName(resultSet.getString(3));
            }
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return student;
    }

    public static boolean create(String id, int age, String name) {

        try {
            connection = JDBCUtils.getConnection();

            // 检查是否已经存在相同的 id
            String checkSql = "select id from info where id = ?";
            preparedStatement = connection.prepareStatement(checkSql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Duplicate entry for id!1");
                return false;

            } else {
                // 如果不存在相同的 id，则插入新数据
                String insertSql = "insert into info (id, age, name) values (?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertSql);
                preparedStatement.setString(1, id);
                preparedStatement.setInt(2, age);
                preparedStatement.setString(3, name);


            }

            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }


    public static boolean deleteById(String id) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from info where id= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public static boolean update(String id, int age, String name) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update info set age=?, name=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

}
