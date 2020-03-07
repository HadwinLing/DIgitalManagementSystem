package com.hadwinling.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hadwinling.dao.UserDao;

import com.hadwinling.entity.User;
import com.hadwinling.util.JDBCTemplate;
import com.hadwinling.util.PreparedStatementCreator;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return JDBCTemplate.update(connection->{
			String sql = "insert into user values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, user.getPassword());
			return preparedStatement;
		})>0? true: false;
	}

	@Override
	public User login(String account,String password) {
		// TODO Auto-generated method stub
		return (User) JDBCTemplate.query(connection->{
//			System.out.println("正在登录中。。。");
			String sql = "select * from  user where account = ? and password = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account);
			preparedStatement.setString(2, password);
			return preparedStatement;
		},resultSet->{
			User user = null;
			if (resultSet.next()) {
				String account1 = resultSet.getString(1);
				String password1 = resultSet.getString(2);
				user = new User(account1, password1);
			}
			return user;
		});
		
				
		
	}


}
