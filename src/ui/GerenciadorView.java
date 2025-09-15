package ui;

import controller.GerenciadorController;
import model.enums.CategoriaPlano;
import model.enums.CicloPagamento;
import util.validators.InputValidador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GerenciadorView {

    private final GerenciadorController controller;
    private final Scanner sc = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GerenciadorView(GerenciadorController controller) {
        this.controller = controller;
    }

    public void rodar() {
        boolean rodando = true;

        while (rodando) {
            exibirMenu();
            int opcao = InputValidador.lerInteiroValido(sc, "/n Selecione uma opção válida.");

            switch (opcao) {
                case 1 -> adicionarAssinatura();
            }
        }
    }

    private void exibirMenu() {
        System.out.println("==============================================");
        System.out.println("===         BEM-VINDO AO ASSINAHUB         ===");
        System.out.println("===   Seu Gerenciador de Assinaturas Pessoais  ===");
        System.out.println("==============================================");
        System.out.println();
        System.out.println("O que você gostaria de fazer?");
        System.out.println();
        System.out.println("[1] Adicionar Nova Assinatura");
        System.out.println("[2] Listar Todas as Assinaturas");
        System.out.println("[3] Remover Assinatura");
        System.out.println("[4] Calcular Custo Mensal Total");
        System.out.println("[5] Sair");
    }

    private void adicionarAssinatura() {
        System.out.println("--- ADICIONAR NOVA ASSINATURA ---");
        System.out.println();
        System.out.println("Qual o tipo de assinatura?");
        System.out.println("[1] Streaming");
        System.out.println("[2] Software");
        System.out.println("[3] Serviço (Ex: Academia)");
        System.out.println();
        System.out.print("Digite o tipo: ");

        int tipo = InputValidador.lerInteiroValido(sc, "\nSelecione uma opção válida.");


        switch (tipo) {
            case 1 -> processarAdicaoStreaming();
            case 2 -> processarAdicaoSoftware();
            case 3 -> processarAdicaoServico();
            default -> System.out.println("❌ Opção inválida! Por favor, digite a opção correspondente.");
        }

    }

    private CicloPagamento obterCicloPagamentoPorString() {
        CicloPagamento cicloSelecionado = null;

        while (cicloSelecionado == null) {
            System.out.print("Ciclo de Pagamento (MENSAL/ANUAL): ");
            String entradaUsuario = sc.nextLine().trim(); // Lê a entrada e remove espaços

            try {
                // Tenta converter a string (em maiúsculas) para o enum correspondente
                cicloSelecionado = CicloPagamento.valueOf(entradaUsuario.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Se a conversão falhar (usuário digitou algo inválido)
                System.out.println("❌ Opção inválida! Por favor, digite MENSAL ou ANUAL.");
            }
        }
        return cicloSelecionado;
    }

    private CategoriaPlano obterCategoriaPorString() {
        CategoriaPlano categoriaPlano = null;

        while (categoriaPlano == null) {
            System.out.print("Categoria do Plano (BASICO/PADRAO/PLUS/PREMIUM/ULTIMATE): ");
            String entradaUsuario = sc.nextLine().trim(); // Lê a entrada e remove espaços

            try {
                // Tenta converter a string (em maiúsculas) para o enum correspondente
                categoriaPlano = CategoriaPlano.valueOf(entradaUsuario.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Se a conversão falhar (usuário digitou algo inválido)
                System.out.println("❌ Opção inválida! Por favor, digite uma categoria disponível.");
            }
        }
        return categoriaPlano;
    }

    private DadosComunsAssinatura coletarDadosComuns() {
        System.out.print("Nome da assinatura: ");
        String nome = sc.nextLine();

        System.out.print("Preço (ex: 45.90): ");
        BigDecimal preco = new BigDecimal(sc.nextLine());

        CicloPagamento ciclo = obterCicloPagamentoPorString();

        System.out.print("Data da contratação (dd/MM/yyyy): ");
        LocalDate dataContratacao = LocalDate.parse(sc.nextLine(), formatter);

        return new DadosComunsAssinatura(nome, preco, ciclo, dataContratacao);
    }

    // --- MÉTODOS ESPECÍFICOS PARA CADA TIPO ---

    private void processarAdicaoStreaming() {
        System.out.println("\n--- DADOS DA ASSINATURA DE STREAMING ---");
        DadosComunsAssinatura dadosComuns = coletarDadosComuns();

        System.out.print("Qualidade de vídeo (HD/4K): ");
        String qualidadeVideo = sc.nextLine();

        System.out.print("Número de telas: ");
        int numeroTelas = Integer.parseInt(sc.nextLine());

        controller.adicionarAssinaturaStreaming(dadosComuns.nome, dadosComuns.preco, dadosComuns.ciclo, dadosComuns.data, qualidadeVideo, numeroTelas);
    }

    private void processarAdicaoSoftware() {
        System.out.println("\n--- DADOS DA ASSINATURA DE SOFTWARE ---");
        DadosComunsAssinatura dadosComuns = coletarDadosComuns();

        System.out.print("Chave de licença: ");
        String chave = sc.nextLine();

        System.out.print("Número máximo de dispositivos: ");
        int maximoDispositivos = Integer.parseInt(sc.nextLine());

        controller.adicionarAssinaturaSoftware(dadosComuns.nome, dadosComuns.preco, dadosComuns.ciclo, dadosComuns.data, chave, maximoDispositivos);
    }

    private void processarAdicaoServico() {
        System.out.println("\n--- DADOS DA ASSINATURA DE SERVIÇO ---");
        DadosComunsAssinatura dadosComuns = coletarDadosComuns();

        CategoriaPlano categoria = obterCategoriaPorString();

        controller.adicionarAssinaturaServico(dadosComuns.nome, dadosComuns.preco, dadosComuns.ciclo, dadosComuns.data, categoria);
    }

    record DadosComunsAssinatura(String nome, BigDecimal preco, CicloPagamento ciclo, LocalDate data) {}
}
