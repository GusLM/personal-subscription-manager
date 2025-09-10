package model.service;

import exceptions.AssinaturaDuplicadaException;
import exceptions.DadosAssinaturaInvalidosException;
import model.dao.AssinaturaDAO;
import model.domain.Assinatura;
import model.enums.CicloPagamento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço que contém toda a lógica de negócio para o gerenciamento de assinaturas.
 * Utiliza BigDecimal para todos os cálculos monetários, garantindo precisão.
 */

public class GerenciadorService {

    private final AssinaturaDAO assinaturaDAO;

    public GerenciadorService(AssinaturaDAO assinaturaDAO) {
        this.assinaturaDAO = assinaturaDAO;
    }

    public void adicionarAssinatura(Assinatura novaAssinatura)
        throws AssinaturaDuplicadaException, DadosAssinaturaInvalidosException {

            // Validação de campos obrigatórios e nulos
            if (novaAssinatura == null || novaAssinatura.getNome() == null || novaAssinatura.getNome().trim().isEmpty()) {
                throw new DadosAssinaturaInvalidosException("O nome da assinatura não pode ser nulo ou vazio.");
            }

            // Validação do preço (deve ser maior que zero)
            if (novaAssinatura.getPrecoBase().compareTo(BigDecimal.ZERO) <= 0) {
                throw new DadosAssinaturaInvalidosException("O preço da assinatura deve ser maior que zero.");
            }

            // Verificação de unicidade (nome duplicado)
            Optional<Assinatura> assinaturaExistente = assinaturaDAO.buscarPorNome(novaAssinatura.getNome());
            if (assinaturaExistente.isPresent()) {
                throw new AssinaturaDuplicadaException("Já existe uma assinatura com o nome '\" + novaAssinatura.getNome() + \"'.");
            }

            // Se todas as regras passarem, salva a assinatura.
            assinaturaDAO.salvar(novaAssinatura);
        }

    public boolean removerPorNome(String nome) {
        return assinaturaDAO.removerPorNome(nome);
    }

    public List<Assinatura> listarTodas() {
        return assinaturaDAO.listarTodas();
    }

    public BigDecimal calcularCustoMensalTotal() {
        List<Assinatura> todasAssinaturas = assinaturaDAO.listarTodas();
        BigDecimal totalMensal = BigDecimal.ZERO;

        // Constante para o número de meses no ano, para o cálculo.
        final BigDecimal MESES_NO_ANO = new BigDecimal("12");

        for (Assinatura assinatura : todasAssinaturas) {
            if (assinatura.getCicloPagamento() == CicloPagamento.MENSAL) {
                totalMensal = totalMensal.add(assinatura.getPrecoBase());
            } else if (assinatura.getCicloPagamento() == CicloPagamento.ANUAL) {
                BigDecimal custoMensalAnual = assinatura.getPrecoBase().divide(MESES_NO_ANO, 2, RoundingMode.HALF_UP);
                totalMensal = totalMensal.add(custoMensalAnual);
            }
        }

        // Retorna o valor final com duas casas decimais.
        return totalMensal.setScale(2, RoundingMode.HALF_UP);

    }

}
