package br.com.fiap.atv8.classes;

import java.time.LocalDate;

public class Meta extends Operacoes{

	public Meta(Integer id, Conta conta, String descrição, double valor, LocalDate data) {
		super(id, conta, descrição, valor, data);
	}

	public Meta() {
		super();
	}
	
}