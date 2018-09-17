package com.tricon.ticketmanagementsystem.service;

import java.util.Map;

public interface IEmailService {

	public void sendTheEmails( String[] stringArray ,String subject,Map < String, Object > model );
}