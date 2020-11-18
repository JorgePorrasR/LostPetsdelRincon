package mx.com.systemjorge.lostpetsdelrincon;

import android.app.Dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Create By JorgeRPorras
public class AdaptadorPerdidos
        extends RecyclerView.Adapter<AdaptadorPerdidos.ViewHolderPersonajes>
        implements View.OnClickListener {

    ArrayList<PerdidoVo> listaPerdidos;
    private View.OnClickListener listener;

    private Dialog mDialogImagen;
    private ImageView mImageDialog;

    public AdaptadorPerdidos(ArrayList<PerdidoVo> listaPerdidos) {
        this.listaPerdidos = listaPerdidos;
    }

    public void addLista(ArrayList<PerdidoVo> listaPerdidos) {

        this.listaPerdidos.clear();
        this.listaPerdidos.addAll(listaPerdidos);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = 0;
        if (Utilidades.visualizacion == Utilidades.LIST) {
            layout = R.layout.iem_list_perdidos;
        } else {
            layout = R.layout.item_grid_perdidos;
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);

        view.setOnClickListener(this);

        mDialogImagen = new Dialog(parent.getContext());
        mDialogImagen.setContentView(R.layout.dialog_imagen);
        mImageDialog = mDialogImagen.findViewById(R.id.bigImage);
        mDialogImagen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return new ViewHolderPersonajes(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {

        holder.etiNombre.setText(listaPerdidos.get(position).getNombre());

        if (Utilidades.visualizacion == Utilidades.LIST) {
            holder.etiInformacion.setText(listaPerdidos.get(position).getDescripcion());

        }

        final String mImagen = listaPerdidos.get(position).getUrl();


        //holder.foto.setImageResource(listaPerdidos.get(position).getFoto());



        //Log.v("url", listaPerdidos.get(position).getFoto());
        holder.asignarDatosItem(listaPerdidos.get(position));


    }

    @Override
    public int getItemCount() {
        return listaPerdidos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }


    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {


        Context context;
        TextView etiNombre, etiInformacion;
        ImageView foto;


        //Context context;
        //String url = "https://static.diariofemenino.com/media/840/c/27-bonitos-nombres-para-perros-que-son-tendencia-en-2019-lg.jpg";
        //Mi avanze del 19 de octubre hasta aqui llegamos jjj

        public ViewHolderPersonajes(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String imagen = listaPerdidos.get(getAdapterPosition()).getUrl();
                    showBigImage(imagen, v);
                }
            });

            context = itemView.getContext();
            etiNombre = (TextView) itemView.findViewById(R.id.idNombre);
            if (Utilidades.visualizacion == Utilidades.LIST) {
                etiInformacion = (TextView) itemView.findViewById(R.id.idInfo);
            }
            foto = (ImageView) itemView.findViewById(R.id.idImagen);




        }


        public void asignarDatosItem(PerdidoVo perdidoVo) {

            Glide
                    .with(context)
                    .load(perdidoVo.getUrl())
                    .circleCrop()
                    //.placeholder(R.drawable.loading_spinner)
                    .into(foto);


        }

    }

    private void showBigImage(String imagen, View v) {
        Glide
                .with(v)
                .load(imagen)
                .circleCrop()
                .into(mImageDialog);

        mDialogImagen.show();

    }


}
