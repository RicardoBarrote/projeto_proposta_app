package tech.ricardo.proposta_app.controller.dto;

import java.math.BigDecimal;

public record UsuarioResponseDto(String nome,
                                 String telefone,
                                 BigDecimal renda) {
}
