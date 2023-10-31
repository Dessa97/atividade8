package br.com.fiap.atv8.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import br.com.fiap.atv8.BancoDados;
import br.com.fiap.atv8.classes.Entrada;
import br.com.fiap.atv8.classes.Meta;
import br.com.fiap.atv8.classes.Operacoes;
import br.com.fiap.atv8.classes.Saida;
import errors.ErroClasseInvalida;

public abstract class OperacoesDAO {
	private Connection conexao;
	
	//Método cadastrar que irá ser usado por Entrada, Saida e Meta para cadastrar
	//as entradas, saidas e metas. A entrada dessa função pode ser uma Entrada ou Meta
	//ou Saida.
	public void cadastrar(Operacoes operacoes){
		PreparedStatement stmt = null;
		
		try {
			conexao = BancoDados.obterConexao();
			
			//A função 'constroiCadastroQuery' vai construir a Query para cadastrar na
			//tabela de banco de dados a entrada, saida ou meta. O retorno desse método
			//ficará salvo na variável "sql"
			String sql = constroiCadastroQuery(operacoes);
			
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, operacoes.getConta().getId());
			stmt.setString(2, operacoes.getDescricao());
			stmt.setDouble(3, operacoes.getValor());
			stmt.setDate(4, Date.valueOf(operacoes.getData()));

			stmt.executeUpdate();
			
			//SE OPERAÇOES FOR ENTRADA OU SAIDA TEM QUE ATUALIZAR O SALDO DE CONTA
			atualizaSaldoConta(operacoes);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ErroClasseInvalida e) {
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
	
	//Esse método é responsável por construir a Query para cadastrar a entrada, saida
	//ou meta. Já que as classes Entrada, Saida e Meta são filhas da classe Operacoes
	//podemos descobrir qual delas está sendo enviada para este método através do uso
	//de "instance of". Dessa forma esse método constrói a query correta para cada uma
	//das três classes.
	private String constroiCadastroQuery(Operacoes operacoes) throws ErroClasseInvalida {
		if(operacoes instanceof Meta) {
			return "INSERT INTO T_SIP_META(ID_META, ID_CONTA, DS_META, VL_META, DT_META)" + 
		" VALUES (SQ_META.NEXTVAL, ?, ?, ?, ?)";
		}else if(operacoes instanceof Entrada) {
			return "INSERT INTO T_SIP_ENTRADA(ID_ENTRADA, ID_CONTA, DS_ENTRADA, VL_ENTRADA, DT_ENTRADA)" +
		" VALUES (SQ_ENTRADA.NEXTVAL, ?, ?, ?, ?)";
		}else if(operacoes instanceof Saida) {
			return "INSERT INTO T_SIP_SAIDA(ID_SAIDA, ID_CONTA, DS_SAIDA, VL_SAIDA, DT_SAIDA)" +
		" VALUES (SQ_SAIDA.NEXTVAL, ?, ?, ?, ?)";
		}
		throw new ErroClasseInvalida("Operacoes é de um tipo diferente de: Meta, Entrada ou Saida");
	}
	
	private void atualizaSaldoConta(Operacoes operacoes) {
		ContaDAO contaDAO = new ContaDAO();
		if(operacoes instanceof Entrada || operacoes instanceof Saida) {
			contaDAO.atualizaSaldo(operacoes.getConta());
		}
	}
	
	//Esse método é responsável por converter Date para LocalDate. Já que MetaDAO,
	//SaidaDAO e EntradaDAO são filhas de OperacoesDAO elas podem acessar esse método
	//já que ele é protected.
	protected LocalDate converteDateParaLocalDate(Date data) {
        return data.toLocalDate();
	}
}
