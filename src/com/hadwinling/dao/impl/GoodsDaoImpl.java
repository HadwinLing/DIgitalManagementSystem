package com.hadwinling.dao.impl;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.hadwinling.dao.GoodsDao;
import com.hadwinling.entity.Goods;
import com.hadwinling.util.JDBCTemplate;


public class GoodsDaoImpl implements GoodsDao {

	//查找所有商品
	@Override
	public List<Goods> findAllGoods() {
		// TODO Auto-generated method stub
		return (List<Goods>) JDBCTemplate.query(connection->{
			String sql ="select *from goods ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeQuery();
			return preparedStatement;
		}, resultSet->{
			List<Goods> goods = new ArrayList<Goods>();
			while (resultSet.next()) {
				String goodid = resultSet.getString(1);
				String goodname = resultSet.getString(2);
				double goodprice = resultSet.getDouble(3);
				Goods good =new Goods(goodid, goodname, goodprice);
				goods.add(good);
			}
			return goods;
		});
	}

	
	@Override
	public Goods findGoodsByGoodName(String goodname) {
		// TODO Auto-generated method stub
		return (Goods) JDBCTemplate.query(connection->{
			String sql = "select* from goods where goodname = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, goodname);
			preparedStatement.executeQuery();
			return preparedStatement;
		}, resultSet->{
			Goods good = null;
			while (resultSet.next()) {
				String goodid = resultSet.getString(1);
				String goodname1 = resultSet.getString(2);
				double goodprice = resultSet.getDouble(3);
				good = new Goods(goodid, goodname1, goodprice);
			}
			return good;
			
		});
		
	
	}

	@Override
	public Goods findGoodsByGoodId(String goodid) {
		// TODO Auto-generated method stub
		return (Goods) JDBCTemplate.query(connection->{
			String sql = "select* from goods where goodid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, goodid);
			preparedStatement.executeQuery();
			return preparedStatement;
		}, resultSet->{
			Goods good = null;
			while (resultSet.next()) {
				String goodid1 = resultSet.getString(1);
				String goodname = resultSet.getString(2);
				double goodprice = resultSet.getDouble(3);
				good = new Goods(goodid1, goodname, goodprice);
			}
			return good;
			
		});
		
	
	}




}
