package br.com.fiap.atv8.testes;

import java.time.LocalDate;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Entrada;
import br.com.fiap.atv8.classes.Meta;
import br.com.fiap.atv8.classes.Saida;
import br.com.fiap.atv8.classes.Usuario;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.EntradaDAO;
import br.com.fiap.atv8.daos.MetaDAO;
import br.com.fiap.atv8.daos.SaidaDAO;
import br.com.fiap.atv8.daos.UsuarioDAO;

public class TesteOperacoes {

	public static void main(String[] args) {
		ContaDAO contaDAO = new ContaDAO();

		//Usuario usuario = new Usuario();
		//usuario.setEmail("bruce@hotmail.com");
		//usuario.setNome("Bruce João");
		//usuario.setSenha("897453");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar("bruce@hotmail.com");
		
		//Conta conta = new Conta(null, usuario);

		//dao.cadastrar(conta);
		
		Conta conta = contaDAO.buscar(21);

		System.out.println("Cadastrado!");
		
		MetaDAO metaDAO = new MetaDAO();
		
		Meta meta = new Meta();
		meta.setConta(conta);
		meta.setData(LocalDate.of(2023, 10, 28));
		meta.setDescricao("Ganhar petisco");
		meta.setValor(1000.00);
		
		metaDAO.cadastrar(meta);
		
		System.out.println("Meta Cadastrado!");
		
		EntradaDAO entradaDAO = new EntradaDAO();
		
		Entrada entrada = new Entrada();
		entrada.setConta(conta);
		entrada.setData(LocalDate.of(2023, 10, 28));
		entrada.setDescricao("Ganhei Petisco");
		entrada.setValor(1000.00);
		
		entradaDAO.cadastrar(entrada);
		
		System.out.println("Entrada Cadastrado!");
		
		SaidaDAO saidaDAO = new SaidaDAO();
		
		Saida saida = new Saida();
		saida.setConta(conta);
		saida.setData(LocalDate.of(2023, 10, 29));
		saida.setDescricao("Bela comeu meu Petisco");
		saida.setValor(500.00);
		
		saidaDAO.cadastrar(saida);
		
		System.out.println("Saida Cadastrado!");

	}

}
