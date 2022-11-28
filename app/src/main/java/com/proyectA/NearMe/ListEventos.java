package com.proyectA.NearMe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyectA.NearMe.Controlador.EveAdapter;
import com.proyectA.NearMe.Modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class ListEventos extends AppCompatActivity {

    RecyclerView rv;
    List<Evento> eventos;

    EveAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_eventos);
        rv = (RecyclerView) findViewById(R.id.eventoslist);
        rv.setLayoutManager(new LinearLayoutManager(this));

        eventos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new EveAdapter(eventos);

        rv.setAdapter(adapter);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventos.removeAll(eventos);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Evento evento = snapshot.getValue(Evento.class);
                    eventos.add(evento);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}