package com.example.ls31084.practicaprojectes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/* Per accedir a la carpeta on està aquest fitxer, copia el teu directori a sota del meu, per tal
 * de fer més accesible el directori per compartir-ho amb git.
 *
 * Directori Aleix ➔
 *      Activitats ➔ C:\Users\Alba\AndroidStudioProjects\PracticaProjectes\app\src\main\java\com\example\ls31084\practicaprojectes
 *      Layouts ➔
 *
 * Directori Carles ➔
 *      Activitats ➔
 *      Layouts ➔
 */

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}