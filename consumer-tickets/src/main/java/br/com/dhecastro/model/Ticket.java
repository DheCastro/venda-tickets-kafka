package br.com.dhecastro.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Ticket {

	private Long processoId;
	private Long clienteId;
	private Integer qtdTickets;
	private BigDecimal valorTickets;
	private String status;
	
	public Ticket(Long processoId, Long clienteId, Integer qtdTickets, BigDecimal valorTickets) {
		super();
		this.processoId = processoId;
		this.clienteId = clienteId;
		this.qtdTickets = qtdTickets;
		this.valorTickets = valorTickets;
	}
	
}
