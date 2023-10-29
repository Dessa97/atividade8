package br.com.fiap.atv8.classes;

import java.time.LocalDate;

public class Entrada extends Operacoes{

	public Entrada(Integer id, Conta conta, String descrição, double valor, LocalDate data) {
		super(id, conta, descrição, valor, data);
	}

	public Entrada() {
		super();
	}

}