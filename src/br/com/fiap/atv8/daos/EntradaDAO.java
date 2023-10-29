package br.com.fiap.atv8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.atv8.BancoDados;
import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Entrada;

public class EntradaDAO extends OperacoesDAO {

	private Connection conexao;

	//Lista todas as Entradas de um Conta.
	public List<Entrada> listar(int idConta) {
		List<Entrada> lista = new ArrayList<Entrada>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ContaDAO contaDAO = new ContaDAO();

		try {
			conexao = BancoDados.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_SIP_ENTRADA WHERE ID_CONTA = ?");
			stmt.setInt(1, idConta);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idEntrada = rs.getInt("ID_ENTRADA");
				String descricao = rs.getString("DS_ENTRADA");
				double valor = rs.getDouble("VL_ENTRADA");
				LocalDate data = converteDateParaLocalDate(rs.getDate("DT_ENTRADA"));

				Conta conta = contaDAO.buscar(idConta);

				// Cria um objeto Entrada para armazenar as informações vindas da Tabela
				Entrada entrada = new Entrada(idEntrada, conta, descricao, valor, data);
				lista.add(entrada);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	//Remove uma Entrada
	public void remover(int idEntrada) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "DELETE FROM T_SIP_ENTRADA WHERE ID_ENTRADA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idEntrada);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
