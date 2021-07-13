package team5.services.bean;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserBean {
	private String uCode;
	private String uPassword;
	private ArrayList<String> uInfo;
	@DateTimeFormat(pattern ="yyyy-mm-dd")
	private Date date;
}
