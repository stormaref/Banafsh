package org.arshef.banafsh.services;

import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.arshef.banafsh.models.DataModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class PDFHandler {
    static Font font;

    public static void createTable(String name, List<DataModel> list) throws Exception {
        Document document = new Document();
        PdfPTable table = new PdfPTable(new float[]{2, 1, 2, 1});
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        BaseFont bf = BaseFont.createFont("assets/font/zar.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf, 12);
        table.addCell(getPhrase("اولویت",16));
        table.addCell(getPhrase("رشته",16));
        table.addCell(getPhrase("دانشگاه",16));
        table.addCell(getPhrase("کد رشته",16));
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (PdfPCell cell : cells) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        for (DataModel model : list) {
            table.addCell(getPhrase(String.valueOf(model.Position),12));
            table.addCell(getPhrase(model.Title,12));
            table.addCell(getPhrase(model.University,12));
            table.addCell(getPhrase(String.valueOf(model.Code),12));
        }
        String path = String.format("%s/pdfs/", Environment.getExternalStorageDirectory().getAbsolutePath());
        final File directory = new File((path));
        if (!directory.exists())
            directory.mkdir();
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%s%s.pdf", path, name)));
        document.open();
        document.add(table);
        document.close();
    }

    private static Phrase getPhrase(String text, int size) {
        return new Phrase(size, text, font);
    }
}
