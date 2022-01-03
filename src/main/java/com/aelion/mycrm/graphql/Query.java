package com.aelion.mycrm.graphql;

import java.util.List;

import com.aelion.mycrm.dao.ContactDao;
import com.aelion.mycrm.models.Person;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {
	private ContactDao contactDao;
	
	public Query(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public List<Person> getPerson(int count, int offset) {
		return this.contactDao.getPersons(count, offset);
	}
}
