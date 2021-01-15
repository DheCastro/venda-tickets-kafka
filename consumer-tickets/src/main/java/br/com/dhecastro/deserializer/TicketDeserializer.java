package br.com.dhecastro.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dhecastro.model.Ticket;

public class TicketDeserializer implements Deserializer<Ticket>{

	@Override
	public Ticket deserialize(String topic, byte[] ticket) {
		try {
			return new ObjectMapper().readValue(ticket, Ticket.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
