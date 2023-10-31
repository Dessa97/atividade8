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
import br.com.fiap.atv8.classes.Saida;

public class SaidaDAO extends OperacoesDAO{

	private Connection conexao;
	
	//Lista todas as saidas de uma Conta.
	public List<Saida> listar(int idConta) {
		List<Saida> lista = new ArrayList<Saida>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ContaDAO contaDAO = new ContaDAO();

	    try {
	        conexao = BancoDados.obterConexao();
	        stmt = conexao.prepareStatement("SELECT * FROM T_SIP_SAIDA WHERE ID_CONTA = ?");
	        stmt.setInt(1, idConta);
	        rs = stmt.executeQuery();
			Conta conta = contaDAO.buscar(idConta);
	        while (rs.next()) {
	            int idSaida = rs.getInt("ID_SAIDA");
	            String descricao = rs.getString("DS_SAIDA");
	            double valor = rs.getDouble("VL_SAIDA");
	            LocalDate data = converteDateParaLocalDate(rs.getDate("DT_SAIDA"));

	            //Cria um objeto Meta para armazenar as informações vindas da Tabela
	            Saida saida = new Saida(idSaida, conta, descricao, valor, data);
	            lista.add(saida);
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
	
	//Remove uma saida
	public void remover(int idSaida) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "DELETE FROM T_SIP_SAIDA WHERE ID_SAIDA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idSaida);
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
