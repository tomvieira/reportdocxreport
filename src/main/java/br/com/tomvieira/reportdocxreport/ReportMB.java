package br.com.tomvieira.reportdocxreport;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.jopendocument.dom.ODSingleXMLDocument;

/**
 *
 * @author Wellington
 */
@Named
@RequestScoped
public class ReportMB implements Serializable {

    public void joinDocuments() {
        try {
            // Load 2 text documents
            //InputStream in1 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/ppra_padrao.odt");
            //InputStream in2 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/capa.odt");
            /*if (new BOMInputStream(in1).hasBOM()) {
                System.out.println("BOM found");
            } else {
                System.out.println("BOM not found");
            }*/                        
            String[] commands = {"/bin/bash", "-c", "ooo_cat /tmp/teste.odt /tmp/teste2.odt > /tmp/bozo.odt"};
            Process p = Runtime.getRuntime().exec(commands);
            p.waitFor();
            p.destroy();
            System.out.println("Gerado");
            
            // Save to file and Open the document with OpenOffice.org !
            //OOUtils.open(p1.saveToPackageAs(new File("cat")));
        } catch (Exception ex) {
            Logger.getLogger(ReportMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateReport() {
        try {
            joinDocuments();
            
            InputStream in = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/capa.odt");
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            FieldsMetadata metadata = report.createFieldsMetadata();
            //metadata.load( "developers", Developer.class, true );
//            metadata.addFieldAsList("developers.nome");
//            metadata.addFieldAsList("developers.telefone");
//            metadata.addFieldAsList("developers.linguagem");
            report.setFieldsMetadata(metadata);

            // 2) Create context Java model
            IContext context = report.createContext();

//            List<Developer> developers = new ArrayList<>();
//            developers.add(new Developer("José", "(11)96596-0277", "JAVA"));
//            developers.add(new Developer("Maria", "(11)98952-6499", "DELPHI"));
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
            ArrayList<ReconhecimentoRiscoDTO> reconhecimentos = new ArrayList<ReconhecimentoRiscoDTO>();
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
            context.put("ppra", ppra);

            //Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.XWPF);
            Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
            PdfOptions pdfOptions = PdfOptions.create();
            options.subOptions(pdfOptions);

            //OutputStream out = new FileOutputStream(new File("/tmp/original.pdf"));
            OutputStream out = new FileOutputStream(new File("/tmp/capa.pdf"));
            //report.process(context, out);  
            report.convert(context, options, out);

            System.out.println("Finalizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
