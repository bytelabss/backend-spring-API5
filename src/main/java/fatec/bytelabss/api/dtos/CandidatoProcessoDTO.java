package fatec.bytelabss.api.dtos;

public class CandidatoProcessoDTO {

    private String candidato;
    private String processoSeletivo;

    public CandidatoProcessoDTO(String candidato, String processoSeletivo) {
        this.candidato = candidato;
        this.processoSeletivo = processoSeletivo;
    }

    public String getCandidato() {
        return candidato;
    }

    public void setCandidato(String candidato) {
        this.candidato = candidato;
    }

    public String getProcessoSeletivo() {
        return processoSeletivo;
    }

    public void setProcessoSeletivo(String processoSeletivo) {
        this.processoSeletivo = processoSeletivo;
    }
}
