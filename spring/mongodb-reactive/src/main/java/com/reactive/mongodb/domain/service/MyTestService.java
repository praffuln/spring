package com.reactive.mongodb.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reactive.mongodb.domain.model.Person;
import com.reactive.mongodb.domain.model.Process1;
import com.reactive.mongodb.domain.model.State;
import com.reactive.mongodb.domain.repository.PersonRepository;
import com.reactive.mongodb.domain.repository.ReactiveProcessRepository;

@Service
public class MyTestService {

	@Autowired
	ReactiveProcessRepository reactiveProcessRepository;

	@Autowired
	PersonRepository personReactiveRepository;

	@Transactional
	public void testTransaction() {
			saveProcessObject();
			savePersonObject();
	}

	public void saveProcessObject() {
		Process1 process = new Process1();
		process.setId(1);
		process.setState(State.CREATED);
		process.setTransitionCount(0);
		process = reactiveProcessRepository.save(process).block();
	}


	public void savePersonObject() {
		Person person = new Person("A", "B");
//		person = null;
		personReactiveRepository.save(person).block();
	}

}
