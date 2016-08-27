package gui;

import db.DBConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.File;

import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;

/**
 * Created by Dima on 19.04.2016.
 */
public class JasperReportFrame {

    private static JasperViewer jv;

    private static final String REPORT_DIR = "C:\\Users\\Dima\\JaspersoftWorkspace\\SoftwareDevelopmentDatabaseReports\\";
    private static final String REPORT_OUTPUT_PDF_DIR = "reports\\output_pdf\\";

    public JasperReportFrame(){
        jv = new JasperViewer(null);
        jv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make sure the output directory exists.
        File outDir = new File("C:/jasperoutput");
        outDir.mkdirs();
    }

    public static void showPopularityOfEachOsReport(){
        String reportSrcFile = "reports\\the_most_popular_os.jrxml";

        JasperPrint jp = compileJasperReport(reportSrcFile);

        saveAsPDF(jp);

        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }

    public static void showPopularityOfEachLangTaskReport(){
        String reportSrcFile = "reports\\the_most_popular_prog_lang.jrxml";

        JasperPrint jp = compileJasperReport(reportSrcFile);

        saveAsPDF(jp);

        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }

    public static void showRequirementsOnTimeTaskReport(){
        String reportSrcFile = "reports\\requirements_on_time.jrxml";

        JasperPrint jp = compileJasperReport(reportSrcFile);

        saveAsPDF(jp);

        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }

    public static void showRequirementsAtWrongTimeTaskReport(){
        String reportSrcFile = "reports\\requirements_on_time.jrxml";

        JasperPrint jp = compileJasperReport(reportSrcFile);

        saveAsPDF(jp);

        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }

    public static void showTheMostSuccessfulProjectsMethodologyReport(){
        String reportSrcFile = "reports\\methodology_at_the_most_successful_projects.jrxml";

        JasperPrint jp = compileJasperReport(reportSrcFile);

        saveAsPDF(jp);

        JasperViewer jv = new JasperViewer(jp, false);
        jv.setVisible(true);
    }


    private static JasperPrint compileJasperReport(String reportSrcFile){
        JasperReport jasperReport = null;
        JasperPrint jp = null;
        try {
            jasperReport = compileReport(reportSrcFile);
            jp = JasperFillManager.fillReport(jasperReport, null, DBConnection.getConnection());
        } catch (JRException e) {
            e.printStackTrace();
        }
        return jp;
    }

    private static void saveAsPDF(JasperPrint jp){
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();

        ExporterInput exporterInput = new SimpleExporterInput(jp);
        // ExporterInput
        exporter.setExporterInput(exporterInput);

        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "REPORT_OUTPUT_PDF_DIR" + "InsuranceTypeReport.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);

        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        try {
            exporter.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        DBConnection.init();
//        JasperReportFrame.showTreatiesAt2015Report();
        JasperReportFrame.showRequirementsOnTimeTaskReport();
        JasperReportFrame.showPopularityOfEachLangTaskReport();
        JasperReportFrame.showRequirementsAtWrongTimeTaskReport();
        JasperReportFrame.showTheMostSuccessfulProjectsMethodologyReport();
    }
}
