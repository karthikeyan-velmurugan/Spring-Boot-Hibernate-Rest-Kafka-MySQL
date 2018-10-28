package com.cyber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cyber.domain.Job;
/**
 * @author Karthikeyan
 *
 */
@Service
public class KafkaConsumer {

	private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "job-kafka-topic",containerFactory = "jobKafkaListenerFactory")
	public Job consumeJob(Job job) {
		log.info("Consumed Job Message: " + job);
		return job;
	}
}	