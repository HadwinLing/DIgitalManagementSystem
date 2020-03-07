package com.hadwinling.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

import com.hadwinling.dao.OrderDao;
import com.hadwinling.entity.Order;
import com.hadwinling.entity.User;
import com.hadwinling.util.JDBCTemplate;



public class OrderDaoImpl implements OrderDao{

	@Override
	public boolean addOrder(Order order) {
		// TODO Auto-generated method stub
		System.out.println("正在下单");
		return JDBCTemplate.update(connection->{
			String sql  = "insert into goodorder values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, order.getUseraccount());
			preparedStatement.setString(2, order.getOrderid());
			preparedStatement.setString(3, order.getUsername());
			preparedStatement.setLong(4, order.getTell());
			preparedStatement.setString(5, order.getAddress());
			preparedStatement.setString(6, order.getAddressMode());
			preparedStatement.setString(7, order.getGoodname());
			preparedStatement.setDouble(8, order.getGoodmoney());
			preparedStatement.setInt(9, order.getBuynumber());
			preparedStatement.setDouble(10, order.getAllmoney());
			preparedStatement.setDate(11,  new java.sql.Date(order.getDate().getTime()));
			System.out.println("下单结束");
			return preparedStatement;
		})>0 ?true:false;
	}

	/*
	 * 查找订单
	 * @see com.hadwinling.dao.OrderDao#myOrder(com.hadwinling.entity.Order)
	 */
	@Override
	public List<Order> myOrder(String account) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		return  (List<Order>) JDBCTemplate.query(connection->{
			String sql ="select * from goodorder where useraccount = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account);
			return preparedStatement;
		}, resultSet->{
			Order order1 = null;
			while (resultSet.next()) {
				String orderid = resultSet.getString(2);
				String username = resultSet.getString(3);
				long tell = resultSet.getLong(4);
				String address = resultSet.getString(5);
				String addressMode = resultSet.getString(6);
				String goodname = resultSet.getString(7);
				double  goodprice = resultSet.getDouble(8);
				int buynumber = resultSet.getInt(9);
				double allmoney = resultSet.getDouble(10);
				Date ordertime = resultSet.getDate(11);
				order1 = new Order(orderid, ordertime, username, tell, address, addressMode, goodname, goodprice, buynumber, allmoney);
				orders.add(order1);
			}
			return orders;
		});
	}




}
