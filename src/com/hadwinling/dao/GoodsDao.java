package com.hadwinling.dao;
/*
 * 商品的处理
 */

import java.util.List;

import com.hadwinling.entity.Goods;

public interface GoodsDao {
	List<Goods> findAllGoods();
	Goods findGoodsByGoodName(String goodname);
	Goods findGoodsByGoodId(String goodid);

}
