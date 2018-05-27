package entity;

import java.util.Date;

public class User {

	String name;
	Date loginTime;
	String ip;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Override
	public boolean equals(Object obj){
		
		if(null == obj || !(obj instanceof User) ){
			return false;
		}
		
		return name.equalsIgnoreCase(((User)obj).getName());
		
	}
}
