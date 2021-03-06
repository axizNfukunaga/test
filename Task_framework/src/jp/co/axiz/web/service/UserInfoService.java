package jp.co.axiz.web.service;

import java.util.List;

import jp.co.axiz.web.entity.UserInfo;

public interface UserInfoService {

	public UserInfo findById(Integer id);

	public List<UserInfo> search (String id, String name, String tel);

	public void register(String name,String tel,String pass);

	public List<UserInfo> findMaxId();

	public int updateName(UserInfo user);

	public int updateTel(UserInfo user);

	public int updatePass(UserInfo user);

	public void deleteById(Integer id);
}
