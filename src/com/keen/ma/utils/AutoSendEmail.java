package com.keen.ma.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * �Զ������ʼ�
 * @author KEEN
 * @lastChangeTime 2014��8��19�� ����10:43:28
 */
public class AutoSendEmail {

	private Properties properties;
	private Session session;
	private Message message;
	private MimeMultipart multipart;
	private static String errorMessage = "";
	
	public AutoSendEmail() {
		super();
		this.properties = new Properties();
	}
	private void setProperties(String host,String post){
		this.properties.put("mail.smtp.host",host);
		this.properties.put("mail.smtp.post",post);
		this.properties.put("mail.smtp.auth",true);
		this.session=Session.getInstance(properties);
		this.message = new MimeMessage(session);
		this.multipart = new MimeMultipart("mixed");
	}
	/**
	 * �����ռ���
	 */
	private void setReceiver(String[] receiver) {
		try {
			Address[] address = new InternetAddress[receiver.length];
			for(int i=0;i<receiver.length;i++){
				address[i] = new InternetAddress(receiver[i]);
			}
			this.message.setRecipients(Message.RecipientType.TO, address);
		} catch (MessagingException e) {
			if(errorMessage.equals("")){
				errorMessage = "�ʼ�����ʧ�ܣ��������ռ��˵�ַ�����糬ʱ��"+e.getMessage();
			}
		}
	}
	/**
	 * �����ʼ�
	 */
	private void setMessage(String from,String title,String content) throws AddressException, MessagingException{
		this.message.setFrom(new InternetAddress(from));
		this.message.setSubject(title);
		MimeBodyPart textBody = new MimeBodyPart();
		textBody.setContent(content,"text/html;charset=gbk");
		this.multipart.addBodyPart(textBody);
	}
	/**
	 * �����ʼ�
	 */
	private void sendEmail(String host,String account,String pwd){
		//��������
		try {
			this.message.setSentDate(new Date());
			this.message.setContent(this.multipart);
			this.message.saveChanges();
			Transport transport=session.getTransport("smtp");  
			transport.connect(host,account,pwd);  
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			errorMessage = "�����ʼ�ʧ��(�����ռ��������ַ�����糬ʱ):"+e.getMessage();
			e.printStackTrace();
		}
		
		
	}
	
	public void SendEmail(final String code,final String emailAdds){
        new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					setProperties("smtp.163.com", "25");
					//�����ˣ�ֻ����163���䣬��Ϊ�õ���163�������Ͷ˿ڣ�
					String msg = "�����紥�ֿɼ����û��˻������һ���֤�룺"+code+"��������ʱ�����֤����Ǳ��˲���������Ա����š�";
					setMessage("autoemailsender@163.com", "EmailSender", msg);
					setReceiver(new String[]{emailAdds});//�ռ���
					sendEmail("smtp.163.com", "autoemailsender@163.com", "sender123");//�������ʺ�����
				} catch (AddressException e) {
					if(errorMessage.equals("")){
						errorMessage = "���䷢��ʧ�ܣ������ռ��������ַ�����糬ʱ����"+e.getMessage();
					}
					e.printStackTrace();
				} catch (MessagingException e) {
					if(errorMessage.equals("")){
						errorMessage = "���䷢��ʧ�ܣ������ռ��������ַ�����糬ʱ����"+e.getMessage();
					}
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static String getErrorMessage(){
		return errorMessage;
	}

}
