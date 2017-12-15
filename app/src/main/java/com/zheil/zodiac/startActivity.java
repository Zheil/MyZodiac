package com.zheil.zodiac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zheil.zodiac.ui.LoadingActivity;


/**
 * Точка входа в программу
 * Промежуточное Activity, выполняет роль экрана загрузки (Splash Screen)
 * */
public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startNewActivity();
    }

    /**Вызывает новое Activity: {@link LoadingActivity}, а данное закрывает*/
    private void startNewActivity() {
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        finish();
    }
}