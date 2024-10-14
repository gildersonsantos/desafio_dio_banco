package main.java.br.edu.gildersonsantos.bancodigital.exceptions;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
