package com.hadwinling.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String account;
	private String password;

}
