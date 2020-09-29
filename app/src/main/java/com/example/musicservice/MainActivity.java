package com.example.musicservice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //AUTORES: LUIS RAMOS MONCAYO Y BEATRIZ DELGADO GUERRERO

    Button play_pausa, btn_repetir;
    MediaPlayer ap;
    ImageView iv;
    TextView cancion;
    int      posicion = 0;

    //La clase MediaPlayer es la que nos proporciona Android para interactuar con elementos multimedia.
    MediaPlayer coleccionmp[] = new MediaPlayer[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relacion entre la parte logica y la parte grafica
        play_pausa = (Button) findViewById(R.id.btnPlay);
        cancion = (TextView) findViewById(R.id.textView2);

        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        coleccionmp[0] = MediaPlayer.create(this, R.raw.mike1);
        coleccionmp[1] = MediaPlayer.create(this, R.raw.mike2);
        coleccionmp[2] = MediaPlayer.create(this, R.raw.mike3);
        coleccionmp[3] = MediaPlayer.create(this, R.raw.mike4);
        coleccionmp[4] = MediaPlayer.create(this, R.raw.mike5);
    }

    //ESTA FUNCION PERMITE DARLE PLAY O PAUSA
    public void PlayPause(View view) {
        //EL STRING PERMITE LLAMAR AL NOMBRE DE LAS CANCIONES Y MOSTRARTLA EN EL ACTIVITY
        String[] cancionero = getResources().getStringArray(R.array.canciones);
        //EN CASO DE QUE ESTE EN REPRODUCCION LO PAUSA
        if (coleccionmp[posicion].isPlaying()) {
            coleccionmp[posicion].pause();
            play_pausa.setBackgroundResource(R.drawable.play);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        }
        //EN CASO DE QUE ESTE ESTE EN PAUSA LE DA A PLAY
        else {
            coleccionmp[posicion].start();
            play_pausa.setBackgroundResource(R.drawable.pausa);
            cancion.setText(cancionero[posicion]);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }

    //FUNCION SIGUIENTE QUE PERMITE DAR A LA CANCION SIGUIENTE
    public void Siguiente(View view) {
        //EL STRING PERMITE LLAMAR AL NOMBRE DE LAS CANCIONES Y MOSTRARTLA EN EL ACTIVITY
        String[] cancionero = getResources().getStringArray(R.array.canciones);
        //SI LA POSICION ES MENOR QUE LA CANTIDAD DE CANCIONES VA DARLE SIGUIENTE
        if (posicion < coleccionmp.length - 1) {
            //EN CASO DE QUE ESTE EN REPRODUCCION LO DETIENE Y AUMENTA LA POSICION Y LE DA A PLAY
            if (coleccionmp[posicion].isPlaying()) {
                coleccionmp[posicion].stop();
                posicion++;
                coleccionmp[posicion].start();
            } else {
                //SOLO AVANZA LA POSICION
                posicion++;
            }
            //SETEA EL TEXTVIEW PARA MOSTRAR LA CANCION
            cancion.setText(cancionero[posicion]);
        }
        //CASO CONTRARIO IDICARA QUE YA NO HAY MÁS CANCIONES
        else {
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo para regresar a la cancion anterior
    public void Anterior(View view) {
        //EL STRING PERMITE LLAMAR AL NOMBRE DE LAS CANCIONES Y MOSTRARTLA EN EL ACTIVITY
        String[] cancionero = getResources().getStringArray(R.array.canciones);
        if (posicion >= 1) {
            if (coleccionmp[posicion].isPlaying()) {
                coleccionmp[posicion].stop();
                //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
                coleccionmp[0] = MediaPlayer.create(this, R.raw.mike1);
                coleccionmp[1] = MediaPlayer.create(this, R.raw.mike2);
                coleccionmp[2] = MediaPlayer.create(this, R.raw.mike3);
                coleccionmp[3] = MediaPlayer.create(this, R.raw.mike4);
                coleccionmp[4] = MediaPlayer.create(this, R.raw.mike5);
                posicion --;

                coleccionmp[posicion].start();

            } else {
                posicion--;
            }
            cancion.setText(cancionero[posicion]);
        } else {
            Toast.makeText(this, "No hay mas canciones", Toast.LENGTH_SHORT).show();
        }
    }


}