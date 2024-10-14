package main.java.br.edu.gildersonsantos.bancodigital.model.conta;

import java.time.LocalDate;

import main.java.br.edu.gildersonsantos.bancodigital.exceptions.SaldoInsuficienteException;

public class ContaCorrente extends Conta {
    private double limiteChequeEspecial; 
    private double taxaMensal; 
    private LocalDate dataUltimaTaxa; 

    public ContaCorrente() {
        super();
        this.tipoDaConta = "Conta Corrente";
        this.limiteChequeEspecial = 500; 
        this.taxaMensal = 10; 
        this.dataUltimaTaxa = LocalDate.now();
    }
    
    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public double getTaxaMensal() {
        return taxaMensal;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if ((saldo + limiteChequeEspecial) >= valor) {
            if (saldo >= valor) {
                saldo -= valor;
            } else {
                double valorUsadoDoLimite = valor - saldo; 
                saldo = 0;
                limiteChequeEspecial -= valorUsadoDoLimite; 
            }
            ultimaMovimentacao = LocalDate.now();
        } else {
            throw new SaldoInsuficienteException("Saldo e limite insuficiente para realizar o saque.");
        }
    }

    public void aumentarLimite(double valor) {
        limiteChequeEspecial += valor;
        imprimirAviso("Limite aumentado em R$ " + valor);
    }

    public void aplicarTaxa() {
        if (LocalDate.now().isAfter(dataUltimaTaxa.plusMonths(1))) {
            saldo -= taxaMensal;
            dataUltimaTaxa = LocalDate.now();

            imprimirAviso("Taxa mensal de R$ " + taxaMensal + "aplicada.");
        }
    }

    public void imprimirExtrato() {
        System.out.println("\n=====    Extrato Conta Corrente    =====");
        imprimirInformacoes();
        System.out.println("Limite: R$ " + limiteChequeEspecial);
        System.out.println("");
    }
}
