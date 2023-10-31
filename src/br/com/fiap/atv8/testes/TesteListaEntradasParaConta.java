package br.com.fiap.atv8.testes;

import br.com.fiap.atv8.classes.Conta;
import br.com.fiap.atv8.classes.Entrada;
import br.com.fiap.atv8.daos.ContaDAO;
import br.com.fiap.atv8.daos.EntradaDAO;

import java.util.List;

public class TesteListaEntradasParaConta {

    public static void main(String[] args) {
        EntradaDAO entradaDAO = new EntradaDAO();
        List<Entrada> listaEntrada = entradaDAO.listar(43);
        if(listaEntrada.size() != 0){
            System.out.println("Lista de Entradas para o usuário " + listaEntrada.get(0).getConta().getUsuario().getNome() + ":");
            for(int i = 0; i < listaEntrada.size(); i++){
                System.out.println(listaEntrada.get(i).getDescricao());
            }
        }else {
            System.out.println("Lista de Entrada vazia para o usuário");
        }

    }
}
