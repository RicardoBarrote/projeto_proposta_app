package tech.ricardo.proposta_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PropostaNaoEncontradaException extends PropostaAppException {

    private final String detail;

    public PropostaNaoEncontradaException(String detail) {
        super(detail);
        this.detail = detail;
    }

    @Override
    public ProblemDetail problemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setTitle("Sua proposta não foi enviada, tente novamente.");
        problemDetail.setDetail(detail);

        return super.problemDetail();
    }
}
