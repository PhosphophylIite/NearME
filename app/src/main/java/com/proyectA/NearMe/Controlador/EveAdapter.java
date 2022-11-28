package com.proyectA.NearMe.Controlador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.proyectA.NearMe.Modelo.Evento;
import com.proyectA.NearMe.R;

import java.util.List;

public class EveAdapter extends RecyclerView.Adapter<EveAdapter.EventoViewHolder> {

    List<Evento> eventos;

    public EveAdapter(List<Evento> eventos){
        this.eventos = eventos;
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        EventoViewHolder holder = new EventoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        String strNom = Long.toString(evento.getNombre());
        String strUbi = Long.toString(evento.getUbicacion());

        holder.tvNombre.setText(strNom);
        holder.tvUbicacion.setText(strUbi);
        holder.tvasistencia.setText(evento.getAsistentes());

    }


    @Override
    public int getItemCount() {
        return eventos.size();
    }


    public static class EventoViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvUbicacion,tvasistencia;

        public EventoViewHolder(View itemview){
            super(itemview);
            tvNombre = (TextView) itemview.findViewById(R.id.nombre_evento);
            tvUbicacion = (TextView) itemview.findViewById(R.id.ubicacion_evento);
            tvasistencia = (TextView) itemview.findViewById(R.id.num_asistentes);

        }

    }
}
