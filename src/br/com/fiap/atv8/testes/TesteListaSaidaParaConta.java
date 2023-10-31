package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Entrada;
import br.com.fiap.atv8.classes.Saida;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.EntradaDAO;
import br.com.fiap.atv8.daos.SaidaDAO;

import java.util.List;

public class TesteListaSaidaParaConta {
    public static void main(String[] args) {
        SaidaDAO saidaDAO = new SaidaDAO();
        List<Saida> listaSaida = saidaDAO.listar(43);
        if(listaSaida.size() != 0){
            System.out.println("Lista de Saidas para o usuário " + listaSaida.get(0).getConta().getUsuario().getNome() + ":");
            for(int i = 0; i < listaSaida.size(); i++){
                System.out.println(listaSaida.get(i));
            }
        }else {
            System.out.println("Lista de Saida Vazia");
        }
    }
}
