package main.java.br.edu.gildersonsantos.bancodigital.model.conta;

import java.time.LocalDate;
import main.java.br.edu.gildersonsantos.bancodigital.exceptions.SaldoInsuficienteException;

public abstract class Conta implements IConta {
    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected String tipoDaConta;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected LocalDate dataAbertura;
    protected LocalDate ultimaMovimentacao;
    
    public Conta() {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.saldo = 0;
        this.dataAbertura = LocalDate.now();
        this.ultimaMovimentacao = this.dataAbertura;
    }
    
    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public LocalDate getUltimaMovimentacao() {
        return ultimaMovimentacao;
    }

    public String getTipoDaConta() {
        return tipoDaConta;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (saldo >= valor) {
            saldo -= valor;
            this.ultimaMovimentacao = LocalDate.now();
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para o saque.");
        }
    }

    public void depositar(double valor) {
        saldo += valor;
        this.ultimaMovimentacao = LocalDate.now();
        imprimirAviso("Deposito de R$ " + valor + " , concluído com sucesso.");
    }
    
    public void transferir(double valor, Conta contaDestino) throws SaldoInsuficienteException {
        this.sacar(valor);
        contaDestino.depositar(valor);
        this.ultimaMovimentacao = LocalDate.now();

        System.out.println(String.format("\nTransferência de R$ %.2f concluída com sucesso.\n[De: Agência %s / Conta %s] - [Para: Agência %s / Conta %s]\n", valor, agencia, numero, contaDestino.getAgencia(), contaDestino.getNumero()));
    }
    
    public void imprimirAviso(String mensagem) {
        System.out.println("(!): " + mensagem);
    }
    
    protected void imprimirInformacoes() {
        System.out.println(String.format("Agência: %s | Conta: %s | Saldo: R$ %.2f\n", agencia, numero, saldo));
    }
}
