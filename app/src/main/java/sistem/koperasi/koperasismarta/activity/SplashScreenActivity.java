package sistem.koperasi.koperasismarta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import sistem.koperasi.koperasismarta.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        long Delay = 5000; // 5second
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent= new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.apper,R.anim.out);
                finish();

            }
        },Delay);
    }

}