package br.com.fiap.atv8.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.atv8.BancoDados;
import br.com.fiap.atv8.classes.Usuario;

public class UsuarioDAO {

	private Connection conexao;
	
	//Cadastra um usuário
	public void cadastrar(Usuario usuario) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "INSERT INTO T_SIP_USUARIO(DS_EMAIL, NM_USUARIO, DS_SENHA)" + 
			" VALUES (?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail().toLowerCase());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());

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

	//Lista todos os usuários cadastrados
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = BancoDados.obterConexao();
			stmt = conexao.prepareStatement("SELECT * FROM T_SIP_USUARIO");
			rs = stmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString("DS_EMAIL");
				String nome = rs.getString("NM_USUARIO");
				String senha = rs.getString("DS_SENHA");

				Usuario usuario = new Usuario(email, nome, senha);

				lista.add(usuario);
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

	//Remove um usuario
	public void remover(String email) {
		PreparedStatement stmt = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "DELETE FROM T_SIP_USUARIO WHERE DS_EMAIL = ?";
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
				e.printStackTrace();
			}
		}
	}

	//busca um usuario em especifico usando o email
	public Usuario buscar(String email) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuarioEncontrado = null;

		try {
			conexao = BancoDados.obterConexao();
			String sql = "SELECT * FROM T_SIP_USUARIO WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuarioEncontrado = new Usuario();
				usuarioEncontrado.setNome(rs.getString("NM_USUARIO"));
				usuarioEncontrado.setEmail(rs.getString("DS_EMAIL"));
				usuarioEncontrado.setSenha(rs.getString("DS_SENHA"));
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
		return usuarioEncontrado;
	}
}
