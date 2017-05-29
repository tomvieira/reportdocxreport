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

    public File joinDocuments(String finalName,List<String> documents) {
        try {
            String command = "";
            for (String document : documents) {
                command += document + " ";
            }
            command += "> " + dirTemp + "/" + finalName;
            String[] commands = {"/bin/bash", "-c", "ooo_cat " + command};
            System.out.println(commands.toString());
            Process p = Runtime.getRuntime().exec(commands);
            p.waitFor();
            p.destroy();            
            LOGGER.log(Level.INFO, "Arquivo completo gerado");
            return new File(dirTemp+"/"+finalName);
        } catch (IOException | InterruptedException ex) {
            LOGGER.log(Level.SEVERE, "Erro na geração do Arquivo único!", ex);
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
