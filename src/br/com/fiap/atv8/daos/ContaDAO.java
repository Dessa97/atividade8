package br.com.fiap.atv8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.atv8.BancoDados;
import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Usuario;

public class ContaDAO {

	private Connection conexao;

	public void cadastrar(Conta conta) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "INSERT INTO T_SIP_CONTA(ID_CONTA, DS_EMAIL, VL_SALDO)" + "VALUES (SQ_ID_CONTA.NEXTVAL, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, conta.getUsuario().getEmail());
			stmt.setDouble(2, conta.getSaldo());

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

	public List<Conta> listar() {
		List<Conta> lista = new ArrayList<Conta>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			conexao = BancoDados.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_SIP_CONTA");
			rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID_CONTA");
				String email = rs.getString("DS_EMAIL");

				// Tente buscar o usuário e trate exceções, se necessário
				Usuario usuario = null;
				try {
					usuario = usuarioDAO.buscar(email);
				} catch (Exception e) {
					// Lide com a exceção de forma apropriada, como registrar ou lançar novamente
					e.printStackTrace();
				}

				Conta conta = new Conta(id, usuario);
				lista.add(conta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	public void remover(String email) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "DELETE FROM T_SIP_CONTA WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {

			}
		}
	}

	public Conta buscar(int idConta) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Conta contaEncontrada = null;
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			conexao = BancoDados.obterConexao();
			String sql = "SELECT * FROM T_SIP_CONTA WHERE ID_CONTA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, idConta);
			rs = stmt.executeQuery();

			if (rs.next()) {
				String email = rs.getString("DS_EMAIL");
				Usuario usuario = usuarioDAO.buscar(email);

				contaEncontrada = new Conta(idConta, usuario);
			}
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
		return contaEncontrada;
	}

	// METODO QUE ATUALIZA O SALDO DA CONTA NO BANCO DE DADOS
	public void atualizaSaldo(Conta conta) {
	    PreparedStatement stmt = null;

	    try {
	        conexao = BancoDados.obterConexao();
	        String sql = "UPDATE T_SIP_CONTA SET VL_SALDO = ? WHERE ID_CONTA = ?";
	        stmt = conexao.prepareStatement(sql);
	        stmt.setDouble(1, conta.getSaldo());
	        stmt.setInt(2, conta.getId());
	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conexao != null) {
	                conexao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
