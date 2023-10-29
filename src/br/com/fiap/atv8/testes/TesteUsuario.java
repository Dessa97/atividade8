package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Usuario;
import br.com.fiap.atv8.daos.UsuarioDAO;

public class TesteUsuario {
	
	    public static void main(String[] args) {

	      UsuarioDAO dao = new UsuarioDAO();
	  
	      Usuario usuario = new Usuario();
	      usuario.setEmail("Bruce@hotmail.com");
	      usuario.setNome("Bruce João");
	      usuario.setSenha("897453");
	
	      dao.cadastrar(usuario);
	  
	      System.out.println("Cadastrado!");
	    }
	  
}