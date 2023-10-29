package br.com.fiap.atv8.classes;

import java.time.LocalDate;

public abstract class Operacoes {

	protected int id;
	protected String descricao;
	protected double valor;
	protected LocalDate data;
	protected Conta conta;

//Construtor com Argumentos
	public Operacoes(Integer id, Conta conta, String descricao, double valor, LocalDate data) {
		super();
		this.id = id;
		this.conta = conta; 
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}

	public Operacoes() {
		super();
	}

//Métodos Get
	public int getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}
	
	public Conta getConta() {
		return conta;
	}

//Métodos Set
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}

}