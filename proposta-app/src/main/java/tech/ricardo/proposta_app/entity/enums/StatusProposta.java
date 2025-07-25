package tech.ricardo.proposta_app.entity.enums;

public enum StatusProposta {

    ACEITA("ACEITA"),
    NEGADA("NEGADA"),
    ANALISE("ANALISE");

    private final String status;

    StatusProposta(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
