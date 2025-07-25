package tech.ricardo.proposta_app.controller.dto;

import tech.ricardo.proposta_app.entity.enums.StatusProposta;

import java.math.BigDecimal;
import java.util.UUID;

public record PropostaPaginadaResponseDto(UUID propostaId,
                                          BigDecimal valorSolicitado,
                                          int prazoPagamento,
                                          StatusProposta statusProposta,
                                          boolean integrada,
                                          String observacao,
                                          UsuarioResponseDto usuario) {
}
