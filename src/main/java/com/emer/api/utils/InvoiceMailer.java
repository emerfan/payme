package com.emer.api.utils;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.emer.api.model.Invoice;

@Component
public class InvoiceMailer {
	
	@Value( "${mail.smtp.host.outgoing}" )
	private String outgoingHost;
	
	@Value( "${mail.smtp.port.outgoing}" )
	private String outgoingPort;
	
	@Value( "${mail.username}" )
	private String mailUsername;
	
	@Value( "${mail.password}" )
	private String mailPassword;
	
	@Value( "${mail.from}" )
	private String mailFrom;
	
	/*Temp mail to for testing*/
	@Value( "${mail.to}" )
	private String mailTo;
	
	/**
	 * 
	 * @param invoice
	 * @param salonName
	 * @param pdfAsBytes
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void mailInvoicePdf(Invoice invoice, String salonName, byte[] pdfAsBytes ) 
			throws AddressException, MessagingException, IOException {
		   	 
		   Properties properties = setMailProperties();
		   Session session = getSessionForMail(properties); 
		   
		   Message msg = createMailMessage(invoice, session);  
		   
		   MimeBodyPart messageBodyPart = writeMailMessage(salonName);
           MimeBodyPart pdfBodyPart = attachPdf(invoice, pdfAsBytes);     
		   Multipart multipart = createMultipartMail(messageBodyPart, pdfBodyPart);
		   
		   msg.setContent(multipart);
		 	   
		   Transport.send(msg); 
		}
	
	/**
	 * 
	 * @param messageBodyPart
	 * @param pdfBodyPart
	 * @return
	 * @throws MessagingException
	 */
	private Multipart createMultipartMail(MimeBodyPart messageBodyPart, MimeBodyPart pdfBodyPart)
			throws MessagingException {
		Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   multipart.addBodyPart(pdfBodyPart);
		return multipart;
	}
	
	/**
	 * 
	 * @return
	 */
	private Properties setMailProperties() {
		Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.host", outgoingHost);
		   props.put("mail.smtp.port", outgoingPort);
		return props;
	}
	
	/**
	 * 
	 * @param salonName
	 * @return
	 * @throws MessagingException
	 */
	private MimeBodyPart writeMailMessage(String salonName) throws MessagingException {
		MimeBodyPart messageBodyPart = new MimeBodyPart();  
		   messageBodyPart.setContent(createEmailMessage(salonName), "text/html");
		return messageBodyPart;
	}
	
	/**
	 * 
	 * @param invoice
	 * @param pdfAsBytes
	 * @return
	 * @throws MessagingException
	 */
	private MimeBodyPart attachPdf(Invoice invoice, byte[] pdfAsBytes) throws MessagingException {
		DataSource dataSource = new ByteArrayDataSource(pdfAsBytes, "application/pdf");
           MimeBodyPart pdfBodyPart = new MimeBodyPart();
           pdfBodyPart.setDataHandler(new DataHandler(dataSource));
           pdfBodyPart.setFileName("INV_"+ invoice.getId() + ".pdf");
		return pdfBodyPart;
	}
	
	/**
	 * 
	 * @param invoice
	 * @param session
	 * @return
	 * @throws MessagingException
	 * @throws AddressException
	 */
	private Message createMailMessage(Invoice invoice, Session session) throws MessagingException, AddressException {
		//TODO Get customer email address
		Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress(mailFrom, false));
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
		   msg.setSubject(getMailSubject(invoice.getId()));
		   msg.setSentDate(new Date());
		return msg;
	}
	
	/**
	 * 
	 * @param props
	 * @return
	 */
	private Session getSessionForMail(Properties props) {
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(mailUsername, mailPassword);
		      }
		   });
		return session;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private String getMailSubject(Long id) {
		return "Invoice Number: " + id.toString();
	}
	
	/**
	 * 
	 * @param salonName
	 * @return
	 */
	private String createEmailMessage(String salonName) {
		String mailMessage = "<p>Hello " + salonName + ",</p>"
				   + "<p>Please find attached your latest invoice.</p>"
				   + "<p>Thanks and regards,</p>"
				   + "<p>Peter</p>"
				   + "<p>Tel: 087 223 88 88 </p>"
				   + "<p>Shop Online at <a href = 'www.handb.ie'>www.handb.ie</a></p>";
		return mailMessage;
	}
}
