package main.java.br.edu.gildersonsantos.bancodigital.model.conta;

import main.java.br.edu.gildersonsantos.bancodigital.exceptions.SaldoInsuficienteException;

public interface IConta {
    void sacar(double valor) throws SaldoInsuficienteException;
    void depositar(double valor);
    void transferir(double valor, Conta contaDestino) throws SaldoInsuficienteException;
    void imprimirExtrato();
}
