package org.arshef.banafsh.services;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import org.arshef.banafsh.models.DataModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class PDFHandler {
    static Font font;

    public static void createTable(Activity context, String name, List<DataModel> list) throws Exception {
        Document document = new Document();
        PdfPTable table = new PdfPTable(new float[]{2, 1, 2, 1});
        table.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        BaseFont bf = BaseFont.createFont("assets/font/zar.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf, 12);
        PdfPTable info = new PdfPTable(3);
        info.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        info.setWidthPercentage(100);
        info.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        info.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        info.getDefaultCell().setFixedHeight(100);
        //todo: string format bezaram \r\n ghatish konam be jaye ye cell e jadid
        info.addCell(getCell("انتخاب رشته", PdfPCell.ALIGN_LEFT, 18));
        info.addCell(getCell("مرکز تخصصی مشاوره و برنامه ریزی", PdfPCell.ALIGN_CENTER, 18));
        info.addCell(getImageCell(context, PdfPCell.ALIGN_RIGHT));
        info.addCell(getCell(name, PdfPCell.ALIGN_LEFT, 18));
        info.addCell(getCell("گروه آموزشی فواد میرباقری", PdfPCell.ALIGN_CENTER, 18));
        info.addCell(getCell("    ",PdfPCell.ALIGN_CENTER,18));

        for (int i = 0; i < 3; i++) {
            info.addCell(getCell("    ",PdfPCell.ALIGN_CENTER,18));
        }
//        Paragraph paragraph = new Paragraph("انتخاب رشته");
//        paragraph.setAlignment(Element.ALIGN_RIGHT);
        Chunk linebreak = new Chunk(new DottedLineSeparator());
        table.addCell(getPhrase("اولویت", 16));
        table.addCell(getPhrase("رشته", 16));
        table.addCell(getPhrase("دانشگاه", 16));
        table.addCell(getPhrase("کد رشته", 16));
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (PdfPCell cell : cells) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        for (DataModel model : list) {
            table.addCell(getPhrase(String.valueOf(model.Position), 12));
            table.addCell(getPhrase(model.Title, 12));
            table.addCell(getPhrase(model.University, 12));
            table.addCell(" ");
        }
        String path = String.format("%s/pdfs/", Environment.getExternalStorageDirectory().getAbsolutePath());
        final File directory = new File(path);
        boolean b = false;
        while (!directory.exists()) {
            permission(context);
            directory.mkdir();
        }
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%s%s.pdf", path, name)));
        document.open();
        document.add(info);
//        document.add(linebreak);
        document.add(table);
        document.close();
    }

    private static void permission(Activity context) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    private static Phrase getPhrase(String text, int size) {
        return new Phrase(size, text, font);
    }

    private static PdfPCell getCell(String text, int alignment, int size) {
        PdfPCell cell = new PdfPCell(getPhrase(text, size));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private static Image getImage(Context context) {
        try {
            InputStream ims = context.getAssets().open("avatar.png");
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return Image.getInstance(stream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }
    private static PdfPCell getImageCell(Context context,int alignment){
        PdfPCell cell = new PdfPCell(getImage(context));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setFixedHeight(50f);
        return cell;
    }
}
