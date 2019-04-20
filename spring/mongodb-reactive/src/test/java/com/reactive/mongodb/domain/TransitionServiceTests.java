///*
// * Copyright 2018 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.reactive.mongodb.domain;
// 
//
//import java.util.function.Consumer;
//
//import org.assertj.core.api.Assertions;
//import org.bson.Document;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.Projections;
//import com.reactive.mongodb.domain.model.Process1;
//import com.reactive.mongodb.domain.model.State;
//import com.reactive.mongodb.domain.service.TransitionService;
//
///**
// * Test showing MongoDB Transaction usage through a synchronous (imperative) API using Spring's managed transactions.
// *
// * @author Christoph Strobl
// * @currentRead The Core - Peter V. Brett
// * @see org.springframework.transaction.annotation.Transactional
// */
//@RunWith(SpringRunner.class)
//@DataMongoTest
//@Ignore
//public class TransitionServiceTests {
//
////	public static @ClassRule EmbeddedMongo replSet = EmbeddedMongo.replSet().configure();
//
//	static final String DB_NAME = "spring-data-tx-examples";
//
//	@Autowired TransitionService transitionService;
//	@Autowired com.mongodb.MongoClient client;
//
//	@Test
//	public void txCommitRollback() {
//
////		for (int i = 0; i < 10; i++) {
//			System.out.println("transitionServicetransitionServicetransitionServicetransitionService "+ transitionService);
//			Process1 process = transitionService.newProcess();
//
//			try {
//				transitionService.run(process.getId());
//				Assertions.assertThat(stateInDb(process)).isEqualTo(State.DONE);
//			} catch (IllegalStateException e) {
//				System.out.println("*******inside catch********");
//				Assertions.assertThat(stateInDb(process)).isEqualTo(State.CREATED);
//			}
////		}
//
//		client.getDatabase(DB_NAME).getCollection("processes").find(new Document())
//				.forEach((Consumer<? super Document>) System.out::println);
//	}
//
//	State stateInDb(Process1 process) {
//		String string = client.getDatabase(DB_NAME).getCollection("processes")
//				.find(Filters.eq("_id", process.getId()))
//				.projection(Projections.include("state"))
//				.first().get("state", String.class);
//		System.out.println("******string is ********** "+ string );
//		
//		return State.valueOf(string);
//	}
//
//}
