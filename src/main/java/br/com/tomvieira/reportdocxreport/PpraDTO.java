package br.com.tomvieira.reportdocxreport;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class PpraDTO implements Serializable {

    private String ano;
    private String dataEmissao;
    private UnidadeDTO unidade;
    private String nomeTecnicoAssinatura;
    private String cargoTecnicoAssinatura;
    private List<ReconhecimentoRiscoDTO> reconhecimentosRisco;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public UnidadeDTO getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeDTO unidade) {
        this.unidade = unidade;
    }

    public List<ReconhecimentoRiscoDTO> getReconhecimentosRisco() {
        return reconhecimentosRisco;
    }

    public void setReconhecimentosRisco(List<ReconhecimentoRiscoDTO> reconhecimentosRisco) {
        this.reconhecimentosRisco = reconhecimentosRisco;
    }

    public String getNomeTecnicoAssinatura() {
        return nomeTecnicoAssinatura;
    }

    public void setNomeTecnicoAssinatura(String nomeTecnicoAssinatura) {
        this.nomeTecnicoAssinatura = nomeTecnicoAssinatura;
    }

    public String getCargoTecnicoAssinatura() {
        return cargoTecnicoAssinatura;
    }

    public void setCargoTecnicoAssinatura(String cargoTecnicoAssinatura) {
        this.cargoTecnicoAssinatura = cargoTecnicoAssinatura;
    }

}
