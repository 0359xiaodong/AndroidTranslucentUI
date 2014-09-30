package com.keen.ma.bean;

import java.io.Serializable;

/**
 * �û�bean��
 * 
 * @author KeenW
 * @lastChangeTime 2014-8-17����7:47:21
 */
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userId;// UID
	private String userNickName;// �û��ǳ�
	private String userPassword;// �û�����
	private String userPhone;// �û��ֻ���
	private String userEmail;// �û�����
	private String userImagePath;// �û�ͼ��·��
	private double userLatitude;// �û�λ��γ��
	private double userLongitude;// �û�λ�þ���

	public UserBean() {

	}

	public UserBean(String userId, String userNickName, String userPassword,
			String userPhone, String userEmail, String userImagePath,
			double userLatitude, double userLongitude) {
		this.userId = userId;
		this.userNickName = userNickName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userImagePath = userImagePath;
		this.userLatitude = userLatitude;
		this.userLongitude = userLongitude;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public String getUserImagePath() {
		return userImagePath;
	}

	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}

	public double getUserLatitude() {
		return userLatitude;
	}

	public void setUserLatitude(double userLatitude) {
		this.userLatitude = userLatitude;
	}

	public double getUserLongitude() {
		return userLongitude;
	}

	public void setUserLongitude(double userLongitude) {
		this.userLongitude = userLongitude;
	}

	@Override
	public String toString() {
		return "�û�ID:" + userId + ";�û��ǳƣ�" + userNickName + ";�û����룺"
				+ userPassword + ";�û��ֻ���" + userPhone + ";�û����䣺" + userEmail
				+ ";�û�ͼ��Url:" + userImagePath + "�û����ȣ�" + userLongitude
				+ ";�û�γ�ȣ�" + userLatitude;
	}
}
