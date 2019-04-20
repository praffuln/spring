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
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.bson.Document;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import com.reactive.mongodb.domain.model.Process1;
//import com.reactive.mongodb.domain.model.State;
//import com.reactive.mongodb.domain.service.ReactiveTransitionService;
//
//import reactor.core.publisher.Flux;
//import reactor.test.StepVerifier;
//
///**
// * Test showing MongoDB Transaction usage through a reactive API.
// *
// * @author Christoph Strobl
// * @currentRead The Core - Peter V. Brett
// */
//@RunWith(SpringRunner.class)
////@ContextConfiguration
//@DataMongoTest
//public class ReactiveTransitionServiceTests {
//
//
//	@Autowired ReactiveTransitionService transitionService;
//	@Autowired MongoClient client;
//
//	static final String DB_NAME = "spring-data-reactive-tx-examples";
//
//
//	@Test
//	public void reactiveTxCommitRollback() {
//
//		for (int i = 0; i < 10; i++) {
//			transitionService.newProcess() //
//					.map(Process1::getId) //
//					.flatMap(transitionService::run) //
//					.onErrorReturn(-1).as(StepVerifier::create) //
//					.consumeNextWith(val -> {}) //
//					.verifyComplete();
//		}
//
//		Flux.from(client.getDatabase(DB_NAME).getCollection("processes").find(new Document())) //
//				.buffer(10) //
//				.as(StepVerifier::create) //
//				.consumeNextWith(list -> {
//
//					for (Document document : list) {
//
//						System.out.println("document: " + document);
//
//						if (document.getInteger("_id") % 3 == 0) {
//							assertThat(document.getString("state")).isEqualTo(State.CREATED.toString());
//						} else {
//							assertThat(document.getString("state")).isEqualTo(State.DONE.toString());
//						}
//					}
//
//				}) //
//				.verifyComplete();
//	}
//}
