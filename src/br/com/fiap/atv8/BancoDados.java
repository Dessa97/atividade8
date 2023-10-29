package br.com.fiap.atv8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDados {
	 
	    public static Connection obterConexao() {
	      Connection conexao = null;

		String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
		String usuario = "rm98563";
		String senhaOracle = "010797";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        conexao = DriverManager.getConnection(url, usuario, senhaOracle);
			System.out.println("Conectou com sucesso!");
		} catch (ClassNotFoundException e) {
			System.out.println("Erro na conexão! " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro na conexão! " + e.getMessage());
		}
		return conexao;
	}

}


