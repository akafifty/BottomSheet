package bs.iso.com.materialbottomsheet;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout linearLayoutBSheet;
    private ToggleButton tbUpDown;
    private ListView listView;
    private TextView txtCantante, txtCancion;
    private ContentLoadingProgressBar progbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        rellenarListView();

        tbUpDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }else{
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View view, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    tbUpDown.setChecked(true);
                }else if(newState == BottomSheetBehavior.STATE_COLLAPSED){
                    tbUpDown.setChecked(false);
                }
            }

            @Override
            public void onSlide(View view, float v) {

            }
        });
    }

    private void init() {
        this.linearLayoutBSheet = findViewById(R.id.bottomSheet);
        this.bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBSheet);
        this.tbUpDown = findViewById(R.id.toggleButton);
        this.listView = findViewById(R.id.listView);
        this.txtCantante = findViewById(R.id.txtCantante);
        this.txtCancion = findViewById(R.id.txtCancion);
        this.progbar = findViewById(R.id.progbar);
    }


    private void rellenarListView() {
        String[] nombre = {"50 Cent", "50 Cent", "50 Cent", "50 Cent", "50 Cent", "50 Cent", "50 Cent", "50 Cent"};
        String[] correo = {"Many Men", "Window Shopper",
                "Candy Shop", "Just a lil bit", "I'm the man", "P.I.M.P", "Wanksta",
                "Ayo technology"};


        ArrayList<Map<String, Object>> lista = new ArrayList<>();

        int nombresLen = nombre.length;

        for (int i = 0; i < nombresLen; i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("Cantante", nombre[i]);
            listItem.put("Titulo", correo[i]);

            lista.add(listItem);
        }

        this.listView.setAdapter(getAdapterListViewCT(lista));

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txtCantanteLV = view.findViewById(android.R.id.text1);
                TextView txtCancionLV = view.findViewById(android.R.id.text2);

                txtCantante.setText(txtCantanteLV.getText());
                txtCancion.setText(txtCancionLV.getText());

                progbar.setProgress(getRandom());
            }
        });


    }

    private SimpleAdapter getAdapterListViewCT(ArrayList<Map<String, Object>> lista) {
        return new SimpleAdapter(this, lista,
                android.R.layout.simple_list_item_2, new String[]{"Cantante", "Titulo"},
                new int[]{android.R.id.text1, android.R.id.text2}) {

            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView txtNombre = view.findViewById(android.R.id.text1);
                txtNombre.setTypeface(Typeface.DEFAULT_BOLD);

                TextView txtCorreo = view.findViewById(android.R.id.text2);
                txtCorreo.setTextColor(Color.DKGRAY);

                return view;
            }

        };
    }

    private int getRandom() {
        return (int) Math.floor(Math.random() * 100);
    }




















}
