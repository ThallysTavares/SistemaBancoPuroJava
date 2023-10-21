import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    private Map<String, Cliente> clientes; // Mapa para armazenar clientes por nome de usuário

    public Principal() {
        this.clientes = new HashMap<>();
    }

    public void cadastrarCliente(String nomeUsuario, String senha, int conta, char digito, String nome, double saldo) {
        Cliente cliente = new Cliente(conta, digito, nome, saldo);
        clientes.put(nomeUsuario, cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public Cliente realizarLogin(String nomeUsuario, String senha) {
        if (clientes.containsKey(nomeUsuario)) {
            Cliente cliente = clientes.get(nomeUsuario);
            if (cliente.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido!");
                return cliente;
            }
        }
        System.out.println("Usuário ou senha incorretos. Login falhou.");
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Principal banco = new Principal();

        System.out.println("Bem-vindo ao sistema bancário!");

        System.out.print("Digite o nome de usuário: ");
        String nomeUsuario = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite o número da conta: ");
        int conta = scanner.nextInt();

        System.out.print("Digite o dígito da conta: ");
        char digito = scanner.next().charAt(0);

        System.out.print("Digite o seu nome: ");
        String nomeCliente = scanner.next();

        System.out.print("Digite o saldo inicial: ");
        double saldoInicial = scanner.nextDouble();

        banco.cadastrarCliente(nomeUsuario, senha, conta, digito, nomeCliente, saldoInicial);

        // Após o cadastro, o cliente pode fazer login
        System.out.println("\nSistema de Login:");
        System.out.print("Digite o nome de usuário: ");
        String nomeUsuarioLogin = scanner.next();

        System.out.print("Digite a senha: ");
        String senhaLogin = scanner.next();

        Cliente cliente = banco.realizarLogin(nomeUsuarioLogin, senhaLogin);

        if (cliente != null) {
            // Menu de operações bancárias
            DecimalFormat df = new DecimalFormat("#0.00");
            boolean continuar = true;

            while (continuar) {
                System.out.println("\nEscolha a operação:");
                System.out.println("1 - Depositar");
                System.out.println("2 - Sacar");
                System.out.println("3 - Consultar Saldo");
                System.out.println("4 - Sair");

                int escolha = scanner.nextInt();

                switch (escolha) {
                    case 1:
                        System.out.print("Digite o valor para depositar: ");
                        double valorDeposito = scanner.nextDouble();
                        cliente.depositar(valorDeposito);
                        break;
                    case 2:
                        System.out.print("Digite o valor para sacar: ");
                        double valorSaque = scanner.nextDouble();
                        cliente.sacar(valorSaque);
                        break;
                    case 3:
                        System.out.println("Saldo atual: R$" + df.format(cliente.getSaldo()));
                        break;
                    case 4:
                        continuar = false;
                        System.out.println("Saindo do sistema. Obrigado!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        }

        scanner.close();
    }
}

class Cliente {
    private int conta;
    private char digito;
    private String nome;
    private double saldo;
    private String senha;



    public Cliente(int conta, char digito, String nome, double saldo) {
        this.conta = conta;
        this.digito = digito;
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
        System.out.println("Depósito de " + valor + " realizado. Novo saldo: R$" + String.format("%.2f", saldo));
    }

    public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente. Saque não realizado.");
        } else {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado. Novo saldo: R$" + String.format("%.2f", saldo));
        }
    }}
