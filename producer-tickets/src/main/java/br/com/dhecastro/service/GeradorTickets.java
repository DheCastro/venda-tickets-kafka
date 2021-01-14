package br.com.dhecastro.service;

import java.math.BigDecimal;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import br.com.dhecastro.model.Ticket;
import br.com.dhecastro.serializer.TicketSerializer;

public class GeradorTickets {

	private static Random random = new Random();
	private static long processo = 0;
	private static BigDecimal valor = BigDecimal.valueOf(100);
	
	public static void main(String[] args) throws InterruptedException {
		
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, TicketSerializer.class.getName());
		
		try(KafkaProducer<String, Ticket> producer = new KafkaProducer<String, Ticket>(properties)){
			
			while(true) {
				Ticket ticket = geraTickets();
				ProducerRecord<String, Ticket> record = new ProducerRecord<String, Ticket>("tickets", ticket);
				producer.send(record);
				Thread.sleep(200);
			}
		}
	}

	private static Ticket geraTickets() {
		long cliente = random.nextLong();
		int qtdTickets = random.nextInt(10);
		return new Ticket(processo++, cliente, qtdTickets, valor.multiply(BigDecimal.valueOf(qtdTickets)));
	}

}
