package com.aelion.mycrm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aelion.mycrm.models.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
