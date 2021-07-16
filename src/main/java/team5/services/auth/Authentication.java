package team5.services.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;

// 여기서 access modifier 때문에 private method들을 사용하지 못하기때문에 dao를 생성 

@Service
public class Authentication {
	@Autowired
	AuthDao dao;
	
	@Autowired
	Gson gson;
	//private ModelAndView mav = null;
	
	@Autowired
	Encryption enc;
	
	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	public ModelAndView signOutCtl(AuthBean ab) {
		ModelAndView mav = new ModelAndView();
		
		ab.setUCode(session().getAttribute("uCode").toString());
		ab.setPublicIp(session().getAttribute("publicIp").toString());
		ab.setPrivateIp(session().getAttribute("privateIp").toString());
		ab.setMethod(Integer.parseInt(session().getAttribute("method").toString())-1);
		
		
	
		if(dao.selMemberHistory(ab)) {
			if(dao.insMemberHistory(ab)) {
				session().invalidate();
				mav.setViewName("signIn");
				mav.addObject("message","로그아웃성공");
			}
		}else {
			mav.setViewName("dashBoard");
			mav.addObject("message", "로그아웃실패");
		}
		
		
		return mav;
	}
	
	
	//signIn check ctl
	public ModelAndView isAccessCtl(AuthBean ab) {
		boolean check = false;
		ModelAndView mav = new ModelAndView();
		
		String encPassword = dao.getEncryptedPW(ab);
		
		

		if(check = dao.isUcode(ab)) {
			if(enc.matches(ab.getUPassword(), encPassword)) {
				if(check = dao.insMemberHistory(ab)) {
					//ArrayList<UserBean> list = (ArrayList)dao.selMemberInfo(ab);
					mav.setViewName("dashBoard");
					try {
						mav.addObject("umail", enc.aesDecode((dao.selMemberInfo(ab).get(0).getUMail()), ab.getUCode()));
						session().setAttribute("uCode", ab.getUCode());
						session().setAttribute("publicIp", ab.getPublicIp());
						session().setAttribute("privateIp", ab.getPrivateIp());
						session().setAttribute("method", ab.getMethod());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(!check) {
			mav.setViewName("signIn");
			mav.addObject("message","정보확인하세요");
		}
		return mav;
		}
		
		
	
	

	//join member control 
	//중복검사를 한 시점과 실제로 회원가입을 하는 시점이 다르기 때문에 여기서 사실상 한번 더 해줘야함
	// 실제 프로젝트시에는 중복검사 하는순간 가상의 아이디를 만들어주고 회원가입 실패시 
	// 중복 검사한 아이디를 지워주는 방식으로 해야함 
	public ModelAndView insMemberCtl(UserBean ub) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signUp");
		mav.addObject("message","잠시 후 다시 시도해 주세요");
		System.out.println(ub.getUCode());
		
		try {
			ub.setUPassword(enc.encode(ub.getUPassword()));
			ub.setUMail(enc.aesEncode(ub.getUMail(), ub.getUCode()));
			ub.setUName(enc.aesEncode(ub.getUName(), ub.getUCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		if(dao.insMember(ub)) {
			mav.setViewName("signIn");
			mav.addObject("message","회원가입 성공. 다시 로그인해주세요");
		}
		
		
		return mav;
	}
	
	public String isdupId(AuthBean ab) {
		boolean message = false;
		if(!dao.isUcode(ab)) {
			message = true;
		}
		//json 형식 {"message:", "true"}
		return gson.toJson(message);
	}
}
