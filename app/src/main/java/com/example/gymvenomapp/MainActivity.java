package com.example.gymvenomapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Referencia al botón en el layout
        Button navigateButton = findViewById(R.id.btnConectPrueba);

        // Configuración del clic en el botón
        navigateButton.setOnClickListener(v -> {
            // Crear el Intent para ir a SecondActivity
            Intent intent = new Intent(MainActivity.this, DB_1.class);
            startActivity(intent); // Iniciar la nueva actividad
        });
    }

}