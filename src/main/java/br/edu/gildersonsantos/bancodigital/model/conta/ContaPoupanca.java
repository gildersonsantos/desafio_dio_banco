package main.java.br.edu.gildersonsantos.bancodigital.model.conta;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContaPoupanca extends Conta {
    private double taxaJuros; 
    protected LocalDate dataUltimaAplicacaoTaxa;

    public ContaPoupanca() {
        super();
        this.tipoDaConta = "Conta Poupança";
        this.taxaJuros = 5;
        this.dataUltimaAplicacaoTaxa = LocalDate.now();
    }

    public double getTaxaJuros() {
        return taxaJuros;
    }

    public void aplicarTaxa() {
        if (LocalDate.now().isAfter(dataUltimaAplicacaoTaxa.plusMonths(1))) {
            long diasDesdeUltimaMovimentacao = ChronoUnit.DAYS.between(ultimaMovimentacao, LocalDate.now());
            int totalDiasDoMes = dataUltimaAplicacaoTaxa.lengthOfMonth();
            double juros;
    
            if (diasDesdeUltimaMovimentacao >= totalDiasDoMes) {
                juros = saldo * (taxaJuros / 100);
            } else {
                juros = (saldo * (taxaJuros / 100) * diasDesdeUltimaMovimentacao) / totalDiasDoMes;
            }

            saldo += juros; 
            dataUltimaAplicacaoTaxa = LocalDate.now();

            imprimirAviso("Taxa de juros aplicada: R$ " + juros);
        }
    }

    public void imprimirExtrato() {
        System.out.println("\n=====    Extrato Conta Poupança    =====");
        imprimirInformacoes();
        System.out.println("");
    }
}
