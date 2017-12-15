package com.zheil.zodiac.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.glomadrian.loadingballs.BallView;

import java.util.HashMap;

import com.zheil.zodiac.R;
import com.zheil.zodiac.ZodiacParser.AsyncParser;
import com.zheil.zodiac.ZodiacParser.iCallbackParsingService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * Класс обеспечивает появления экрана загрузки,
 * который будет отображать псевдо-прогресс, пока идет процесс парсинга
 * На данный момент, класс не содержит инструкций по выводу информации о прогрессе парсинга
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
     * Данный метод будет вызван по завершению процесса парсинга
     * Метод проверяет входные данные на их корректность, после чего
     * делает вывод о их целостности.
     * Входная коллекция ДОЛЖНА иметь количество элементов не меньше, чем количество знаков зодиака (их 12!)
     * Именно по целостности коллекции, будет сделан вывод о результате парсинга
     *
     * Если парсинг прошел успешно, полученная коллекция будет передана в новую Activity, которая
     * также будет вызвана.
     * Если метод делает вывод, что полученная коллекция не соответствует ожиданиям,
     * бросает UI-оповещение, про ошибку
     *
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
     * Метод будет выполнен, если полученная во время парсинга коллекция, не будет удовлетворять
     * условиям.
     * Показывает сообщение про ошибку, делает кнопку повторного подключения видимой
     * */
    private void errorToParsing() {
        mTvInfoForParsing.setText(R.string.connect_fail);
        mBtnRepeatConnect.setVisibility(View.VISIBLE);
        mProgressBarLoading.stop();
    }
}