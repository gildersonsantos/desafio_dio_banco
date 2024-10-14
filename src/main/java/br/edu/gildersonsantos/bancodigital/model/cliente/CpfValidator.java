package main.java.br.edu.gildersonsantos.bancodigital.model.cliente;

public class CpfValidator {

    // Verifica se o CPF é válido
    public static boolean isValidCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = limparCPF(cpf);

        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se o CPF é composto apenas por dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Validação dos dígitos verificadores
        int firstCheckDigit = calculateCheckDigit(cpf.substring(0, 9));
        int secondCheckDigit = calculateCheckDigit(cpf.substring(0, 9) + firstCheckDigit);

        // Verifica se os dígitos verificadores estão corretos
        return cpf.charAt(9) == (char) (firstCheckDigit + '0') && cpf.charAt(10) == (char) (secondCheckDigit + '0');
    }

    // Remove caracteres não numéricos e espaços do CPF
    public static String limparCPF(String cpf) {
        return cpf.replaceAll("[^0-9]", "").replaceAll("\\s+", "");
    }

    // Formata o CPF no padrão "XXX.XXX.XXX-XX"
    public static String formatarCPF(String cpf) {
        String cpfLimpo = limparCPF(cpf); // Primeiro, limpa o CPF

        if (cpfLimpo.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos.");
        }

        return String.format("%s.%s.%s-%s",
                cpfLimpo.substring(0, 3),
                cpfLimpo.substring(3, 6),
                cpfLimpo.substring(6, 9),
                cpfLimpo.substring(9, 11)
        );
    }

    // Cálculo do dígito verificador
    private static int calculateCheckDigit(String base) {
        int sum = 0;
        for (int i = 0; i < base.length(); i++) {
            sum += (base.length() + 1 - i) * (base.charAt(i) - '0');
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}
