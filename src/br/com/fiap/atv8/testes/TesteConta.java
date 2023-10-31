package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Usuario;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.UsuarioDAO;

public class TesteConta {

    public static void main(String[] args) {
        ContaDAO contaDAO = new ContaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.buscar("rafael@gmail.com");
        Conta conta = new Conta(null, usuario);


        contaDAO.cadastrar(conta);

        System.out.println("Cadastrado!");
    }

}

