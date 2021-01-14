package br.com.dhecastro.serializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dhecastro.model.Ticket;

public class TicketSerializer implements Serializer<Ticket>{

	@Override
	public byte[] serialize(String topic, Ticket venda) {
		try {
			return new ObjectMapper().writeValueAsBytes(venda);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
