package com.zheil.zodiac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**Промежуточный класс, обеспечивает появление экрана загрузки*/
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startNewActivity();
    }

    /**Вызывает LoadingActivity, а данное закрывает*/
    private void startNewActivity() {
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        finish();
    }
}