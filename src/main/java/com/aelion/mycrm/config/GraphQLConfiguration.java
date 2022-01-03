package com.aelion.mycrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aelion.mycrm.dao.ContactDao;
import com.aelion.mycrm.graphql.PersonResolver;
import com.aelion.mycrm.graphql.Query;

@Configuration
public class GraphQLConfiguration {
	@Bean
	public Query query(ContactDao contactDao) {
		return new Query(contactDao);
	}
	
	@Bean
	public PersonResolver personResolver(ContactDao contactDao) {
		return new PersonResolver(contactDao);
	}
}
