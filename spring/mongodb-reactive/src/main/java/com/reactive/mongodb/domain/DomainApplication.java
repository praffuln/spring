package com.reactive.mongodb.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.reactive.mongodb.domain.model.Person;
import com.reactive.mongodb.domain.repository.PersonRepository;
import com.reactive.mongodb.domain.repository.ReactiveProcessRepository;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ComponentScan
@EnableTransactionManagement
public class DomainApplication implements ApplicationRunner {

	@Autowired
	PersonRepository personReactiveRepository;
	
	@Autowired
	ReactiveProcessRepository reactiveProcessRepository;

	public static void main(String[] args) {
		SpringApplication.run(DomainApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		personReactiveRepository.deleteAll();
		reactiveProcessRepository.deleteAll();
//		System.out.println("************inside run()*********************************");
//		Person person = new Person("A", "B");
//		personReactiveRepository.save(person).block();
	}

	@Bean
	PlatformTransactionManager transactionManager(MongoDbFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
	
//	@Bean
//	public MongoClient reactiveMongoClient() {
//		return MongoClients.create("mongodb://localhost/myfirstdb");
//	}
//
//	@Bean
//	public ReactiveMongoTemplate reactiveMongoTemplate() {
//		return new ReactiveMongoTemplate(reactiveMongoClient(), "myfirstdb");
//	}

}
