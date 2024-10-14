package main.java.br.edu.gildersonsantos.bancodigital.model.banco;

import java.time.LocalDate;
import java.util.Optional;

import main.java.br.edu.gildersonsantos.bancodigital.model.cliente.Cliente;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.TipoConta;

public interface IBanco {
    void adicionarCliente(String nome, String cpf, LocalDate dataNascimento, TipoConta tipoConta);
    void imprimirClientes();
    void removerCliente(String cpf);
    void removerClientePorConta(int agencia, int numero);
    Optional<Cliente> pesquisarClientePorCpf(String cpf);
    Optional<Cliente> pesquisarClientePorConta(int agencia, int numero);
}
