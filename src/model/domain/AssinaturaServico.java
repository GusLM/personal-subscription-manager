package model.domain;

import model.enums.CategoriaPlano;
import model.enums.CicloPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssinaturaServico extends Assinatura {
    private CategoriaPlano tipoPlano;
    private final List<String> beneficios = new ArrayList<>();

    public AssinaturaServico(String nome, BigDecimal precoBase, CicloPagamento cicloPagamento, LocalDate dataContratacao, CategoriaPlano tipoPlano) {
        super(nome, precoBase, cicloPagamento, dataContratacao);
        this.tipoPlano = tipoPlano;
    }

    public CategoriaPlano getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(CategoriaPlano tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public List<String> getBeneficios() {
        return beneficios;
    }

    public void adicionarBeneficio(String beneficio) {
        beneficios.add(beneficio);
    }

    public void removerBeneficio(String beneficio) {
        beneficios.remove(beneficio);
    }

    @Override
    public String getDetalhes() {
        return getNome() + " - Categoria: " + getTipoPlano();
    }

    @Override
    public BigDecimal calcularCustoMensal() {
        return null;
    }
}