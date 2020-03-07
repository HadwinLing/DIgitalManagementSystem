package com.hadwinling.dao;
/*
 * 用户类的Dao
 */

import com.hadwinling.entity.User;

public interface UserDao {
	boolean register(User user);
	User login(String account,String password);
}
