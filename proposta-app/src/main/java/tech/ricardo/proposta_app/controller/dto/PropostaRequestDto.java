package tech.ricardo.proposta_app.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PropostaRequestDto(@NotBlank
                                 String nome,
                                 @NotBlank
                                 String sobrenome,
                                 @NotBlank
                                 String telefone,
                                 @NotBlank
                                 String cpf,
                                 @DecimalMin(value = "0,01")
                                 BigDecimal renda,
                                 @NotBlank
                                 BigDecimal valorSolicitado,
                                 @Positive
                                 int prazoPagamento) {
}
