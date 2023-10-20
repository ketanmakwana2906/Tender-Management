package mail;
import javax.mail.Address;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
	public class PasswordMail {
		public PasswordMail() {
		}
		
		public boolean sendMail(String to,String otp) throws MessagingException 
		{
			String host="smtp.gmail.com";
			String username="authoritytendermanagement@gmail.com";//emailid 
			String password="jnue bwjv vseu ntyk";//emailid password
			String from="authoritytendermanagement@gmail.com";// email from which u have to send
			String subject="One Time Password";
			String body="Your One Time passsword is "+otp;
	
			boolean sessionDebug=false;
			
			Properties props=System.getProperties();
			props.put("mail.host",host);  
			props.put("mail.transport.protocol","smtp");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.starttls.required", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "425");  
		 	
			Session mailSession=Session.getDefaultInstance(props,null);
			mailSession.setDebug(sessionDebug);
			
			Message msg=new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress [] address={new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO,address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(body);

			Transport tr=mailSession.getTransport("smtp");
			tr.connect(host,username,password);
			msg.saveChanges();
			tr.sendMessage(msg,msg.getAllRecipients());
			tr.close();
			//Transport.send(msg);
			return true;
		}
		public boolean sendMail1(String to,String passwrd, String link) throws MessagingException
		{
			String host="smtp.gmail.com";
			String username="authoritytendermanagement@gmail.com";//emailid
			String password="jnue bwjv vseu ntyk";//emailid password
			String from="authoritytendermanagement@gmail.com";// email from which u have to send
			String subject="Website Password";
			String body="Your website password is "+passwrd+"  Please login using this password and you can also reset password  ";
			boolean sessionDebug=false;
			
			Properties props=System.getProperties();
			props.put("mail.host",host);
			props.put("mail.transport.protocol","smtp");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "25"); 
	
			Session mailSession=Session.getDefaultInstance(props,null);
			mailSession.setDebug(sessionDebug);
		
			Message msg=new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			
			InternetAddress [] address={new InternetAddress(to)};
			msg.setRecipients(Message.RecipientType.TO,address);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(body);
			
			Transport tr=mailSession.getTransport("smtp");
			tr.connect(host,username,password);
			msg.saveChanges();
			tr.sendMessage(msg,msg.getAllRecipients());
			tr.close();
					//Transport.send(msg);
			return true;
		}
		
public boolean sendMail2(String to, String bid,String tid,String vid,String amount) throws AddressException, MessagingException {
			
			String host="smtp.gmail.com";
			String username="authoritytendermanagement@gmail.com";//emailid 
			String password="jnue bwjv vseu ntyk";//emailid password
			String from="authoritytendermanagement@gmail.com";// email from which u have to send
			String subject="Notice for Pay Bid Processing charge";
			 String body = "Dear Vendor , Your bid selected for tender("+ tid +").\n\n"
			            + "now complete your payment for bid processing charge to confirm bid order\n"
			            + "Bid ID: " + bid + "\n"
			            + "Tender ID: " + tid + "\n"
			            + "Vendor ID: " + vid + "\n"
			            + "Amount: ₹ " + amount + "\n"
			            + "payment process: login in your vendor account and go to bid history section";
			    			boolean sessionDebug=false;
			
			    			Properties props=System.getProperties();
			    			props.put("mail.host",host);  
			    			props.put("mail.transport.protocol","smtp");
			    			props.put("mail.smtp.starttls.enable","true");
			    			props.put("mail.smtp.starttls.required", "true");
			    			props.put("mail.smtp.auth", "true");
			    			props.put("mail.smtp.debug", "true");
			    			props.put("mail.smtp.socketFactory.port", "465");
			    			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			    			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			    			props.put("mail.smtp.socketFactory.fallback", "false");
			    			props.put("mail.smtp.host", "smtp.gmail.com");
			    			props.put("mail.smtp.port", "425");  
			    		 	
			    			Session mailSession=Session.getDefaultInstance(props,null);
			    			mailSession.setDebug(sessionDebug);
			    			
			    			Message msg=new MimeMessage(mailSession);
			    			msg.setFrom(new InternetAddress(from));
			    			InternetAddress [] address={new InternetAddress(to)};
			    			msg.setRecipients(Message.RecipientType.TO,address);
			    			msg.setSubject(subject);
			    			msg.setSentDate(new Date());
			    			msg.setText(body);

			    			Transport tr=mailSession.getTransport("smtp");
			    			tr.connect(host,username,password);
			    			msg.saveChanges();
			    			tr.sendMessage(msg,msg.getAllRecipients());
			    			tr.close();
			    			//Transport.send(msg);
			    			return true;
			    		}
		
		public boolean sendMail3(String to, String bid,String tid,String vid,String pid,String amount,String oid) throws AddressException, MessagingException {
			
			String host="smtp.gmail.com";
			String username="authoritytendermanagement@gmail.com";//emailid 
			String password="jnue bwjv vseu ntyk";//emailid password
			String from="authoritytendermanagement@gmail.com";// email from which u have to send
			String subject="Payment successfull! and tender assigned against bid";
			 String body = "Congratulations! Tender successfully assigned against your bid.\n\n"
			            + "Your Payment details:\n"
			            + "Payment ID: " + pid + "\n"
			            + "Bid ID: " + bid + "\n"
			            + "Tender ID: " + tid + "\n"
			            + "Vendor ID: " + vid + "\n"
			            + "Amount: ₹ " + amount + "\n"
			            + "Order ID: " + oid + "\n"
			            + "Now our admin will contact you through your email for further process.";
			    			boolean sessionDebug=false;
			
			    			Properties props=System.getProperties();
			    			props.put("mail.host",host);  
			    			props.put("mail.transport.protocol","smtp");
			    			props.put("mail.smtp.starttls.enable","true");
			    			props.put("mail.smtp.starttls.required", "true");
			    			props.put("mail.smtp.auth", "true");
			    			props.put("mail.smtp.debug", "true");
			    			props.put("mail.smtp.socketFactory.port", "465");
			    			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			    			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			    			props.put("mail.smtp.socketFactory.fallback", "false");
			    			props.put("mail.smtp.host", "smtp.gmail.com");
			    			props.put("mail.smtp.port", "425");  
			    		 	
			    			Session mailSession=Session.getDefaultInstance(props,null);
			    			mailSession.setDebug(sessionDebug);
			    			
			    			Message msg=new MimeMessage(mailSession);
			    			msg.setFrom(new InternetAddress(from));
			    			InternetAddress [] address={new InternetAddress(to)};
			    			msg.setRecipients(Message.RecipientType.TO,address);
			    			msg.setSubject(subject);
			    			msg.setSentDate(new Date());
			    			msg.setText(body);

			    			Transport tr=mailSession.getTransport("smtp");
			    			tr.connect(host,username,password);
			    			msg.saveChanges();
			    			tr.sendMessage(msg,msg.getAllRecipients());
			    			tr.close();
			    			//Transport.send(msg);
			    			return true;
			    		}
	}