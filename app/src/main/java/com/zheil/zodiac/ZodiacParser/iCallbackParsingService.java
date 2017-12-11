package com.zheil.zodiac.ZodiacParser;

import java.util.HashMap;

/**
 *  Интерфейс содержит методы для обратного вызова, которые будут выполнены по завершению парсинга
 *  Основная задача интерфейса, вернуть коллекцию HashMap из асинхронного вызова
 * */
public interface iCallbackParsingService {

    /**
     * Метод для обратного вызова после парсинга
     * @param zodiac Коллекция, которая должна содержать значения:
     *     "key" - наименование зодиака
     *     "value" - гороскоп, взятый из HTML-страницы
     * */
    void doParsingFinish(HashMap zodiac);
}