package br.com.tomvieira.reportdocxreport;

import java.util.List;

/**
 *
 * @author Wellington
 */
public class EspecificaoRiscoDTO {

    private String cargo;
    private int qtdeMasculinos;
    private int qtdeFeminino;
    private int qtdeMenores;
    private List<String> funcionariosComRisco;
    private List<String> funcionariosSemRisco;
    private List<DetalhamentoRiscoDTO> detalhamentoRisco;

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getQtdeMasculinos() {
        return qtdeMasculinos;
    }

    public void setQtdeMasculinos(int qtdeMasculinos) {
        this.qtdeMasculinos = qtdeMasculinos;
    }

    public int getQtdeFeminino() {
        return qtdeFeminino;
    }

    public void setQtdeFeminino(int qtdeFeminino) {
        this.qtdeFeminino = qtdeFeminino;
    }

    public int getQtdeMenores() {
        return qtdeMenores;
    }

    public void setQtdeMenores(int qtdeMenores) {
        this.qtdeMenores = qtdeMenores;
    }
    
    public int getQtdeTotal(){
        return getQtdeFeminino() + getQtdeMasculinos() + getQtdeMenores();
    }

    public List<String> getFuncionariosComRisco() {
        return funcionariosComRisco;
    }

    public void setFuncionariosComRisco(List<String> funcionariosComRisco) {
        this.funcionariosComRisco = funcionariosComRisco;
    }

    public List<String> getFuncionariosSemRisco() {
        return funcionariosSemRisco;
    }

    public void setFuncionariosSemRisco(List<String> funcionariosSemRisco) {
        this.funcionariosSemRisco = funcionariosSemRisco;
    }

    public List<DetalhamentoRiscoDTO> getDetalhamentoRisco() {
        return detalhamentoRisco;
    }

    public void setDetalhamentoRisco(List<DetalhamentoRiscoDTO> detalhamentoRisco) {
        this.detalhamentoRisco = detalhamentoRisco;
    }

}
