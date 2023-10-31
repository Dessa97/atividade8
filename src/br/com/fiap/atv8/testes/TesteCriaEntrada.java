package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Entrada;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.EntradaDAO;

import java.time.LocalDate;

public class TesteCriaEntrada {
    public static void main(String [] args){
        //Busca uma conta já existente
        ContaDAO contaDAO = new ContaDAO();
        Conta conta = contaDAO.buscar(43);

        //Cria uma Entrada para a conta fornecida
        EntradaDAO entradaDAO = new EntradaDAO();

        Entrada entrada = new Entrada();
        entrada.setConta(conta);
        entrada.setData(LocalDate.of(2023, 10, 28));
        entrada.setDescricao("Ganhei Petisco");
        entrada.setValor(1000.00);

        entradaDAO.cadastrar(entrada);

        System.out.println("Entrada Cadastrado!");
    }
}
