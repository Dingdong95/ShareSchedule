package team5.services.bean;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


//member table 
@Data
public class UserBean {
	private String uCode;
	private String uPassword;
	private String uName;
	private String uMail;
	
}
