package br.com.dhecastro.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import br.com.dhecastro.deserializer.TicketDeserializer;
import br.com.dhecastro.model.Ticket;

public class ProcessadorTickets {
	
	public static void main(String[] args) throws InterruptedException {
		
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, TicketDeserializer.class.getName());
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "grupo-processamento");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
		
		
		try(KafkaConsumer<String, Ticket> consumer = new KafkaConsumer<>(properties)){
			
			consumer.subscribe(Arrays.asList("tickets"));
			
			while(true) {
				
				ConsumerRecords<String, Ticket> vendas = consumer.poll(Duration.ofMillis(200));
				for (ConsumerRecord<String, Ticket> record : vendas) {
					
					Ticket ticket = record.value();
					
					if(new Random().nextBoolean()) {
						ticket.setStatus("APROVADO");
					}else {
						ticket.setStatus("REPROVADO");
					}
					Thread.sleep(500);
					System.out.println(ticket);
				}
				
			}
			
		}
		
	}

}
