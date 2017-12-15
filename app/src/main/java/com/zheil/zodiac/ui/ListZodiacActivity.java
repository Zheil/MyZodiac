package com.zheil.zodiac.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zheil.zodiac.R;

import java.util.HashMap;

/**
 * Класс содержит методы для инициализации компонента RecycleView,
 * который будет отображать информацию про актуальный астрологический гороскоп
 * и содержать некоторые изображения
 * */
public class ListZodiacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlStartRecycleView();
    }

    /**
     * Управляет процессом создания, инициализации RecycleView
     * получает данные из прошлой Activity
     * */
    private void controlStartRecycleView() {
        HashMap zodiacMap = getMapLastIntent();
        initRecycleViewZodiac(zodiacMap);
    }

    /*** Получает информацию из прошлого Activity* */
    private HashMap getMapLastIntent() {
        Intent intent = getIntent();
        HashMap hashMap = (HashMap) intent.getSerializableExtra("zodiac");
        return hashMap;
    }


    /**
     * Инициализация RecycleView, LayoutManager и Adapter
     * @param zodiacMap Уже готовая к работе коллекция, которая хранит данные о знаках зодиаках, их гороскоп. Нужна для вывода данных.
     * */
    private void initRecycleViewZodiac(HashMap zodiacMap) {
        RecyclerView recyclerView = findViewById(R.id.rv_zodiak_item);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        RecyclerView.Adapter adapter = new RecycleViewZodiac(zodiacMap);
        recyclerView.setAdapter(adapter);
    }
}