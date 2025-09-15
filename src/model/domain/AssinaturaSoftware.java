package model.domain;

import model.enums.CicloPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssinaturaSoftware extends Assinatura {
    private String chaveLicenca;
    private Integer maximoDispositivos;

    public AssinaturaSoftware(String nome, BigDecimal precoBase, CicloPagamento cicloPagamento, LocalDate dataContratacao, String chaveLicenca, Integer maximoDispositivos) {
        super(nome, precoBase, cicloPagamento, dataContratacao);
        this.chaveLicenca = chaveLicenca;
        this.maximoDispositivos = maximoDispositivos;
    }

    public String getChaveLicenca() {
        return chaveLicenca;
    }

    public void setChaveLicenca(String chaveLicenca) {
        this.chaveLicenca = chaveLicenca;
    }

    public Integer getMaximoDispositivos() {
        return maximoDispositivos;
    }

    public void setMaximoDispositivos(Integer maximoDispositivos) {
        this.maximoDispositivos = maximoDispositivos;
    }

    @Override
    public String getDetalhes() {
        return getNome() + " - Dispositivos: " + getMaximoDispositivos();
    }

    @Override
    public BigDecimal calcularCustoMensal() {
        return null;
    }
}