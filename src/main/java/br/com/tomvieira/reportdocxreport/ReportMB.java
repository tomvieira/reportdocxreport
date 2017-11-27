package br.com.tomvieira.reportdocxreport;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Wellington
 */
@Named
@RequestScoped
public class ReportMB implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ReportMB.class.getName());

    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void generateReport() {
        try {
            PpraDTO ppra = new PpraDTO();
            ppra.setAno("2017-2018");
            ppra.setDataEmissao("24/05/2017");
            ppra.setNomeTecnicoAssinatura("Zezinho Tecnico de Segurança do Trabalho");
            ppra.setCargoTecnicoAssinatura("Técnico de Segurança do Trabalho");
            UnidadeDTO unidade = new UnidadeDTO();
            unidade.setNome("FEIS - FACULDADE DE ENGENHARIA DE ILHA SOLTEIRA");
            unidade.setBairro("Centro");
            unidade.setCep("15385-000");
            unidade.setCidade("São Paulo");
            unidade.setCnae("85.31-7-00");
            unidade.setCnpj("48.031.918/0015-20");
            unidade.setComplemento("");
            unidade.setEndereco("AV. BRASIL CENTRO, 56 ");
            unidade.setDescricaoCnae("Ensino Superior Graduação");
            unidade.setGrauRisco("2");
            unidade.setUf("SP");
            ppra.setUnidade(unidade);
            List<ReconhecimentoRiscoDTO> reconhecimentos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                popularReconhecimentoRisco(reconhecimentos);
            }
            popularPlanosAcoes(ppra);
            ppra.setReconhecimentosRisco(reconhecimentos);
            
            
            HashMap<String, Object> objetoBase = new HashMap<>();
            objetoBase.put("ppra", ppra);
            objetoBase.put("newline", "\r\n");

            ReportGenerator reportGenerator = new ReportGenerator();
            List<String> templates = new ArrayList<>();
            templates.add("capa.odt");
            templates.add("protocolo_entrega.odt");
            templates.add("texto_introdutorio.odt");
            templates.add("ppra_padrao.odt");
            templates.add("folha_assinaturas.odt");
            templates.add("cronograma_atividades.odt");
            templates.add("plano_acao.odt");
            List<String> arquivosGerados = reportGenerator.generate(objetoBase, templates);
            File completo = reportGenerator.joinDocuments("ppra.odt", arquivosGerados);
            file = new DefaultStreamedContent(new FileInputStream(completo), FacesContext.getCurrentInstance().getExternalContext().getMimeType(completo.getPath()), completo.getName());
            reportGenerator.close();
            System.out.println("Finalizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void popularReconhecimentoRisco(List<ReconhecimentoRiscoDTO> reconhecimentos) {
        ReconhecimentoRiscoDTO reconhecimento = new ReconhecimentoRiscoDTO();
        SetorDTO setor = new SetorDTO();
        setor.setNomeSetor("Departamento de Teste");
        setor.setDescricaoSetor("Sala de madeira e sem nenhum piso");
        reconhecimento.setSetor(setor);
        List<EspecificaoRiscoDTO> especificacoes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            popularEspecificacoesRisco(especificacoes);
        }
        reconhecimento.setEspecificoesRiscos(especificacoes);
        reconhecimentos.add(reconhecimento);
    }

    private void popularEspecificacoesRisco(List<EspecificaoRiscoDTO> especificacoes) {
        EspecificaoRiscoDTO esp = new EspecificaoRiscoDTO();
        esp.setCargo("Analista de O&M");
        esp.setQtdeMasculinos(10);
        esp.setQtdeFeminino(5);
        List<String> funcionariosComRisco = new ArrayList<>();
        popularFuncionariosComRisco(funcionariosComRisco);
        esp.setFuncionariosComRisco(funcionariosComRisco);
        List<String> funcionariosSemRisco = new ArrayList<>();
        popularFuncionariosSemRisco(funcionariosSemRisco);
        esp.setFuncionariosSemRisco(funcionariosSemRisco);
        List<DetalhamentoRiscoDTO> detalhamentos = new ArrayList<>();
        popularDetalhamentosRisco(detalhamentos);
        esp.setDetalhamentoRisco(detalhamentos);
        especificacoes.add(esp);
    }

    private void popularFuncionariosComRisco(List<String> funcionariosComRisco) {
        funcionariosComRisco.add("Osmar Andrade");
        funcionariosComRisco.add("Edson Senne");
        funcionariosComRisco.add("Vanessa");
        funcionariosComRisco.add("Fabio Mattar");
        funcionariosComRisco.add("Humberto");
        funcionariosComRisco.add("Marcos Mion");
        funcionariosComRisco.add("Ronaldo Baggio");
        funcionariosComRisco.add("Renato Ramalho");
    }

    private void popularFuncionariosSemRisco(List<String> funcionariosSemRisco) {
        funcionariosSemRisco.add("Eder Leal");
        funcionariosSemRisco.add("Sandro Gonçalves");
        funcionariosSemRisco.add("Lisandra Cossulin");
    }

    private void popularDetalhamentosRisco(List<DetalhamentoRiscoDTO> detalhamentos) {
        DetalhamentoRiscoDTO detalhamentoRiscoDTO = new DetalhamentoRiscoDTO();
        detalhamentoRiscoDTO.setAgente("Formol");
        detalhamentoRiscoDTO.setCaracterizacao("Caracterizado");
        detalhamentoRiscoDTO.setDanosSaude("Sem danos a saude");
        detalhamentoRiscoDTO.setFonteGeradora("Substância química");
        detalhamentoRiscoDTO.setFormaAvaliacao("Teste");
        detalhamentoRiscoDTO.setFrequencia("5.6 l/m");
        detalhamentoRiscoDTO.setFuncionario("Osmar Andrade");
        detalhamentoRiscoDTO.setFundamentacao("Sem fundamentação");
        detalhamentoRiscoDTO.setGrupoAgente("Quimícos");
        detalhamentoRiscoDTO.setLimiteTolerancia("10 litros");
        detalhamentoRiscoDTO.setMedidasExistentes("Nenhuma medida");
        detalhamentoRiscoDTO.setMedidasRecomendadas("sem recomendações");
        detalhamentoRiscoDTO.setMeiosPropagacao("Líquido");
        detalhamentoRiscoDTO.setNivelAcao("Baixo");
        detalhamentos.add(detalhamentoRiscoDTO);
    }

    private void popularPlanosAcoes(PpraDTO ppra) {
        ArrayList<PlanoAcaoDTO> planos = new ArrayList<PlanoAcaoDTO>();
        for (int i = 0; i < 10; i++) {
            PlanoAcaoDTO plano = new PlanoAcaoDTO();
            plano.setAtividade("001 - Programa de gestão de EPIS");
            plano.setResponsavelExecucao("Zezinho dos testes");
            plano.setDataInicial("01/01/2017");
            plano.setDataLimite("31/12/2017");
            List<String> setores = new ArrayList<>();
            setores.add("ST ZELADORIA");
            setores.add("Centro de convivência infantil");
            setores.add("STS");
            setores.add("STI");
            plano.setSetores(setores);
            planos.add(plano);
        }
        ppra.setPlanosAcoes(planos);
    }
}
