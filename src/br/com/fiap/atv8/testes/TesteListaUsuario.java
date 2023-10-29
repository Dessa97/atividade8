package br.com.fiap.atv8.testes;

import java.util.List;

import br.com.fiap.atv8.classes.Usuario;
import br.com.fiap.atv8.daos.UsuarioDAO;

public class TesteListaUsuario {

	public static void main(String[] args) {

		UsuarioDAO dao = new UsuarioDAO();

		List<Usuario> lista = dao.listar();
		for (Usuario item : lista) {
			System.out.println(item.getEmail() + " " + item.getNome() + " " + item.getSenha());
		}
	}

}
