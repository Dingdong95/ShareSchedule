package team5.services.auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import team5.services.bean.AuthBean;
import team5.services.bean.UserBean;
import team5.services.util.Encryption;
import team5.services.util.ProjectUtility;

// 여기서 access modifier 때문에 private method들을 사용하지 못하기때문에 dao를 생성 

@Service
public class Authentication {
	HttpSession session;

	@Autowired
	AuthDao dao;

	@Autowired
	Gson gson;
	//private ModelAndView mav = null;

	@Autowired
	Encryption enc;

	@Autowired
	ProjectUtility pu;


	public ModelAndView signOutCtl(HttpServletRequest req, AuthBean ab) {
		ModelAndView mav = new ModelAndView();
		boolean check = false;

		try {
			ab.setUCode(enc.aesDecode(ab.getCertification(), "logOut"));
			ab.setBrowser(this.getBrowserInfo(req, "others"));
			if(pu.getAttribute("uCode") != null) {
				//check가 false일동안 = while의 조건이 true일동안,  while 반복문을 돌리는데 
				while(!check) {
					//accesshistory테이블에 로그아웃을 기록해라 > 실제 로그아웃이 되는 시점 
					check = dao.insMemberHistory(ab);
				}
				//여기서 session자체를 지워 그대신 원래 sessionId만 기억해 
				pu.removeAttribute("uCode");
				mav.setViewName("redirect:/");
				mav.addObject("message", "정상 로그아웃 되었습니다.");

			}else {
				mav.setViewName("redirect:/");
				mav.addObject("message", "이미로그아웃되었습니다.");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mav;
	}

	//signIn check ctl
	public ModelAndView signInCtl(HttpServletRequest req, AuthBean ab) {
		System.out.println("여기 SIGNINcTL 들어옴?");
		
		String[] browserList = {"MSIE","Opera","Edge","iPhone","Android","Chrome","Others"};
		String[] openBrowser = new String[7];
		ArrayList<AuthBean> openBrowserBean = new ArrayList<AuthBean>();
		boolean check = false;
		ModelAndView mav = new ModelAndView();
		ab.setBrowser(this.getBrowserInfo(req, "others"));
		String thisBrowser = ab.getBrowser();
		ab.setBrowser(null);


		//db password 
		String encPassword = dao.getEncryptedPW(ab);

		for(int i = 0; i<browserList.length; i++) {
			ab.setBrowser(browserList[i]);
			if(dao.checkBrowserExist(ab)) {
				openBrowser[i] = ab.getBrowser();
			}
		}



		for(int i = 0; i < openBrowser.length; i++) {
			if(openBrowser[i] != null) {
				ab.setBrowser(openBrowser[i]);
				if(dao.checkOtherLogs(ab)) {
					openBrowserBean.add(ab);
				}
			}
		}
		
		


		//if db에 열려있는 계정이 있는지 확인 
		if(openBrowserBean.size() != 0) {
			System.out.println(openBrowserBean.get(0).getBrowser());
			ab.setMethod(-1);
			ab.setBrowser(openBrowserBean.get(0).getBrowser());
			dao.insMemberHistory(ab);
		}

		ab.setMethod(1);
		ab.setBrowser(thisBrowser);
		//System.out.println(ab.getBrowser() + ab.getMethod() + ab.getPrivateIp() + ab.getPublicIp() + ab.getUCode());
		//현재 로그인한 브라우저가 어떤것인지 알려줌 

		try {
			//그냥 새로 고침 할때 SIGNiN을 다시 요청해서 여기서 먼저 SESSION이 살아 있는지 확인 하고 넘어가 줘야함 
			// F5를 하면 계속 FORM.SUBMIT을 때려서 MAV는 초기화 되고 SESSION은 남아서 여기서 계속 다시 넣어 줘야함 
			//session에 아이디가 있는지 없는지 확인 
			//전에 로그인한 기록이 없으면 통과 
			System.out.println(ab.getBrowser() + ab.getUCode() + ab.getPrivateIp() + ab.getPublicIp() + ab.getMethod());
			if((pu.getAttribute("uCode") != null) && !dao.checkOtherLogs(ab)) {	
				pu.removeAttribute("uCode");
				mav.setViewName("redirect:/");
			}else {
				//니가 입력한 비밀번호가 db에 있을경우 통과 > 제대로된 비밀번호인지 확인 및 회원가입 확인 
				if(check = (encPassword !=null)) {
					//사용자가 입력한 비밀번호와 db에 비밀번호의 일치여부 확인 
					if(check = enc.matches(ab.getUPassword(), encPassword)) {
						//로그인 = acccesshistory table에 기록 남김 
						if(check = dao.insMemberHistory(ab)) {
							//처음 세션 생성 
							// session에 아이디와  로그아웃여부를 저장 
							pu.setAttribute("uCode", ab.getUCode());
							//여기서 sessionId를 넘겨줘 (최초 로그인) 
							mav.setViewName("redirect:/");
						}
					}
				}else if(!check) {
					mav.setViewName("signIn");
					mav.addObject("message","정보확인하세요");
				}
			}


		}catch(Exception e){
			e.printStackTrace();
		}


		//dao.checkOtherLogs(ab);
		//arraylist 들음 


		return mav;
	}


	public ModelAndView signInForm() {
		ModelAndView mav = new ModelAndView();
		try {
			//로그인을 이미해서 session에 uCode가 들어가는 경우 
			if(pu.getAttribute("uCode") != null) {
				mav.setViewName("dashBoard");
				mav.addObject("certification", enc.aesEncode((String)pu.getAttribute("uCode"),"logOut"));
			}else {
				mav.setViewName("signIn");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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


	protected String getBrowserInfo(HttpServletRequest req, String browser) {
		try {
			String browserInfo = req.getHeader("User-Agent").toLowerCase(); // 사용자 User-Agent 값 얻기

			if (browserInfo != null) {
				if (browserInfo.indexOf("trident") > -1) {
					browser = "MSIE";
				} else if (browserInfo.indexOf("opera") > -1) {
					browser = "Opera";
				} else if(browserInfo.indexOf("edg") > -1){
					browser = "Edge";
				} else if (browserInfo.indexOf("iPhone") > -1
						&& browserInfo.indexOf("mobile") > -1) {
					browser = "iPhone";
				} else if (browserInfo.indexOf("android") > -1
						&& browserInfo.indexOf("mobile") > -1) {
					browser = "Android";
				}else if (browserInfo.indexOf("chrome") > -1) {
					browser = "Chrome";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return browser;
	}



}
