package mx.com.systemjorge.lostpetsdelrincon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Pantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menumain);


    }

    public void Menu2(View view){
        Intent menu2 = new Intent(this, MainActivity.class);
        startActivity(menu2);

    }
}
