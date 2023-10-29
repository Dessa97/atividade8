package br.com.fiap.atv8.classes;

import java.util.List;

import br.com.fiap.atv8.daos.EntradaDAO;
import br.com.fiap.atv8.daos.MetaDAO;
import br.com.fiap.atv8.daos.SaidaDAO;

public class Conta {

	private Integer id;
	private Usuario usuario;

	// construtor
	public Conta(Integer id, Usuario usuario) {
		this.id = id;
		this.usuario = usuario;
	}

	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Entrada> getListaEntrada() {
		if (this.id == null) {
			return null;
		}
		EntradaDAO entradaDAO = new EntradaDAO();
		return entradaDAO.listar(this.id);
	}

	public List<Saida> getListaSaida() {
		if (this.id == null) {
			return null;
		}
		SaidaDAO saidaDAO = new SaidaDAO();
		return saidaDAO.listar(this.id);
	}

	public List<Meta> getListaMeta() {
		if (this.id == null) {
			return null;
		}
		MetaDAO metaDAO = new MetaDAO();
		return metaDAO.listar(this.id);
	}

	public double getSaldo() {
		return calcularSaldo();
	}

	private double calcularSaldo() {

		double totalEntrada = 0;
		
		List<Entrada> listaEntrada = getListaEntrada();
		
		if(listaEntrada != null) {
			for (int i = 0; i < listaEntrada.size(); i++) {
				totalEntrada = totalEntrada + listaEntrada.get(i).getValor();
			}
		}

		double totalSaida = 0;
		
		List<Saida> listaSaida = getListaSaida();
		
		if(listaSaida != null) {
			for (int i = 0; i < listaSaida.size(); i++) {
				totalSaida = totalSaida + listaSaida.get(i).getValor();
			}
		}

		return totalEntrada - totalSaida;
	}

}
