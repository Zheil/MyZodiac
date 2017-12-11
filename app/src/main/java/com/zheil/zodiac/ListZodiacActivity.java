package com.zheil.zodiac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;

/**
 * Класс для вывода гороскопа, знаков зодиака, изображений, обеспечения работы UI
 * Главная форма.
 * Для работы (показа информации) используется RecycleView
 * */
public class ListZodiacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Получает информацию из прошлого Activity
     * Вызывает метод, который потом выполняет инициализацию RecycleView
     * */
    private void init() {
        Intent intent = getIntent();
        HashMap hashMap = (HashMap) intent.getSerializableExtra("zodiac");
        initRecycleViewZodiac(hashMap);
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