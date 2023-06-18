package com.example.autoservice.service;

import com.example.autoservice.pdf.TemplatePdf;
import com.example.autoservice.service.domain.ServiceDto;
import com.example.autoservice.vehicle.domain.VehicleDto;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ServicesPdfExporter extends TemplatePdf {
    private final Font font;
    private final List<ServiceDto> listServices;

    private final VehicleDto vehicleDto;

    public ServicesPdfExporter(List<ServiceDto> listService, VehicleDto vehicleDto) throws IOException {
        super();
        this.listServices = listService;
        this.vehicleDto = vehicleDto;
        String FONT = "c:/windows/fonts/arial.ttf";
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf);
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.getHSBColor(136, 34, 29));
        cell.setPaddingBottom(5);

        cell.setPhrase(new Phrase("L.p", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Opis usługi", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Data", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cena", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        int licznik = 1;
        double sumPrice = 0.00f;

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        for (ServiceDto service : listServices) {
            cell.setPhrase(new Phrase(Integer.toString(licznik) + ".", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(service.getServiceType().getName(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(service.getDate().toString(), font));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.format("%.2f", service.getPrice()) + " PLN", font));
            table.addCell(cell);


            sumPrice += service.getPrice();
            licznik++;
        }

        cell.setColspan(2);
        cell.setPhrase(new Phrase("Suma kwoty wykonanych usług", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.format("%.2f", sumPrice) + " PLN", font));
        table.addCell(cell);
    }

    public void body(Document document) {
        font.setSize(18);
        Paragraph p = new Paragraph("Lista usług", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.4f, 3f, 1f, 1.5f});
        table.setSpacingBefore(10);
        font.setSize(11);
        font.setColor(Color.WHITE);
        writeTableHeader(table);
        font.setColor(Color.BLACK);
        writeTableData(table);
        document.add(table);
        font.setSize(12);
        Paragraph p1 = new Paragraph(" ", font);
        document.add(p1);
        font.setSize(12);
        p1 = new Paragraph(vehicleDto.getCustomer().getFirst_name() + " " + vehicleDto.getCustomer().getLast_name() + ", email:" + vehicleDto.getCustomer().getEmail(), font);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p1);
        font.setSize(12);
        p1 = new Paragraph(vehicleDto.getBrand() + " " + vehicleDto.getModel() + " " + vehicleDto.getManufactured_year() + ", vin: " + vehicleDto.getVin() + ", " + vehicleDto.getVehicleType(), font);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(p1);
    }
}
