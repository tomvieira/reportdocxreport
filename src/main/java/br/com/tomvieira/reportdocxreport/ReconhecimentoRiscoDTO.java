package br.com.tomvieira.reportdocxreport;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Wellington
 */
public class ReconhecimentoRiscoDTO implements Serializable {

    private SetorDTO setor;

    private List<EspecificaoRiscoDTO> especificoesRiscos;

    public SetorDTO getSetor() {
        return setor;
    }

    public void setSetor(SetorDTO setor) {
        this.setor = setor;
    }

    public List<EspecificaoRiscoDTO> getEspecificoesRiscos() {
        return especificoesRiscos;
    }

    public void setEspecificoesRiscos(List<EspecificaoRiscoDTO> especificoesRiscos) {
        this.especificoesRiscos = especificoesRiscos;
    }

}
