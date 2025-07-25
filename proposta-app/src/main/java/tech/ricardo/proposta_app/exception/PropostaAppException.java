package tech.ricardo.proposta_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class PropostaAppException extends RuntimeException {

    public PropostaAppException(String message) {
        super(message);
    }

    public ProblemDetail problemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problemDetail.setTitle("Erro interno da aplicação!");
        problemDetail.setDetail("Entre em contato com o suporte.");

        return problemDetail;
    }
}
