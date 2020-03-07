package com.hadwinling.test;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.hadwinling.dao.GoodsDao;
import com.hadwinling.dao.OrderDao;
import com.hadwinling.dao.UserDao;
import com.hadwinling.dao.impl.GoodsDaoImpl;
import com.hadwinling.dao.impl.OrderDaoImpl;
import com.hadwinling.dao.impl.UserDaoImpl;
import com.hadwinling.entity.Goods;
import com.hadwinling.entity.Order;
import com.hadwinling.entity.User;
import com.hadwinling.util.JDBCTemplate;




public class Test {
	public static void main(String[] args) {
		List<Goods>goods = new ArrayList<Goods>();
		Scanner input = new Scanner(System.in);
		OrderDao orderDao = new OrderDaoImpl();
		GoodsDao goodsDao = new GoodsDaoImpl();
		UserDao userDao = new UserDaoImpl();
		boolean flag = true;
		while (flag) {
			registerOrloginMenu();
			int key = input.nextInt();
			switch (key) {
			case 1:
				//登录
				System.out.println("-----------登录界面-----------------");
				System.out.println("请输入账号：");
				String loginaccount = input.next();
				System.out.println("请输入密码：");
				String loginpassword = input.next();
				User loginResult = userDao.login(loginaccount, loginpassword);
//				System.out.println(loginResult);
				if (loginResult!=null) {
					System.out.println("登录成功");
					Menu(loginaccount,orderDao,goodsDao,goods,input);
				}else {
					System.out.println("登录失败，请重新登录");
				}
				break;
			case 2:
				//注册
				System.out.println("-------------注册界面----------------");
				System.out.println("请设置账号：");
				String account = input.next();
				System.out.println("请设置密码：");
				String password = input.next();
				User user = new User(account,password);
				boolean registerResult = userDao.register(user);
				if (registerResult) {
					System.out.println("注册成功");
					System.out.println("亲，您的账号为："+account+",密码为："+password+"请记住账号密码");
				}else {
					System.out.println("注册失败，请重新注册");
				}
				break;
			case 3:
				System.out.println("确认退出？y/n");
				String y = input.next();
				if ("y".equalsIgnoreCase(y)) {
					flag = false;
				}
				break;

			default:
				System.out.println("输入错误，请重新输入");
				break;
			}
		}
	}
	public static void Menu(String account,OrderDao orderDao,GoodsDao goodsDao,List<Goods> goods,Scanner input) {
		boolean flag = true;
		while (flag) {
			printTitle(goodsDao, goods);
			int key = input.nextInt();
			switch (key) {
			case 1:
				//我的订单
				 myOrder(account,orderDao,input);
				break;
			case 2:
				//我要下单
				BuyGood(account,orderDao,goodsDao,goods,input);
				break;
			case 3:
				//查找订单
				checkGood(input,goodsDao);
				break;
			case 4:
				//退出
				flag =false;
				break;
			default:
				System.out.println("输入有误，请重新输入");
				break;
			}
		}
	}
	public static void printTitle(GoodsDao goodsDao,List<Goods> goods) {
		System.out.println("------------惠东数码城---------------------------");
		System.out.println("今日上架");
		System.out.println("编号\t商品\t价格");
		goods =goodsDao.findAllGoods();
		for (Goods good : goods) {
			System.out.println(good);
		}
		System.out.println("请选择：[ 1. 我的订单 ]	[ 2. 我要下单 ]	[ 3. 查询商品 ]	[ 4. 退出 ]      ");
	}
	public static void registerOrloginMenu() {
		System.out.println("-------------欢迎进入惠东数码城----------------");
		System.out.println("1.登录");
		System.out.println("2.注册");
		System.out.println("3.退出");
		System.out.println("-----------------------------------");
	}
	//我要下单
	public static void BuyGood(String account,OrderDao orderDao,GoodsDao goodsDao,List<Goods> goods,Scanner input) {
		System.out.println("------------欢迎订购---------------------------");
		System.out.println("亲，请输入商品编号");
		String goodid = input.next();
		System.out.println("亲，请输入购买数量");
		int buynumber = input.nextInt();
		Goods goods2 =goodsDao.findGoodsByGoodId(goodid);
//		System.out.println(goods2);
		if (goods2!=null) {
		System.out.print("亲，您当前购买的是【"+goods2.getGoodname()+"】，单价是【"+
				goods2.getGoodprice()+"】，合计【"+goods2.getGoodprice()*buynumber+"】"+
				"是否确认下单（Y/N？）");
		String result  = input .next();
		if("y".equalsIgnoreCase(result)) {
			System.out.print("请输入姓名");
			String username= input.next();
			System.out.print("请输入联系方式");
			long tell = input.nextLong();
			System.out.print("请选择派送方式【1.自提 2.邮寄】");
			int op = input.nextInt();
			switch (op) {
			case 1:
				String addressMethod1 = "自提";
				String address1 = "广东惠东";
				Order order1 = new Order(account, username, tell, address1, addressMethod1, goods2.getGoodname(), goods2.getGoodprice(), buynumber);
				boolean addresult1 = orderDao.addOrder(order1);
				if (addresult1) {
					System.out.println("恭喜你，下单成功，请及时到营业厅领取");							
				}else {
					System.out.println("购买失败");
				}
				break;
			case 2:
				String addressMethod2 = "邮寄";
				System.out.println("请输入收货地址");
				String address2= input .next();	
				Order order2 = new Order(account, username, tell, address2, addressMethod2, goods2.getGoodname(), goods2.getGoodprice(), buynumber);
				boolean result2 = orderDao.addOrder(order2);
				if (result2) {							
					System.out.println("恭喜你，下单成功，耐心等待收货");
				}else {
					System.out.println("购买失败");
				}
				break;
			default:
				break;
			}
		}else {
			System.out.println("亲，不好意思，目前库存没有该商品，小二正在加紧备货中....");
		}
		}
	}


