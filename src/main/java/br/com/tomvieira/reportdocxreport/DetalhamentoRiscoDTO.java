package br.com.tomvieira.reportdocxreport;

import java.io.Serializable;

/**
 *
 * @author Wellington
 */
public class DetalhamentoRiscoDTO implements Serializable{
    private String funcionario;
    private String agente;
    private String grupoAgente;
    private String limiteTolerancia;
    private String nivelAcao;
    private String frequencia;
    private String danosSaude;
    private String fonteGeradora;
    private String medidasRecomendadas;
    private String medidasExistentes;
    private String caracterizacao;
    private String meiosPropagacao;
    private String fundamentacao;
    private String formaAvaliacao;

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getGrupoAgente() {
        return grupoAgente;
    }

    public void setGrupoAgente(String grupoAgente) {
        this.grupoAgente = grupoAgente;
    }

    public String getLimiteTolerancia() {
        return limiteTolerancia;
    }

    public void setLimiteTolerancia(String limiteTolerancia) {
        this.limiteTolerancia = limiteTolerancia;
    }

    public String getNivelAcao() {
        return nivelAcao;
    }

    public void setNivelAcao(String nivelAcao) {
        this.nivelAcao = nivelAcao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getDanosSaude() {
        return danosSaude;
    }

    public void setDanosSaude(String danosSaude) {
        this.danosSaude = danosSaude;
    }

    public String getFonteGeradora() {
        return fonteGeradora;
    }

    public void setFonteGeradora(String fonteGeradora) {
        this.fonteGeradora = fonteGeradora;
    }

    public String getMedidasRecomendadas() {
        return medidasRecomendadas;
    }

    public void setMedidasRecomendadas(String medidasRecomendadas) {
        this.medidasRecomendadas = medidasRecomendadas;
    }

    public String getMedidasExistentes() {
        return medidasExistentes;
    }

    public void setMedidasExistentes(String medidasExistentes) {
        this.medidasExistentes = medidasExistentes;
    }

    public String getCaracterizacao() {
        return caracterizacao;
    }

    public void setCaracterizacao(String caracterizacao) {
        this.caracterizacao = caracterizacao;
    }

    public String getMeiosPropagacao() {
        return meiosPropagacao;
    }

    public void setMeiosPropagacao(String meiosPropagacao) {
        this.meiosPropagacao = meiosPropagacao;
    }

    public String getFundamentacao() {
        return fundamentacao;
    }

    public void setFundamentacao(String fundamentacao) {
        this.fundamentacao = fundamentacao;
    }

    public String getFormaAvaliacao() {
        return formaAvaliacao;
    }

    public void setFormaAvaliacao(String formaAvaliacao) {
        this.formaAvaliacao = formaAvaliacao;
    }
    
}
