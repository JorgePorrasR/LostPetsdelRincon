package mx.com.systemjorge.lostpetsdelrincon;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button menu;
    private DatabaseReference mDatabase;
    PerdidoVo p;
   EditText emailId,PassW;
    //FirebaseAuth FA;
    ArrayList<PerdidoVo> listaPerdidos;
    RecyclerView recyclerPersonajes;
    AdaptadorPerdidos adapter;

    //Create By JorgeRPorras
    private Dialog mDialogLoading;
    private LottieAnimationView mAnimationLoading;

//Creo que lo olvide jaja mas bien no movi nada ok vamos a correr vaaa
//vaa
    //espera jajaj no se ve el boton creo que es error de diseño del layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        mDialogLoading = new Dialog(this);
        mDialogLoading.setContentView(R.layout.dialog_carga);
        mAnimationLoading = mDialogLoading.findViewById(R.id.animationLoading);
        mDialogLoading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        menu = (Button)findViewById(R.id.btnMenu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent menu = new Intent(MainActivity.this, Pantalla2.class);
                startActivity(menu);


            }
        });

        construirRecycler();



    }
    protected void onStart() {
        super.onStart();




    }
    //SI ERAM 2 BROOO
    //ESTOY BUSCANDO LA USB NO TARDOOO
    //SERA QUE BORRE CODIGO :(
//nO ENCUENTRO LA USB BRO :(
    //VUELVO A CORRER ?
    //YA VOLVI
    //DIMEE BRO VAA

    //siii

    //POR ESO NO ME GUSTA MOVERLE :((((((
    //CREO QUE DEBERE HACER UN PROYECTO NUEVOO

    public void llenarPersonajes2() {


        mDatabase = FirebaseDatabase.getInstance().getReference("perrosAlbergues");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    p = dataSnapshot1.getValue(PerdidoVo.class);
                    //p.setFoto(R.drawable.perro);

                    //Log.v("datos", p.getFoto());
                    listaPerdidos.add(p);
                    //Log.v("DATA", p.getDescripcion());
                    //System.out.println(p.getNombre());
                    adapter.notifyDataSetChanged();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        //adapter.addLista(listaPerdidos);


    }

    public void llenarPersonajes() {

        mDatabase = FirebaseDatabase.getInstance().getReference("perros");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Log.v("DATOS", dataSnapshot1.getValue().toString());


                    p = dataSnapshot1.getValue(PerdidoVo.class);
                    //p.setFoto(R.drawable.perro);
                    listaPerdidos.add(p);
                    Log.v("Fotico", (p.getUrl() != null)? p.getUrl(): "URL VACIA");
                    //Log.v("DATA", p.getNombre());

                    adapter.notifyDataSetChanged();
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        //adapter.addLista(listaPerdidos);


    }





    //listaPersonajes.add(new PerdidoVo("f","idoo","perdido",R.drawable.perro));





    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnList: Utilidades.visualizacion=Utilidades.LIST;
                ConnectivityManager connection = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo network = connection.getActiveNetworkInfo();
                if(network != null && network.isConnected()){
                    construirRecycler();
                    llenarPersonajes();
                }else {
                    mDialogLoading.show();
                    Toast.makeText(MainActivity.this, "Usted no tiene acceso a internet", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnAdopccion: Utilidades.visualizacion=Utilidades.LIST2;
                ConnectivityManager connection2 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo network2 = connection2.getActiveNetworkInfo();
                if(network2 != null && network2.isConnected()){
                    construirRecycler();
                    llenarPersonajes2();
                }else {
                    mDialogLoading.show();
                    Toast.makeText(MainActivity.this, "Usted no tiene acceso a internet", Toast.LENGTH_SHORT).show();
                }

                break;


        }

    }




    private void construirRecycler() {
        listaPerdidos = new ArrayList<>();
        recyclerPersonajes= (RecyclerView) findViewById(R.id.RecyclerId);

        recyclerPersonajes.setHasFixedSize(true);
        if (Utilidades.visualizacion==Utilidades.LIST){
            recyclerPersonajes.setLayoutManager(new GridLayoutManager(this, 1));
        }else {
            recyclerPersonajes.setLayoutManager(new GridLayoutManager(this,3));
        }

        adapter = new AdaptadorPerdidos(listaPerdidos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "Selección: "+listaPerdidos.get
                                (recyclerPersonajes.getChildAdapterPosition(view))
                                .getNombre(), Toast.LENGTH_SHORT).show();


            }
        });

        recyclerPersonajes.setAdapter(adapter);



    }


}
