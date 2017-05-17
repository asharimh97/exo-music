package id.asharimh.exomusicplayer;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListDaerahActivity extends AppCompatActivity {

    TextView textViewTitle ;
    ListView listViewDaerah ;
    String daerahName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_daerah);

        Typeface tfMont = Typeface.createFromAsset(getAssets(), BaseClass.MONTSERRAT_BOLD_PATH) ;
        Typeface tfLato = Typeface.createFromAsset(getAssets(), BaseClass.LATO_LIGHT_PATH) ;

        String[] daftarLagu = new String[]{"Butet", "Jembatan Ampera", "Ampar Ampar Pisang"} ;
        listViewDaerah = (ListView) findViewById(R.id.listViewDaerah);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        daerahName = getIntent().getStringExtra("DAERAH_NAME") ;
        textViewTitle.setText(daerahName);

        textViewTitle.setTypeface(tfMont);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.layout_list_daerah, R.id.judulDaerah, daftarLagu) ;
        listViewDaerah.setAdapter(adapter);

        listViewDaerah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) listViewDaerah.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
