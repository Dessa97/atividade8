package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Meta;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.MetaDAO;

import java.time.LocalDate;

public class TesteCriaMeta {

    public static void main(String [] args){

        //Busca uma conta já existente
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscar(21);

        //Cria uma meta para a conta fornecida
        MetaDAO metaDAO = new MetaDAO();

        Meta meta = new Meta();
        meta.setConta(conta);
        meta.setData(LocalDate.of(2023, 10, 28));
        meta.setDescricao("Ganhar petisco");
        meta.setValor(1000.00);

        metaDAO.cadastrar(meta);

        System.out.println("Meta Cadastrado!");
    }
}
