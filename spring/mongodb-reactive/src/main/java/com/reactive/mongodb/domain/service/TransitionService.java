/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reactive.mongodb.domain.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.reactive.mongodb.domain.model.Process1;
import com.reactive.mongodb.domain.model.State;
import com.reactive.mongodb.domain.repository.ReactiveProcessRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author Christoph Strobl
 * @currentRead The Core - Peter V. Brett
 */
@Service
public class TransitionService {

	@Autowired
	ReactiveProcessRepository repository;
	
	@Autowired
	MongoTemplate template;

	final AtomicInteger counter = new AtomicInteger(0);

	@Transactional
	public Process1 newProcess() {
		Process1 process = new Process1();
		process.setId(counter.incrementAndGet()); process.setState(State.CREATED);
		process.setTransitionCount(0);
		process = repository.save(process).block();
		return process;
	}

	@Transactional
	public void run(Integer id) {

		Process1 process = lookup(id);

		if (!State.CREATED.equals(process.getState())) {
			return;
		}

		start(process);
		verify(process);
		finish(process);
	}

	void start(Process1 process) {
		template.update(Process1.class).matching(Query.query(Criteria.where("id").is(process.getId())))
		.apply(Update.update("state", State.ACTIVE).inc("transitionCount", 1)).first();
	}
	
	void verify(Process1 process) {
		Assert.state(process.getId() % 3 != 0, "We're sorry but we needed to drop that one");
	}

	private void finish(Process1 process) {
		template.update(Process1.class).matching(Query.query(Criteria.where("id").is(process.getId())))
				.apply(Update.update("state", State.DONE).inc("transitionCount", 1)).first();
	}

	Process1 lookup(Integer id) {
		return repository.findById(id).block();
	}


}
