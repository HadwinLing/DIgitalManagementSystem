package com.hadwinling.dao;

import java.util.List;

import com.hadwinling.entity.Order;

public interface OrderDao {
	//我要下单
	boolean addOrder(Order order);
	//查询订单
	List<Order> myOrder(String account);
}
