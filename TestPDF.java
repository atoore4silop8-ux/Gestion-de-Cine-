package com.cinema;

import com.cinema.service.ReportService;

public class TestPDF {
    public static void main(String[] args) {
        ReportService reportService = new ReportService();
        try {
            reportService.generateTestScriptsPDF("TestScripts.md", "TestScripts.pdf");
            System.out.println("PDF generado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al generar PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
