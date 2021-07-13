package team5.services.bean;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


//accesshistory table 
@Data
public class AuthBean {
	private String uCode;
	private String uPassword;
	private String method;
	private String publicIp;
	private String privateIp;
}
