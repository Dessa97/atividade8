package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Saida;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.SaidaDAO;

import java.time.LocalDate;

public class TesteCriaSaida {
    public static void main(String[] args) {
        //Busca uma conta já existente
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscar(21);

        //Cria uma Saida para a conta fornecida
        SaidaDAO saidaDAO = new SaidaDAO();

        Saida saida = new Saida();
        saida.setConta(conta);
        saida.setData(LocalDate.of(2023, 10, 29));
        saida.setDescricao("Bela comeu meu Petisco");
        saida.setValor(500.00);

        saidaDAO.cadastrar(saida);

        System.out.println("Saida Cadastrado!");
    }
}
