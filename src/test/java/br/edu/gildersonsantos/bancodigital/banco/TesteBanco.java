package test.java.br.edu.gildersonsantos.bancodigital.banco;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import main.java.br.edu.gildersonsantos.bancodigital.model.banco.Banco;
import main.java.br.edu.gildersonsantos.bancodigital.model.cliente.Cliente;
import main.java.br.edu.gildersonsantos.bancodigital.model.conta.TipoConta;

public class TesteBanco {
    public static void main(String[] args) {
        // Criando uma instância de Banco
        Banco banco = new Banco("Banco Digital");
        banco.adicionarCliente("João Silva", "709.721.750-24", LocalDate.of(1974, Month.DECEMBER, 15), TipoConta.POUPANCA);

        // Testando se o banco e o cliente foram criados corretamente
        System.out.println("Banco: " + banco.getNome());

        // Optional<Cliente> cliente = banco.pesquisarClientePorCpf("709.721.750-24,");
        Optional<Cliente> cliente = banco.pesquisarClientePorConta(1, 1);

        cliente.ifPresent(c -> {
            c.getConta().imprimirExtrato();
        });

        banco.imprimirClientes();
        banco.removerClientePorConta(1, 1);
        banco.imprimirClientes();

    }
}

