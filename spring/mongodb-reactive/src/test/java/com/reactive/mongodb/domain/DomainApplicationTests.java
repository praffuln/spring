//package com.reactive.mongodb.domain;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.reactive.mongodb.domain.model.Person;
//import com.reactive.mongodb.domain.repository.PersonRepository;
//import com.reactive.mongodb.domain.service.ReactiveTransitionService;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class DomainApplicationTests {
//	
//	@Autowired ReactiveTransitionService transitionService;
//	
//	@Autowired
//	PersonRepository personReactiveRepository;
//	
//	@Test
//	public void contextLoads() {
//	}
//
//	@Test
//	public void savePerson() {
//		personReactiveRepository.deleteAll().block();
//		System.out.println("************inside run()*********************************");
//		Person person = new Person("A", "B");
//		personReactiveRepository.save(person).block();
//	}
//}
