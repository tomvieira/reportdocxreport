package br.com.tomvieira.reportdocxreport;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.jdom.JDOMException;
import org.jopendocument.dom.ODSingleXMLDocument;

/**
 *
 * @author Wellington
 */
public class ReportGenerator {

    private static final Logger LOGGER = Logger.getLogger(ReportGenerator.class.getName());

    private final String dirTemp;

    public ReportGenerator() {
        UUID aleatorio = UUID.randomUUID();
        dirTemp = "/tmp/sisgo" + aleatorio.toString();
        criarDiretorioTemporario();
    }

    private void criarDiretorioTemporario() {
        File diretorio = new File(dirTemp);
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
    }

    private void apagarDiretorioTemporario() {
        File diretorio = new File(dirTemp);
        if (diretorio.exists()) {
            File[] arquivos = diretorio.listFiles();
            for (File arquivo : arquivos) {
                arquivo.delete();
            }
            diretorio.delete();
        }
    }

    public void close() {
        apagarDiretorioTemporario();
    }    

    public File joinDocuments(String finalName, List<String> documents) {
        try {
            File f1 = new File(documents.get(0));
            ODSingleXMLDocument p1 = ODSingleXMLDocument.createFromPackage(f1);

            for (int i = 1; i < documents.size(); i++) {
                File f2 = new File(documents.get(i));
                ODSingleXMLDocument p2 = ODSingleXMLDocument.createFromPackage(f2);
                p1.add(p2);
            }
            File ultimo = new File(dirTemp + "/" + finalName);
            p1.saveToPackageAs(ultimo);
            return ultimo;
        } catch (JDOMException ex) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<String> generate(Map<String, Object> objetoBase, List<String> templates) {
        try {
            List<String> retorno = new ArrayList<>();
            for (String template : templates) {
                InputStream in = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/" + template);
                IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
                // 2) Create context Java model
                IContext context = report.createContext();
                context.putMap(objetoBase);

                //Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
                String file = dirTemp + "/" + template;
                OutputStream out = new FileOutputStream(new File(file));
                report.process(context, out);
                //report.convert(context, options, out);
                retorno.add(file);
            }
            return retorno;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar arquivos");
        }
    }
}
