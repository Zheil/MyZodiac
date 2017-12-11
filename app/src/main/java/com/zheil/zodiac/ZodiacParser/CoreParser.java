package com.zheil.zodiac.ZodiacParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

/**
 * Класс содержит методы для парсинга HTML-документа (подключения, поиск по HTML-документу),
 * а также для его хранения.
 * За работу с HTML-документом отвечает библиотека Jsoup
 * Напоминание! Jsoup - это разновидность DOM-парсера!
 * */
class CoreParser {


    /**Объект отвечает за поэлементную передачу данных: наименование знака зодиака, URL для парсинга HTML.*/
    private final IteratorZodiac ITERATOR_ZODIAC;


    /**
     * Содержит распарсенные значения
     * "key" - имя знака зодиака
     * "value" - распарсенное описание знака зодиака
     * */
    private final HashMap<String, String> MAP_ZODIAC;


    /**
     * Хранит текущий URL
     * Ссылка на объект является глобальной, дабы лишний раз ее не пересоздавать
     * */
    @SuppressWarnings("FieldCanBeLocal")
    private String mUrlSite;


    /**
     * Содержит текущую HTML страницу
     *  Ссылка на объект является глобальной, дабы лишний раз ее не пересоздавать
     *  */
    @SuppressWarnings("FieldCanBeLocal")
    private Document mCurrentWebSite;


    /**
     * Содержит значение текущего элемента
     * Ссылка на объект является глобальной, дабы лишний раз ее не пересоздавать
     * */
    @SuppressWarnings("FieldCanBeLocal")
    private Element mElement;



    /**Конструктор объекта
     * Инициализирует объекты*/
    CoreParser() {
        ITERATOR_ZODIAC = new IteratorZodiac();
        MAP_ZODIAC = new HashMap<>();
    }


    /**
     * Начинает парсить описание для знака зодиака.
     * Управляет процессом парсинга,
     * сохраняет наименования знаков зодиака, получает описания знака зодиака
     * сохраняет полученные значения в HashMap
     * @return Возвращает готовую, заполненную коллекцию со знаками зодиака и их описанием из сайта
     * */
    HashMap startParse() throws Exception{
        while(ITERATOR_ZODIAC.hasNext()) {
            String nameZodiac = ITERATOR_ZODIAC.getCurrentNameZodiac();
            String descriptionZodiac = getDescriptionFromWebSite();
            putToHashMap(nameZodiac, descriptionZodiac);
            ITERATOR_ZODIAC.nextElement();
        }
        return MAP_ZODIAC;
    }


    /**
     * Добавляет элементы в коллекцию HashMap
     * @param nameZodiac Имя знака зодиака
     * @param descriptionZodiac Описание гороскопа
     * */
    private void putToHashMap(String nameZodiac, String descriptionZodiac) {
        MAP_ZODIAC.put(nameZodiac, descriptionZodiac);
    }


    /**
     * Парсит описание знака зодиака из сайта
     * Функция подключается к сайту, получает HTML-страницу
     * Ищет нужный HTML-тег и получает первое его значение
     * @return Возвращает описание знака зодиака
     * */
    private String getDescriptionFromWebSite() throws Exception{
        mUrlSite = ITERATOR_ZODIAC.getCurrentUrlZodiac();
        mCurrentWebSite = Jsoup.connect(mUrlSite).get();
        mElement = mCurrentWebSite.select("div.horoBlock").select("p").first();
        return mElement.text();
    }
}