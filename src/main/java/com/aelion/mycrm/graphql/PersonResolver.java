package com.aelion.mycrm.graphql;

import com.aelion.mycrm.dao.ContactDao;
import com.aelion.mycrm.models.Occupation;
import com.aelion.mycrm.models.Person;
import com.coxautodev.graphql.tools.GraphQLResolver;

public class PersonResolver implements GraphQLResolver<Person> {
	private ContactDao contactDao;
	
	public PersonResolver(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	public Occupation getOccupation(Person person) {
		return this.contactDao.getOccupation(person);
	}
}
