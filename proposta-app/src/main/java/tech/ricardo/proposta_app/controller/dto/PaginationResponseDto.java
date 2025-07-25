package tech.ricardo.proposta_app.controller.dto;

public record PaginationResponseDto(Integer page,
                                    Integer pageSize,
                                    long totalElements,
                                    int totalPages) {
}
