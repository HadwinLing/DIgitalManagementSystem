package com.hadwinling.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class Dbcp {
	public static final String CONFIG_FILEPATH ="Digitalconfig.properties";
	private static Properties properties;
	private static Dbcp dbcp;
	
	static {
		try {
			properties=new Properties();
			InputStream is=Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILEPATH);
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	BasicDataSource dataSource=null;
	private Dbcp() {
		//创建Commons-DBCP连接池对象
		dataSource=new BasicDataSource();
		//设置数据源参数
		dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
		dataSource.setUrl(properties.getProperty("jdbc.url"));
		dataSource.setUsername(properties.getProperty("jdbc.username"));
		dataSource.setPassword(properties.getProperty("jdbc.password"));
		//设置连接池参数
		dataSource.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initialSize")));//初始化容量
		dataSource.setMaxTotal(Integer.parseInt(properties.getProperty("jdbc.maxTotal")));//最大活动数
		dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("jdbc.maxIdle")));//最大空闲数
		dataSource.setMaxWaitMillis(Long.parseLong(properties.getProperty("jdbc.maxWaitMillis")));//最长等待时间
	}
	public static Dbcp getInstance() {
		if(dbcp==null) {
			dbcp=new Dbcp();
		}
		return dbcp;
		
	}
	
	
	//返回数据源对象
	public BasicDataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * 连接池获取连接对象
	 * @throws SQLException 
	 */
	public Connection getConnection() throws SQLException {
		return dataSource==null?null:dataSource.getConnection();
	}
	/**
	 * 归还连接
	 */
	public void close(Connection conn,Statement psmt,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(conn, psmt);
	}
	public void close(Connection conn,Statement psmt) {
		if(psmt!=null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
