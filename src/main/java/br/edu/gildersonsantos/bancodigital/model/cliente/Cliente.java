package main.java.br.edu.gildersonsantos.bancodigital.model.cliente;

import java.time.LocalDate;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.Conta;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.ContaCorrente;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.ContaPoupanca;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.TipoConta;

public class Cliente {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Conta conta; 

    public Cliente(String nome, String cpf, LocalDate dataNascimento, TipoConta tipoConta) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;

        if (cpf == null) {
            throw new IllegalArgumentException("O CPF não pode ser nulo.");
        }

        if (CpfValidator.isValidCPF(cpf)) {
            System.out.println("Cheguei");
            this.cpf = CpfValidator.limparCPF(cpf);
        } else {
            throw new IllegalArgumentException("O CPF " + CpfValidator.formatarCPF(cpf) + " é inválido.");
        }

        if (dataNascimento == null) {
            throw new IllegalArgumentException("A data de nascimento não pode ser nula.");
        }

        this.dataNascimento = dataNascimento;

        if (tipoConta == null) {
            throw new IllegalArgumentException("O tipo de conta não pode ser nulo.");
        }

        if (tipoConta == TipoConta.CORRENTE) {
            this.conta = new ContaCorrente();
        } else if (tipoConta == TipoConta.POUPANCA) {
            this.conta = new ContaPoupanca(); 
        } else {
            throw new IllegalArgumentException("Tipo de conta desconhecido: " + tipoConta);
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Conta getConta() {
        return conta;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((conta == null) ? 0 : conta.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (conta == null) {
            if (other.conta != null)
                return false;
        } else if (!conta.equals(other.conta))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente { nome: " + nome + ", cpf: " + CpfValidator.formatarCPF(cpf) + ", dataNascimento: " + dataNascimento + ", " + conta.getTipoDaConta() + "[ag/n]: " + conta.getAgencia() + "/" + conta.getNumero() + " }";
    }
}
