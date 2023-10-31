package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Meta;
import br.com.fiap.atv8.classes.Saida;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.MetaDAO;
import br.com.fiap.atv8.daos.SaidaDAO;

import java.util.List;

public class TesteListaMetaParaConta {
    public static void main(String[] args) {
        MetaDAO metaDAO = new MetaDAO();
        List<Meta> listaMeta = metaDAO.listar(43);
        if(listaMeta.size() != 0){
            System.out.println("Lista de Metas para o usuário " + listaMeta.get(0).getConta().getUsuario().getNome() + ":");
            for(int i = 0; i < listaMeta.size(); i++){
                System.out.println(listaMeta.get(i).getDescricao());
            }
        }else {
            System.out.println("Lista de Metas vazia para o usuario");
        }

    }
}
