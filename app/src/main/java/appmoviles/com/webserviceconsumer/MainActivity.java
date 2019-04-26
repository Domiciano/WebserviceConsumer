package appmoviles.com.webserviceconsumer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button btn_get;
    private Button btn_post;
    private TextView txt_console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_post = findViewById(R.id.btn_post);
        btn_get = findViewById(R.id.btn_get);
        txt_console = findViewById(R.id.txt_console);
        txt_console.setMovementMethod(new ScrollingMovementMethod());

        /*
        new Thread(()->{
            new ServiceManager.SimpleGET(new ServiceManager.SimpleGET.OnResponseListener() {
                @Override
                public void onResponse(String response) {
                    runOnUiThread(()->{
                        txt_console.setText(response);
                    });
                }
            });
        }).start();
        */

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    new ServiceManager.usuariosGET(new ServiceManager.usuariosGET.OnResponseListener() {
                        @Override
                        public void onResponse(String response) {
                            runOnUiThread(() -> {
                                txt_console.setText(response);

                                Type tipo = new TypeToken<HashMap<String, Usuario>>() {

                                }.getType();

                                Gson g = new Gson();
                                HashMap<String, Usuario> usuarios = g.fromJson(response, tipo);
                                txt_console.append("\n\n+Usuarios: " + usuarios.size());
                                txt_console.append("\nUsuario 0:" + usuarios.get("0tIu7M5u4kbGpYAD181Holv0pJU2").nombre);


                            });
                        }
                    });
                }).start();

            }
        });


        btn_post.setOnClickListener(v -> {

            new Thread(() -> {
                Usuario usuario = new Usuario();
                usuario.nombre = "Dummy";
                usuario.correo = "dumm@gmail.com";
                usuario.id = "0";
                usuario.telefono = "123123";
                usuario.pass = "24234234";
                new ServiceManager.usuariosPOST(usuario, new ServiceManager.usuariosPOST.OnResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        runOnUiThread(() -> {
                            txt_console.setText(response);
                        });
                    }
                });
            }).start();


        });


    }
}
