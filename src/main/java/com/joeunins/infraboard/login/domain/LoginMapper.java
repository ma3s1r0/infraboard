package com.joeunins.infraboard.login.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface LoginMapper {
	public ArrayList<HashMap<String,Object>> SEL_USER_TB(Map<String,Object> allRequestParams) throws Exception;
	public HashMap<String,Object> SEL_USER_TB_AT_LOGIN(Map<String,Object> allRequestParams) throws Exception;
	public int INS_USER_TB(Map<String,Object> allRequestParams) throws Exception;
}
