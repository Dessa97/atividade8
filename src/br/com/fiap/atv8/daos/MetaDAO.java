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
import br.com.fiap.atv8.classes.Meta;

public class MetaDAO extends OperacoesDAO{

	private Connection conexao;

	public List<Meta> listar(int idConta) {
		List<Meta> lista = new ArrayList<Meta>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ContaDAO contaDAO = new ContaDAO();

	    try {
	        conexao = BancoDados.obterConexao();
	        stmt = conexao.prepareStatement("SELECT * FROM T_SIP_META WHERE ID_CONTA = ?");
	        stmt.setInt(1, idConta);
	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            int idMeta = rs.getInt("ID_META");
	            String descricao = rs.getString("DS_META");
	            double valor = rs.getDouble("VL_META");
	            LocalDate data = converteDateParaLocalDate(rs.getDate("DT_META"));
	            
	            Conta conta = contaDAO.buscar(idConta);
	            
	            //Cria um objeto Meta para armazenar as informações vindas da Tabela
	            Meta meta = new Meta(idMeta, conta, descricao, valor, data);
	            lista.add(meta);
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
	
	public void remover(int idMeta) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "DELETE FROM T_SIP_META WHERE ID_META = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idMeta);
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
