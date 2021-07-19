package team5.services.bean;

import lombok.Data;


//accesshistory table 
@Data
public class AuthBean {
	private String uCode;
	private String uPassword;
	private int method;
	private String publicIp;
	private String privateIp;
	private String certification;
}
