package com.joeunins.infraboard.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeunins.infraboard.login.domain.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginMapper loginMapper;
	
	@Override
	public ArrayList<HashMap<String, Object>> SEL_USER_TB(Map<String, Object> allRequestParams) throws Exception {
		// TODO Auto-generated method stub
		return loginMapper.SEL_USER_TB(allRequestParams);
	}

	@Override
	public int INS_USER_TB(Map<String, Object> allRequestParams) throws Exception {
		// TODO Auto-generated method stub
		return loginMapper.INS_USER_TB(allRequestParams);
	}

	@Override
	public HashMap<String, Object> SEL_USER_TB_AT_LOGIN(Map<String, Object> allRequestParams) throws Exception {
		// TODO Auto-generated method stub
		return loginMapper.SEL_USER_TB_AT_LOGIN(allRequestParams);
	}

}
