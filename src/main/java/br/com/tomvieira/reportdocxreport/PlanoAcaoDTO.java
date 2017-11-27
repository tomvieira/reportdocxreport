package br.com.tomvieira.reportdocxreport;

import java.util.List;

/**
 *
 * @author Wellington
 */
public class PlanoAcaoDTO {

    private String atividade;
    private String responsavelExecucao;
    private String dataInicial;
    private String dataLimite;
    private List<String> setores;

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getResponsavelExecucao() {
        return responsavelExecucao;
    }

    public void setResponsavelExecucao(String responsavelExecucao) {
        this.responsavelExecucao = responsavelExecucao;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public List<String> getSetores() {
        return setores;
    }

    public void setSetores(List<String> setores) {
        this.setores = setores;
    }
}
