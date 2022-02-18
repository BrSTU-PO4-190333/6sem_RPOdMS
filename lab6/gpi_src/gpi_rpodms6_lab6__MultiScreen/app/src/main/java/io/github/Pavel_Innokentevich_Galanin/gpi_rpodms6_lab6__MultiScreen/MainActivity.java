package io.github.Pavel_Innokentevich_Galanin.gpi_rpodms6_lab6__MultiScreen;

import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;

public class MainActivity extends ListActivity {
    private ArrayAdapter<String> adapter;
    private String[] islands = {
            "Канары",
            "Курилы",
            "Мальдивы",
            "Филиппины",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, islands);
        setListAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // TODO Auto-generated method stub
            }
        };
    }
}