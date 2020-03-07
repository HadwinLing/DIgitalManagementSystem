package com.hadwinling.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 订单实体类
 */
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	private String useraccount;
	private static long sid= 1;
	private String orderid;
	private Date date;
	private String username;
	private long tell;
	private String address;
	private String addressMode;
	private String goodname;
	private double goodmoney;
	private int buynumber;
	private double allmoney;
	/**
	 * @param orderid
	 * @param date
	 * @param username
	 * @param tell
	 * @param address
	 * @param addressMode
	 * @param shopname
	 * @param shopmoney
	 * @param buynumber
	 * @param allmoney
	 */
	
	public Order(String useraccount, String username, long tell, String address, String addressMode,
			String shopname, double shopmoney, int buynumber) {
		super();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhMMss");
		this.useraccount = useraccount;
		this.date = new Date();
		this.sid =++sid;
		this.orderid =sdf.format(date)+sid;
		this.username = username;
		this.tell = tell;
		this.address = address;
		this.addressMode = addressMode;
		this.goodname = shopname;
		this.goodmoney = shopmoney;
		this.buynumber = buynumber;
		this.allmoney =shopmoney*buynumber ;
	}
	@Override
	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd hh:ss");
//		orderid = sdf.format(date);
		return  orderid + "\t" + sdf1.format(date) + "\t" + username + "\t" + tell
				+ "\t" + address + "\t" + addressMode ;
	}
	/**
	 * @param orderid
	 * @param date
	 * @param username
	 * @param tell
	 * @param address
	 * @param addressMode
	 * @param goodname
	 * @param goodmoney
	 * @param buynumber
	 * @param allmoney
	 */
	public Order(String orderid, Date date, String username, long tell, String address, String addressMode,
			String goodname, double goodmoney, int buynumber, double allmoney) {
		super();
		this.orderid = orderid;
		this.date = date;
		this.username = username;
		this.tell = tell;
		this.address = address;
		this.addressMode = addressMode;
		this.goodname = goodname;
		this.goodmoney = goodmoney;
		this.buynumber = buynumber;
		this.allmoney = allmoney;
	}
}
