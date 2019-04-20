package com.reactive.mongodb.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactive.mongodb.domain.service.MyTestService;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MyTestServiceTests {
	
	@Autowired
	private MyTestService myTestService;
	
	@Test
	public void reactiveTxCommitRollback() {
		myTestService.testTransaction();
		}

}
