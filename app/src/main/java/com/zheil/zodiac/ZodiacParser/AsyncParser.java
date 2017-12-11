package com.zheil.zodiac.ZodiacParser;

import android.os.AsyncTask;

import java.util.HashMap;

/**
 * Класс для выполнения парсинга асинхронно.
 * Является служебным классом для выполнения данных потоке.
 * Логики парсера здесь нет, она находится в классе-парсере CoreParser
 * */

public class AsyncParser extends AsyncTask<Void, Void, Void> {

    /** Ссылка на объект парсера, в нем происходит работа с разбором элементов*/
    private final CoreParser CORE_PARSER;


    /**Интерфейс для обратного вызова по завершению парсинга*/
    private final iCallbackParsingService CALLBACK_DELEGATE;


    /**
     * Содержит распарсенные данные в виде "ключ" - "значение"
     * Где "ключ" - наименование зодиака
     * Где "значение" - распарсенное значение (описание зодиака)
     * */
    private HashMap mZodiacMap;


    /**
     * Конструктор объекта
     * Для работы нужно получить ссылку интерфейса из Activity, для возможности обратного вызова
     * @param IResultParsing Ссылка на интерфейс для обратного вызова. Метод интерфейса будет вызван по завершению работы
     * */
    public AsyncParser(iCallbackParsingService IResultParsing) {
        this.CALLBACK_DELEGATE = IResultParsing;
        CORE_PARSER = new CoreParser();
    }


    /**Будет вызван по завершению работы doInBackground*/
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        CALLBACK_DELEGATE.doParsingFinish(mZodiacMap);
    }

    /**Выполняется парсинг*/
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mZodiacMap = CORE_PARSER.startParse();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}