	public static void checkGood(Scanner input,GoodsDao goodsDao) {
		System.out.println("请输入商品编号或者名称");
		String goodNameOrId = input.next();
		char first = goodNameOrId.charAt(0);
		Goods goods = null;
		if (first == 'g') {
			goods = goodsDao.findGoodsByGoodId(goodNameOrId);
			System.out.println(goods);
		}else {
			goods = goodsDao.findGoodsByGoodName(goodNameOrId);
			System.out.println(goods);
		}
	}
	public static void myOrder(String account,OrderDao orderDao,Scanner input) {
		List<Order> orders =orderDao.myOrder(account);
		if (orders.size()==0) {
			System.out.println("当前还没有订单，请前往购买页下单");
			return;
		}
		int ordersize = orders.size();
		System.out.println("当前位置》我的订单");
		System.out.println("订单数\t订单编号\t\t\t创建时间\t\t创建人\t联系方式\t联系地址\t派送方式");
		for (int i = 0; i < orders.size(); i++) {
			String orederid = orders.get(i).getOrderid();
			Date date = orders.get(i).getDate();
			String username = orders.get(i).getUsername();
			long tell = orders.get(i).getTell();
			String address = orders.get(i).getAddress();
			String addressMode = orders.get(i).getAddressMode();
			System.out.println((i+1)+"\t"+orederid+"\t"+date+"\t"+username+"\t"+tell+"\t"+address+"\t"+addressMode);
		}
		boolean flag  =true;
		while(flag) {
			System.out.println("请选择：【1.查看订单】【2.返回上一级】【3.退出】");
			int select = input.nextInt();
			switch(select) {
			case 1:
				System.out.println("要查看第几条订单？");
				int checkorder = input.nextInt()-1;
				if (checkorder<0&&checkorder>ordersize) {
					System.out.println("订单数输入有误");
					break;
				}
				System.out.println("当前位置》我的订单》订单详情");
				System.out.println("商品\t\t价格\t\t购买数量\t\t总价");
				String goodname = orders.get(checkorder).getGoodname();
				double goodprice = orders.get(checkorder).getGoodmoney();
				int buynumber = orders.get(checkorder).getBuynumber();
				double allmoney = orders.get(checkorder).getAllmoney();
				System.out.println(goodname+"\t"+goodprice+"\t"+buynumber+"\t"+allmoney);
				return;

			case 2:
				flag = false;
				break;
			case 3:
				System.exit(0);
				break;
			default :
				System.out.println("输入错误，请重新输入");
				break;
			}
		}
	}
}
