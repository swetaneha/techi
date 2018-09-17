package com.tricon.ticketmanagementsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import com.tricon.ticketmanagementsystem.ldap.TicketManagementSystemRepo;

@Configuration
@Component
@PropertySource("classpath:application.properties")
public class TicketManagementSystemConfig {

	@Value("${Url}")
	private String Url;

	@Value("${Base}")
	private String Base;

	@Value("${UserDn}")
	private String UserDn;

	@Value("${Password}")
	private String Password;

	@Bean
	public LdapContextSource ldapContextSource() {
		LdapContextSource lcs = new LdapContextSource();
		lcs.setUrl(Url);
		lcs.setBase(Base);
		lcs.setUserDn(UserDn);
		lcs.setPassword(Password);
		return lcs;
	}

	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(ldapContextSource());
	}

	@Bean
	public TicketManagementSystemRepo repo() {
		return new TicketManagementSystemRepo();
	}
}
