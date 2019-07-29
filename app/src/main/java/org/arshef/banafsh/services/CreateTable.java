package org.arshef.banafsh.services;

import android.os.Environment;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreateTable {
	public static void SavePdf() throws FileNotFoundException, DocumentException {
		 Document document = new Document();
		 PdfPTable table = new PdfPTable(new float[] { 2, 1, 2 });
	     table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		 table.addCell("Name");
         table.addCell("Age");
         table.addCell("Location");
	     table.setHeaderRows(1);
	     PdfPCell[] cells = table.getRow(0).getCells();
	     for (int j=0;j<cells.length;j++){
	    	 cells[j].setBackgroundColor(BaseColor.GRAY);
	     }
         for (int i=1;i<5;i++){
    		 table.addCell("Name:"+i);
             table.addCell("Age:"+i);
             table.addCell("Location:"+i);
         }
	     PdfWriter.getInstance(document, new FileOutputStream(String.format("%s/pdfs/aref.pdf", Environment.getExternalStorageDirectory().getAbsolutePath())));
	     document.open();
         document.add(table);
	     document.close();
	     System.out.println("Done");
	}
}
