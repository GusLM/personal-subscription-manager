package controller;

import model.domain.Assinatura;
import model.service.GerenciadorService;

import java.math.BigDecimal;
import java.util.List;

public class GerenciadorController {

    private final GerenciadorService gerenciadorService;

    public GerenciadorController(GerenciadorService gerenciadorService) {
        this.gerenciadorService = gerenciadorService;
    }

    public void adicionarAssinatura(Assinatura assinatura) {
        gerenciadorService.adicionarAssinatura(assinatura);
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
