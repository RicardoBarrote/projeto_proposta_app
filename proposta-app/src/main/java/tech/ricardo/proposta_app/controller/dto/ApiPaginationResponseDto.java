package tech.ricardo.proposta_app.controller.dto;

import java.util.List;

public record ApiPaginationResponseDto<T>(List<T> list, PaginationResponseDto dto) {
}
