package com.hadwinling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/*
 * 实体类
 */

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
	private String goodid;//
	private String goodname;
	private Double goodprice;
	@Override
	public String toString() {
		return  goodid + "\t" + goodname + "\t" + goodprice;
	}
	
	

}
