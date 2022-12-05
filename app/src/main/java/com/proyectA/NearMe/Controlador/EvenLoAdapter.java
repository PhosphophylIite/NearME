package com.proyectA.NearMe.Controlador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectA.NearMe.Details_evento;
import com.proyectA.NearMe.Details_eventoLo;
import com.proyectA.NearMe.Modelo.Evento;
import com.proyectA.NearMe.R;

import java.util.List;

public class EvenLoAdapter extends RecyclerView.Adapter<EveAdapter.EventoViewHolder> {

    List<Evento> eventos;

    public EvenLoAdapter(List<Evento> eventos){
        this.eventos = eventos;
    }

    @Override
    public EveAdapter.EventoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        EveAdapter.EventoViewHolder holder = new EveAdapter.EventoViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EveAdapter.EventoViewHolder holder, int position) {
        Evento evento = eventos.get(position);
        //String strNom = Long.toString(evento.getNombre());
        //String strUbi = Long.toString(evento.getUbicacion());

        holder.tvNombre.setText(evento.getNombre());
        holder.tvUbicacion.setText(evento.getUbicacion());
        holder.tvFecha.setText(evento.getFecha());
        holder.tvHora.setText(evento.getHora());

        //ITEM SOLITARIO
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Details_evento fragment_evento = new Details_evento();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rec, fragment_evento).addToBackStack(null).commit();
                 */
                Intent intent = new Intent(view.getContext(), Details_eventoLo.class);
                intent.putExtra("Titulos",evento.getNombre());
                intent.putExtra("Horas",evento.getHora());
                intent.putExtra("Fechas",evento.getFecha());
                intent.putExtra("Ubicaciones",evento.getUbicacion());
                intent.putExtra("Descripciones",evento.getDescripcion());
                intent.putExtra("ID",evento.getID());
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder{

        TextView tvNombre, tvUbicacion,tvasistencia, tvFecha, tvHora;

        public EventoViewHolder(View itemview){
            super(itemview);
            tvNombre = (TextView) itemview.findViewById(R.id.nombre_evento);
            tvUbicacion = (TextView) itemview.findViewById(R.id.ubicacion_evento);
            tvFecha = (TextView) itemview.findViewById(R.id.Fecha_evento);
            tvHora = (TextView) itemview.findViewById(R.id.Hora_evento);

        }

    }
}
