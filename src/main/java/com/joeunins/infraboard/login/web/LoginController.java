package com.joeunins.infraboard.login.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joeunins.infraboard.login.domain.LoginMapper;

@Controller
public class LoginController {
	
	@Autowired
	private LoginMapper loginMapper;
	
	@RequestMapping("/")
	private String login(@RequestParam Map<String,Object> allRequestParams, Model model) throws Exception {
		
		return "login/login";
	}
	
	@RequestMapping(value="/login_proc", method=RequestMethod.POST)
	private String login_proc(@RequestParam Map<String,Object> allRequestParams, Model model, HttpSession sess, RedirectAttributes rttr) throws Exception {
		
		String user_id = allRequestParams.get("user_id").toString();
		String user_pw = allRequestParams.get("user_pwd").toString();
		
		HashMap<String,Object> _hm = new HashMap();
		_hm.put("user_id",user_id);
		HashMap<String,Object> resultMap = loginMapper.SEL_USER_TB_AT_LOGIN(_hm);
		
		if(resultMap.isEmpty()) {
			rttr.addFlashAttribute("msg","로그인에 실패하였습니다. 아이디를 확인해주세요.");
			return "redirect:/";
		}else{
			String cmp_pw = resultMap.get("PASSWORD").toString();
			boolean LOGIN_FLAG = cmp_pw.equals(user_pw);
			LOGIN_FLAG = true;
			if(LOGIN_FLAG) {
				//로그인 성공.
				
				sess.setAttribute("LOGIN_ID", resultMap.get("USER_ID"));
				sess.setAttribute("LOGIN_NAME", resultMap.get("NAME"));
				sess.setAttribute("LOGIN_DEPT", resultMap.get("DEPT"));
				sess.setAttribute("LOGIN_DEPT_TEAM", resultMap.get("DEPT_TEAM"));
				sess.setAttribute("LOGIN_AUTH", resultMap.get("AUTH"));
				
				//sess.setAttribute("LOGIN_LOGIN_TIME", resultMap.get("LOGIN_TIME"));
				
				return "redirect:/infraboard/home";
			}else {
				//로그인 실패
				rttr.addFlashAttribute("msg","비밀번호가 틀립니다.");
				return "redirect:/";
			}
		}
	}
	
	@RequestMapping("/register")
	private String register(@RequestParam Map<String,Object> allRequestParams, Model model) throws Exception {
		
		return "login/register";
	}
	
	@RequestMapping(value="/register_proc", method=RequestMethod.POST)
	private String register_proc(@RequestParam Map<String,Object> allRequestParams, Model model, RedirectAttributes rttr) throws Exception {
		
		System.out.println(allRequestParams);
		
		String user_id = allRequestParams.get("user_id").toString();
		String user_pw = allRequestParams.get("user_pw").toString();
		String user_name = allRequestParams.get("user_name").toString();
		String user_pos = allRequestParams.get("user_pos").toString();
		String user_dep = allRequestParams.get("user_dep").toString();
		String user_team = allRequestParams.get("user_team").toString();
		String user_email = allRequestParams.get("user_email").toString();
		
		HashMap<String,Object> _hm = new HashMap();
		_hm.put("user_id",user_id);
		_hm.put("password",user_pw);
		_hm.put("name",user_name);
		_hm.put("position",user_pos);
		_hm.put("dept",user_dep);
		_hm.put("dept_team",user_team);
		_hm.put("email",user_email);
		loginMapper.INS_USER_TB(_hm);
		
		rttr.addFlashAttribute("msg","가입이 완료되었습니다. 로그인해 주세요.");
		
		return "redirect:/";
	}
	
}
