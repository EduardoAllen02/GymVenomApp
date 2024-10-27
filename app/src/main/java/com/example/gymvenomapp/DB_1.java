package com.example.gymvenomapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DB_1 extends AppCompatActivity {
    ConnectionClass connectionClass;
    Connection con;
    ResultSet   rs;
    String name, str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_db1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        connect();
        connectionClass = new ConnectionClass();

    }

    public void btnMostrar(View view) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() ->{
            try {
                con = connectionClass.CONN();
                String query = "SELECT * FROM bdgym.usuario";
                PreparedStatement statement = con.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                StringBuilder bStr = new StringBuilder("Lista de Usuarios: \n");

                while(rs.next()){
                   bStr.append(rs.getString("Nombre")).append("\n");
                }
                name= bStr.toString();
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            runOnUiThread(() ->{
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                TextView txtList = findViewById(R.id.textView8);
                txtList.setText(name);
            });

        });
    }
    public void connect(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() ->{
          try {
              con = connectionClass.CONN();
              if (con != null){
                  str = "Mysql server conectado con exito";
              }else{
                  str = "Error al conectar con Mysql server";
              }
          }catch (Exception e){
              throw new RuntimeException(e);
          }
          runOnUiThread(()->{
              try {
                  Thread.sleep(1000);
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
              Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
          });

        }
        );
    }
}