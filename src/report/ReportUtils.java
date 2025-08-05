package report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;

import connection.connection;

public class ReportUtils {
    public static void generateReport(String reportPath){
        Connection conn = null;
        try{
            InputStream report = ReportUtils.class.getResourceAsStream(reportPath);

            if (report == null){
                throw new RuntimeException("No se pudo encontrar el archivo del reporte: " + reportPath);
            }
            conn = connection.getConnection();
            JasperPrint print = JasperFillManager.fillReport(report, null, conn);

            JasperViewer.viewReport(print, false);
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                connection.closeConnection(conn);
            }
        }
    }
}
