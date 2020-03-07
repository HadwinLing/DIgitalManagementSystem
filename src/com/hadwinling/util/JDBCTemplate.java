package com.hadwinling.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {
	//更新
	public static final int update(PreparedStatementCreator preparedStatementCreator) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result = -1;
		try {
			connection = Dbcp.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement =preparedStatementCreator.create(connection);
			result = preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (connection==null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}finally {
			//关闭资源
			Dbcp.getInstance().close(connection, preparedStatement);
		}
		return result;
	}
	//查询
	public static final Object query(PreparedStatementCreator preparedStatementCreator,ResultSetExtractor resultSetExtractor) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Object result = null;
		try {
			connection = Dbcp.getInstance().getConnection();
			connection.setAutoCommit(false);
			preparedStatement = preparedStatementCreator.create(connection);
			resultSet = preparedStatement.executeQuery();
			result = resultSetExtractor.extractor(resultSet);
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (connection==null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}finally {
			Dbcp.getInstance().close(connection, preparedStatement, resultSet);
		}
		return result;
	}

}
