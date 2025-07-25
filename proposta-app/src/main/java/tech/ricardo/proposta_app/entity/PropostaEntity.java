package tech.ricardo.proposta_app.entity;

import jakarta.persistence.*;
import tech.ricardo.proposta_app.entity.enums.StatusProposta;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_proposta")
public class PropostaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "proposta_id")
    private UUID propostaId;

    @Column(name = "valor_solicitado", nullable = false)
    private BigDecimal valorSolicitado;

    @Column(name = "prazo_pagamento", nullable = false)
    private int prazoPagamento;

    @Column(name = "status_proposta", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusProposta statusProposta;

    @Column(name = "integrada")
    private boolean integrada;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "fk_proposta_usuario"))
    private UsuarioEntity usuarioEntity;

    public PropostaEntity() {
    }

    public UUID getPropostaId() {
        return propostaId;
    }

    public void setPropostaId(UUID propostaId) {
        this.propostaId = propostaId;
    }

    public BigDecimal getValorSolicitado() {
        return valorSolicitado;
    }

    public void setValorSolicitado(BigDecimal valorSolicitado) {
        this.valorSolicitado = valorSolicitado;
    }

    public int getPrazoPagamento() {
        return prazoPagamento;
    }

    public void setPrazoPagamento(int prazoPagamento) {
        this.prazoPagamento = prazoPagamento;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public void setStatusProposta(StatusProposta statusProposta) {
        this.statusProposta = statusProposta;
    }

    public boolean getIntegrada() {
        return integrada;
    }

    public void setIntegrada(boolean integrada) {
        this.integrada = integrada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PropostaEntity that = (PropostaEntity) o;
        return Objects.equals(propostaId, that.propostaId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(propostaId);
    }
}
