package com.hhy.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)    // @GeneratedValue 用于标注主键的生成策略, IDENTITY: 采用数据库ID自增长的方式来自增主键字段。
	@Column(name = "id")
	int id;
	String username;
	String password;
	String salt;
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
