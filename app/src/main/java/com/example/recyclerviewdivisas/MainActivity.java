package com.example.recyclerviewdivisas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<divisasEventModel> DivisasEventModel = new ArrayList<>();
    private EditText cantEurosEditText;
    private RecyclerView rv;
    public static Switch VIPSwitch;
    private static Button botonMostrar;
    public static TextView resultado;
    public static EditText cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.mostrarValor);
        cantidad = findViewById(R.id.cantidad_euros);
        VIPSwitch = findViewById(R.id.switch1);

        RecyclerView recyclerView = findViewById(R.id.divisasEventsRecycler);
        setDivisasEventModel();

        HistoricEventRVAdapter adapter = new HistoricEventRVAdapter(this, DivisasEventModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cantEurosEditText = findViewById(R.id.cantidad_euros);
        rv = findViewById(R.id.divisasEventsRecycler);
        botonMostrar = findViewById(R.id.button);

        // Agrega el OnClickListener al botón
        botonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mueve el código aquí
                handleClick();
            }
        });
    }

    private void setDivisasEventModel() {
        String[] eventNames = getResources().getStringArray(R.array.divisas);
        String[] eventPrecio = getResources().getStringArray(R.array.precio_divisas);

        TypedArray ta = getResources().obtainTypedArray(R.array.ic_divisas);
        Drawable[] eventIc = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                eventIc[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();

        for (int i = 0; i < eventNames.length; i++) {
            DivisasEventModel.add(new divisasEventModel(
                    eventNames[i],
                    eventPrecio[i],
                    eventIc[i]
            ));
        }
    }

    private void handleClick() {
        // El código que deseas ejecutar cuando se hace clic en el botón
            resultado.setText(HistoricEventRVAdapter.getResult());

    }
}
