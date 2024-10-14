package main.java.br.edu.gildersonsantos.bancodigital.model.banco;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import main.java.br.edu.gildersonsantos.bancodigital.model.cliente.Cliente;
import main.java.br.edu.gildersonsantos.bancodigital.model.cliente.CpfValidator;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.TipoConta;

public class Banco implements IBanco {
    private String nome;
    private Set<Cliente> clienteSet;
    
    public Banco(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }

        this.nome = nome;
        this.clienteSet = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCliente(String nome, String cpf, LocalDate dataNascimento, TipoConta tipoConta) {
        clienteSet.add(new Cliente(nome, cpf, dataNascimento, tipoConta));
        imprimirAviso("Cliente adicionado com sucesso: " + nome);
    }

    public void imprimirClientes() {
        System.out.println(clienteSet);
    }

    public void removerCliente(String cpf) {
        if (!clienteSet.isEmpty()) {
            String cpfArgumento = cpf.replaceAll("[^0-9]", "").replaceAll("\\s+", "");
    
            Optional<Cliente> clienteParaRemover = clienteSet.stream().
                filter(cliente -> {
                    String clienteCpf = cliente.getCpf().replaceAll("[^0-9]", "").replaceAll("\\s+", "");
                    
                    return clienteCpf.equals(cpfArgumento);
                })
                .findFirst();
            
            clienteParaRemover.ifPresent(cliente -> {
                clienteSet.remove(cliente);
                imprimirAviso("Cliente removido com sucesso: " + cliente.getNome());
            });
            
            if (!clienteParaRemover.isPresent()) {
                imprimirAviso("Nenhum cliente encontrado com o CPF especificado.");
            }
        };
    }

    public void removerClientePorConta(int agencia, int numero) {
        if (!clienteSet.isEmpty()) {
            Optional<Cliente> clienteParaRemover = clienteSet.stream().
                filter(cliente -> (cliente.getConta().getAgencia() == agencia) && (cliente.getConta().getNumero() == numero))
                .findFirst();
            
            clienteParaRemover.ifPresent(cliente -> {
                clienteSet.remove(cliente);
                imprimirAviso("Cliente removido com sucesso: " + cliente.getNome());
            });
            
            if (!clienteParaRemover.isPresent()) {
                imprimirAviso("Nenhum cliente encontrado com a conta especificada.");
            }
        };
    }

    public Optional<Cliente> pesquisarClientePorCpf(String cpf) {
        if (clienteSet.isEmpty()) {
            imprimirAviso("Não há clientes registrados para serem verificados no momento.");
            return Optional.empty();
        }
        
        String cpfArgumento = CpfValidator.limparCPF(cpf);

        Optional<Cliente> clientePorCpf = clienteSet.stream()
            .filter(cliente -> {
                String clienteCpf = CpfValidator.limparCPF(cliente.getCpf());
                
                return clienteCpf.equals(cpfArgumento);
            })
            .findFirst();
    
        if (clientePorCpf.isPresent()) {
            imprimirAviso("O cliente " + clientePorCpf.get().getNome() + " foi encontrado, mediante o CPF especificado.");
        } else {
            imprimirAviso("Nenhum cliente encontrado com o CPF especificado.");
        }
    
        return clientePorCpf;
    }

    public Optional<Cliente> pesquisarClientePorConta(int agencia, int numero) {
        if (clienteSet.isEmpty()) {
            imprimirAviso("Não há clientes registrados para serem verificados no momento.");
            return Optional.empty();
        }

        Optional<Cliente> clientePorConta = clienteSet.stream()
            .filter(cliente -> (cliente.getConta().getAgencia() == agencia) && (cliente.getConta().getNumero() == numero))
            .findFirst();
    
        if (clientePorConta.isPresent()) {
            imprimirAviso("O cliente " + clientePorConta.get().getNome() + " foi encontrado, mediante a agencia e número especificados.");
        } else {
            imprimirAviso("Nenhum cliente encontrado com o CPF especificado.");
        }
    
        return clientePorConta;
    }

    private void imprimirAviso(String mensagem) {
        System.out.println("(!): " + mensagem);
    }
}