package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.daos.UsuarioDAO;

public class TesteRemoçãoUsuario {
  
    public static void main(String[] args) {
      UsuarioDAO dao = new UsuarioDAO();
    
      dao.remover("Bruce@fiap.com.br");
      
      System.out.println("Removido!");
    }
  }
