package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Usuario;
import br.com.fiap.atv8.daos.ContaDAO;

public class TesteConta {

	public static void main(String[] args) {
		 ContaDAO dao = new ContaDAO();
		  
	      Usuario usuario = new Usuario();
	      usuario.setEmail("Bruce@hotmail.com");
	      usuario.setNome("Bruce João");
	      usuario.setSenha("897453");
	      Conta conta = new Conta(null, usuario);
	      
	
	      dao.cadastrar(conta);
	  
	      System.out.println("Cadastrado!");
	    }

	}

