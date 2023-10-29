package br.com.fiap.atv8.classes;


public class Usuario {
	private String email;
	private String nome;
	private String senha;
	//private Conta conta;

//Construtor com argumentos
	public Usuario (String email, String nome, String senha) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Usuario() {
		super();
	}
//Métodos Get
	public String getEmail() {
		return email;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	
//Métodos Set
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}



}