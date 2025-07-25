package tech.ricardo.proposta_app.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tech.ricardo.proposta_app.exception.PropostaAppException;
import tech.ricardo.proposta_app.exception.global.dto.CamposVaziosDto;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(PropostaAppException.class)
    public ProblemDetail propostaAppException(PropostaAppException e) {
        return e.problemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        var erros = e.getFieldErrors()
                .stream()
                .map(fieldError -> new CamposVaziosDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        problemDetail.setTitle("Campos invalidos na requisicao.");
        problemDetail.setDetail("Existem campos invalidos na requisicao");
        problemDetail.setProperty("Campos invalidos", erros);

        return problemDetail;
    }
}
