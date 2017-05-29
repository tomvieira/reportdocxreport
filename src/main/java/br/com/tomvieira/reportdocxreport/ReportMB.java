package br.com.tomvieira.reportdocxreport;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
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

//    public FileDownloadView() {
//        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/demo/images/optimus.jpg");
//        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
//    }
    public StreamedContent getFile() {
        return file;
    }

    public void generateReport() {
        try {
            PpraDTO ppra = new PpraDTO();
            ppra.setAno("2017-2018");
            ppra.setDataEmissao("24/05/2017");
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
            ArrayList<ReconhecimentoRiscoDTO> reconhecimentos = new ArrayList<>();
            ReconhecimentoRiscoDTO reconhecimento = new ReconhecimentoRiscoDTO();
            SetorDTO setor = new SetorDTO();
            setor.setNomeSetor("Departamento de Teste");
            setor.setDescricaoSetor("Sala de madeira e sem nenhum piso");
            reconhecimento.setSetor(setor);

            List<EspecificaoRiscoDTO> especificacoes = new ArrayList<>();
            EspecificaoRiscoDTO esp = new EspecificaoRiscoDTO();
            esp.setCargo("Analista de O&M");
            esp.setQtdeMasculinos(10);
            esp.setQtdeFeminino(5);
            especificacoes.add(esp);
            reconhecimento.setEspecificoesRiscos(especificacoes);

            reconhecimentos.add(reconhecimento);

            ReconhecimentoRiscoDTO reconhecimento2 = new ReconhecimentoRiscoDTO();
            SetorDTO setor2 = new SetorDTO();
            setor2.setNomeSetor("Departamento de teste2");
            setor2.setDescricaoSetor("Sala de madeira e sem nenhum piso2");
            reconhecimento2.setSetor(setor2);

            List<EspecificaoRiscoDTO> especificacoes2 = new ArrayList<>();
            EspecificaoRiscoDTO esp2 = new EspecificaoRiscoDTO();
            esp2.setCargo("Analista de Sistemas");
            esp2.setQtdeMasculinos(10);
            esp2.setQtdeFeminino(5);
            especificacoes2.add(esp2);
            reconhecimento2.setEspecificoesRiscos(especificacoes2);

            reconhecimentos.add(reconhecimento2);
            ppra.setReconhecimentosRisco(reconhecimentos);
            HashMap<String, Object> objetoBase = new HashMap<>();
            objetoBase.put("ppra", ppra);
            
            
            ReportGenerator reportGenerator = new ReportGenerator();
            List<String> templates = new ArrayList<>();            
            templates.add("capa.odt");
            templates.add("protocolo_entrega.odt");
            templates.add("texto_introdutorio.odt");
            templates.add("ppra_padrao.odt");
            List<String> arquivosGerados = reportGenerator.generate(objetoBase, templates);
            File completo = reportGenerator.joinDocuments("ppra.odt",arquivosGerados);
            file = new DefaultStreamedContent(new FileInputStream(completo), FacesContext.getCurrentInstance().getExternalContext().getMimeType(completo.getPath()),completo.getName());
            System.out.println("Finalizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
