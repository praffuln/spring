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
//package com.reactive.mongodb.domain.service;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.ReactiveMongoOperations;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import com.reactive.mongodb.domain.model.Process1;
//import com.reactive.mongodb.domain.model.State;
//import com.reactive.mongodb.domain.repository.ReactiveProcessRepository;
//
//import reactor.core.publisher.Mono;
//
//@Service
//public class ReactiveTransitionService {
//
//	@Autowired
//	private com.reactive.mongodb.domain.repository.ReactiveProcessRepository repository;
//	
//	@Autowired ReactiveMongoTemplate template;
//
//	final AtomicInteger counter = new AtomicInteger(0);
//
//	public Mono<com.reactive.mongodb.domain.model.Process1> newProcess() {
//		return repository.save(new com.reactive.mongodb.domain.model.Process1(counter.incrementAndGet(), State.CREATED, 0));
//	}
//
//	public Mono<Integer> run(Integer id) {
//
//		return template.inTransaction().execute(action -> {
//
//			return lookup(id) //
//					.flatMap(process -> start(action, process)) //
//					.flatMap(it -> verify(it)) //
//					.flatMap(process -> finish(action, process));
//
//		}).next().map(Process1::getId);
//	}
//
//	private Mono<Process1> finish(ReactiveMongoOperations operations, Process1 process) {
//		return operations.update(Process1.class).matching(Query.query(Criteria.where("id").is(process.getId())))
//				.apply(Update.update("state", State.DONE).inc("transitionCount", 1)).first() //
//				.then(Mono.just(process));
//	}
//
//	Mono<Process1> start(ReactiveMongoOperations operations, Process1 process) {
//
//		return operations.update(Process1.class).matching(Query.query(Criteria.where("id").is(process.getId())))
//				.apply(Update.update("state", State.ACTIVE).inc("transitionCount", 1)).first() //
//				.then(Mono.just(process));
//	}
//
//	Mono<Process1> lookup(Integer id) {
//		return repository.findById(id);
//	}
//
//	Mono<Process1> verify(Process1 process) {
//
//		Assert.state(process.getId() % 3 != 0, "We're sorry but we needed to drop that one");
//		return Mono.just(process);
//	}
//}
