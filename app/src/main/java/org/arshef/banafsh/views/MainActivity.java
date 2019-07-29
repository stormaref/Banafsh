package org.arshef.banafsh.views;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.arshef.banafsh.R;
import org.arshef.banafsh.models.DataModel;
import org.arshef.banafsh.models.ModelDataAdapter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<DataModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.main_listview);
        list = new ArrayList<>();
        switch (ChooseActivity.choice) {
            case 0: {
                list = getList("app.json");
                break;
            }
            case 1: {
                list = getList("app.json");
                break;
            }
            case 2: {
                list = getList("app.json");
                break;
            }
        }
        ModelDataAdapter adapter = new ModelDataAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private List<DataModel> getList(String choose) {
        List<DataModel> list = new ArrayList<>();
        try {
            String s = loadJSONFromAsset(choose);
            JSONArray array = new JSONArray(s);
            for (int i = 1; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String a = object.getString("a");
                String b = object.getString("b");
                String c = object.getString("c");
                String tmp = object.getString("d");
                String d = (!tmp.equals("")) ? tmp : "0";
                DataModel model = new DataModel(Integer.parseInt(a), b, c, Integer.parseInt(d));
                list.add(model);
            }
        } catch (Exception e) {
            Log.e("*******", e.getMessage());
        }
        return list;
    }

    public String loadJSONFromAsset(String choose) {
        String json = null;
        try {
            InputStream is = getAssets().open(choose);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}