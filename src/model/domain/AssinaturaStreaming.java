package model.domain;

import model.enums.CicloPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssinaturaStreaming extends Assinatura {

    private String qualidadeVideo;
    private Integer maximoTelas;

    public AssinaturaStreaming(String nome, BigDecimal precoBase, CicloPagamento cicloPagamento, LocalDate dataContratacao, String qualidadeVideo, Integer maximoTelas) {
        super(nome, precoBase, cicloPagamento, dataContratacao);
        this.qualidadeVideo = qualidadeVideo;
        this.maximoTelas = maximoTelas;
    }

    public String getQualidadeVideo() {
        return qualidadeVideo;
    }

    public void setQualidadeVideo(String qualidadeVideo) {
        this.qualidadeVideo = qualidadeVideo;
    }

    public Integer getMaximoTelas() {
        return maximoTelas;
    }

    public void setMaximoTelas(Integer maximoTelas) {
        this.maximoTelas = maximoTelas;
    }

    @Override
    public String getDetalhes() {
        return getNome() + " - Qualidade: " + getQualidadeVideo() + " - Telas: " + getMaximoTelas();
    }

    @Override
    public BigDecimal calcularCustoMensal() {
        return null;
    }
}
