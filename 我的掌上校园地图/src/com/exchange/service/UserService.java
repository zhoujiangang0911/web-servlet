package com.exchange.service;

public interface UserService {

	public void userLogin(String loginName,String longinPassword)throws Exception;
	public void userRegister(String registerMid,String  registerMname,String  registerPassword,String  registerMcollege,String  registerMage,String  registerMsex,String  registerMaddress,String  registerMphone, String registerMphone2, String registerMaddress2)throws Exception;
    public void userUpdate(String updateMid,String  updateMname,String  updatePassword,String  updateMcollege,String  updateMage,String  updateMsex,String  updateMaddress,String  updateMphone)throws Exception;
}
