package com.keen.ma.db.test;

import java.util.List;
import java.util.UUID;

import android.test.AndroidTestCase;

import com.keen.ma.bean.UserBean;
import com.keen.ma.db.impl.UserDaoImpl;

/**
 * ���ݿ⹦�ܵ�Ԫ����
 * @author KEEN
 * @lastChangeTime 2014��8��25�� ����1:03:41
 */
public class DBTest extends AndroidTestCase {

	private String getUUID() {
		return UUID.randomUUID().toString();
	}

	public void testAdd() throws Exception {
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		String userId = getUUID();
		UserBean userBean = new UserBean(userId, "��������", "aassddff",
				"18325451125", "sddfsdf@163.com", "https://www.baidu.com",
				172.3025, 112.2541);
		userTest.add(userBean);
		System.out.println("===========��ӵ����û�=================="+userBean);
	}

	public void deleteById() throws Exception {
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		userTest.deleteById("12345678");
	}

	public void deleteByPhone() throws Exception {
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		userTest.deleteByPhone("12345678");
	}

	public void deleteByEmail() throws Exception {
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		userTest.deleteByEmail("12345678");
	}
	
	public void testFindById(){
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		UserBean userBean = userTest.findById("9761edcf-2f71-40c4-8e5f-c061b03a3348");
		System.out.println("===========����ID���ҵ����û�=================="+userBean);
	}
	
	public void testFindByPhone(){
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		UserBean userBean = userTest.findByPhone("18325451125");
		System.out.println("===========�����ֻ��Ų��ҵ����û�=================="+userBean);
	}
	public void testFindByEmail(){
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		UserBean userBean = userTest.findByEmail("sddfsdf@163.com");
		System.out.println("===========����������ҵ����û�=================="+userBean);
	}
	
	public void testFind(){
		UserDaoImpl userTest = new UserDaoImpl(getContext());
		List<UserBean> userList = userTest.find();
		System.out.println("=============���ҵ������û���:================"+userList.size());
	}
}
