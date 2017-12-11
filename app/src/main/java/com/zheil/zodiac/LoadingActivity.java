package com.zheil.zodiac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.glomadrian.loadingballs.BallView;

import java.util.HashMap;

import com.zheil.zodiac.ZodiacParser.AsyncParser;
import com.zheil.zodiac.ZodiacParser.iCallbackParsingService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Экран загрузки, который будет отображаться, пока идет процес парсинга
 * */
public class LoadingActivity extends AppCompatActivity implements iCallbackParsingService {

    /**Хранит количество знаков зодиака*/
    private final int NUMBER_OF_ZODIAC = 12;


    /**Погресс бар. Нужен для показа анимации загрузки, дабы пользователь знал, что программа не зависла*/
    @BindView(R.id.bv_progress_bar_loading_parsing) BallView mProgressBarLoading;


    /**Информация о ходе процессора парсинга*/
    @BindView(R.id.tv_infoForParsing) TextView mTvInfoForParsing;


    /**Кнопка для повторного подключения. По умолчанию она невидима*/
    @BindView(R.id.btn_RepeatConnect) Button mBtnRepeatConnect;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        startAsyncParsing();
    }


    /**Начинает асинхронный парсинг*/
    private void startAsyncParsing() {
        AsyncParser asyncZodiacsParser = new AsyncParser(this);
        asyncZodiacsParser.execute();
    }


    /**
     * Результат парсинга
     * Проверяет данные на их количество и на их инициализацию
     * Если все хорошо, вызывает функцию, которая откроет новое activity
     * @param zodiac Коллекция с распарсенными данными
     * */
    @Override
    public void doParsingFinish(HashMap zodiac) {
        if((zodiac==null) || (zodiac.size() != NUMBER_OF_ZODIAC)) {
            errorToParsing();
        } else {
            startMainActivity(zodiac);
        }
    }

    /**Кнопка для повторного подключения, возвращает все UI-элементы в их исходное состояние*/
    @OnClick(R.id.btn_RepeatConnect)
    public void onClickRepeatConnect() {
        restoreUiElements();
        startAsyncParsing();
    }

    /**Запускает основное Acitivity, в котором будет отображаться пользовательская информация*/
    private void startMainActivity(HashMap zodiac) {
        Intent intent = new Intent(this, ListZodiacActivity.class);
        intent.putExtra("zodiac", zodiac);
        startActivity(intent);
        finish();
    }

    /**Восстанавливает все UI-элементы в Activity на исходные*/
    private void restoreUiElements() {
        mBtnRepeatConnect.setVisibility(View.INVISIBLE);
        mTvInfoForParsing.setText(R.string.loading);
        mProgressBarLoading.start();
    }

    /**
     * Случиться, если во время парсинга произойдет ошибка
     * Показывает сообщение про ошибку, делает кнопку повторного подключения видимой
     * */
    private void errorToParsing() {
        mTvInfoForParsing.setText(R.string.connect_fail);
        mBtnRepeatConnect.setVisibility(View.VISIBLE);
        mProgressBarLoading.stop();
    }
}