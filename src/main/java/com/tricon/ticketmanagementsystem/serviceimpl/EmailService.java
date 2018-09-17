package com.tricon.ticketmanagementsystem.serviceimpl;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.tricon.ticketmanagementsystem.service.IEmailService;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class EmailService implements IEmailService {
	
	@Value("${spring.mail.username}")
	String from;
	
    @Autowired
    private JavaMailSender sender;
    
    @Autowired
	VelocityEngine velocityEngine;

    @Override
	public void sendTheEmails( String[] stringArray ,String subject, Map < String, Object > model ) {
		
		
		try {				
			MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        
	        helper.setFrom(from);
	       
	        helper.setTo(stringArray);
	        	        
	        helper.setText(getContentFromTemplateGroup(model),true);
	        helper.setSubject(subject);
	        
	        sender.send(message);
		}
		catch(Exception e) {
			System.out.println("Snap! Something went wrong.");
		}

	}
		
	        
		 public String getContentFromTemplateCreation(Map < String, Object > model) {
		        StringBuffer content = new StringBuffer();
		        try {
		            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/templates/emailCreation.vm", model));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return content.toString();
		}
		 
		 public String getContentFromTemplateGroup(Map < String, Object > model) {
		        StringBuffer content = new StringBuffer();
		        try {
		            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/templates/groupCreation.vm", model));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return content.toString();
		}
	}
