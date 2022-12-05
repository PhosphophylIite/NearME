package com.proyectA.NearMe.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectA.NearMe.Controlador.EvenLoAdapter;
import com.proyectA.NearMe.Controlador.EventoDBHelper;
import com.proyectA.NearMe.Modelo.Evento;
import com.proyectA.NearMe.Modelo.EventoLocal;
import com.proyectA.NearMe.R;
import com.proyectA.NearMe.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    // Add RecyclerView member
    ArrayList<Evento> listaEvento;
    private RecyclerView recyclerView;
    EventoDBHelper conn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        conn=new EventoDBHelper(root.getContext());

        listaEvento=new ArrayList<>();

        // Add the following lines to create RecyclerView
        recyclerView = root.findViewById(R.id.eventoslist_selected);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        consultarListaPersonas();

        EvenLoAdapter adapter = new EvenLoAdapter(listaEvento);
        recyclerView.setAdapter(adapter);

        return root;
    }
    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Evento evento=null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ EventoLocal.EventosLocales.TABLE_NAME,null);

        while (cursor.moveToNext()){
            evento=new Evento();
            evento.setID((cursor.getString(1)));
            evento.setNombre(cursor.getString(2));
            evento.setFecha(cursor.getString(3));
            evento.setHora(cursor.getString(4));
            evento.setUbicacion(cursor.getString(5));
            evento.setDescripcion(cursor.getString(6));

            listaEvento.add(evento);
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}