package org.arshef.banafsh.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;
import org.arshef.banafsh.services.ItemMoveCallback;
import org.arshef.banafsh.services.ModelDataAdapter;
import org.arshef.banafsh.services.PDFHandler;
import org.arshef.banafsh.services.RecyclerViewAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    public static String name;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button button = findViewById(R.id.submitBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog, null);
                builder.setCancelable(false);
                builder.setView(dialogView);
                Button btn_positive = dialogView.findViewById(R.id.okBtn);
                Button btn_negative = dialogView.findViewById(R.id.cancelBtn);
                final EditText nameTxt = dialogView.findViewById(R.id.nameInput);
                final AlertDialog dialog = builder.create();
                btn_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = nameTxt.getText().toString();
                        if (!name.equals("")) {
                            try {
                                PDFHandler.createTable(name, ModelDataAdapter.Models);
                                Toast.makeText(ResultActivity.this, "Saved Succeed!", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Log.wtf("**", e.getMessage());
                                Toast.makeText(ResultActivity.this, "Error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                btn_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.super.onBackPressed();
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        List<DataModel> list = ModelDataAdapter.Models;
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(list);
        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);
    }

    public static void SavePdf() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfPTable table = new PdfPTable(new float[]{1, 1, 2});
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell("Name");
        table.addCell("Age");
        table.addCell("Location");
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
        for (int i = 1; i < 5; i++) {
            table.addCell("Name:" + i);
            table.addCell("Age:" + i);
            table.addCell("Location:" + i);
        }
        File folder = new File(Environment.getExternalStorageDirectory().toString() + "/pdfs/");
        folder.mkdirs();
        PdfWriter.getInstance(document, new FileOutputStream(String.format("%s/pdfs/%s.pdf", Environment.getExternalStorageDirectory().getAbsolutePath(), name)));
        document.open();
        document.add(table);
        document.close();
    }
}
