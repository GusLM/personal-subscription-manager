package model.domain;

import model.enums.CicloPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Assinatura {
    private String nome;
    private BigDecimal precoBase;
    private CicloPagamento cicloPagamento;
    private LocalDate dataContratacao;

    public Assinatura(String nome, BigDecimal precoBase, CicloPagamento cicloPagamento, LocalDate dataContratacao) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.cicloPagamento = cicloPagamento;
        this.dataContratacao = dataContratacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(BigDecimal precoBase) {
        this.precoBase = precoBase;
    }

    public CicloPagamento getCicloPagamento() {
        return cicloPagamento;
    }

    public void setCicloPagamento(CicloPagamento cicloPagamento) {
        this.cicloPagamento = cicloPagamento;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public abstract String getDetalhes();

    public abstract BigDecimal calcularCustoMensal();

}
