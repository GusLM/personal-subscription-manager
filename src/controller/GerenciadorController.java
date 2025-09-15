package controller;

import model.domain.Assinatura;
import model.domain.AssinaturaServico;
import model.domain.AssinaturaSoftware;
import model.domain.AssinaturaStreaming;
import model.enums.CategoriaPlano;
import model.enums.CicloPagamento;
import model.service.GerenciadorService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GerenciadorController {

    private final GerenciadorService gerenciadorService;

    public GerenciadorController(GerenciadorService gerenciadorService) {
        this.gerenciadorService = gerenciadorService;
    }

    public void adicionarAssinatura(Assinatura assinatura) {
        try {
            gerenciadorService.adicionarAssinatura(assinatura);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarAssinaturaStreaming(String nome, BigDecimal preco, CicloPagamento ciclo, LocalDate data, String qualidade, int telas) {
        AssinaturaStreaming novaAssinatura = new AssinaturaStreaming(nome, preco, ciclo, data, qualidade, telas);
        adicionarAssinatura(novaAssinatura);
    }

    public void adicionarAssinaturaSoftware(String nome, BigDecimal preco, CicloPagamento ciclo, LocalDate data, String chave, int dispositivos) {
        AssinaturaSoftware novaAssinatura = new AssinaturaSoftware(nome, preco, ciclo, data, chave, dispositivos);
        adicionarAssinatura(novaAssinatura);
    }

    public void adicionarAssinaturaServico(String nome, BigDecimal preco, CicloPagamento ciclo, LocalDate data, CategoriaPlano categoria) {
        AssinaturaServico novaAssinatura = new AssinaturaServico(nome, preco, ciclo, data, categoria);
        adicionarAssinatura(novaAssinatura);
    }

    public boolean removerPorNome(String nome) {
        return gerenciadorService.removerPorNome(nome);
    }

    public List<Assinatura> listarTodas() {
        return gerenciadorService.listarTodas();
    }

    public BigDecimal calcularCustoMensalTotal() {
        return gerenciadorService.calcularCustoMensalTotal();
    }

}
