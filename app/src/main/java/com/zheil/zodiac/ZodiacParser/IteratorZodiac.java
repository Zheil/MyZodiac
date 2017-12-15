package com.zheil.zodiac.ZodiacParser;

/**
 * Класс необходим для работы парсинга
 * Основная задача класса - это поэлементная передача информации, такой как:
 * название знака зодиака, URL-сайт
 * */
class IteratorZodiac {

    /**
     * Содержит метку. Она будет конкатенирована  в переменную, которая хранит URL-адрес.
     * Позже вместо метки, будет подставлено имя знака зодиака.
     * В итоге мы получим готовую URL-ссылку на HTML-документ для его парсинга
     */
    private final String MARK_OF_ZODIAC = "MARK_OF_ZODIAC";


    /**
     * Хранит ссылку на HTML-документ
     * Внутри находится переменная-метка, позже она будет заменена названием знака зодиака
     * */
    private final String SITE =
            "http://orakul.com/horoscope/astrologic/general/"+ MARK_OF_ZODIAC +"/today.html";


    /**
     * Массив хранит все наименования знаков зодиака
     * Важно для парсинга, в будущем они станут "ключом" в hashTable
     * */
    private final String[] ARRAY_OF_ZODIACS = new String[]{"aries", "taurus", "gemini", "cancer",
            "lion", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "pisces"};


    /**Содержит индекс текущего элемента*/
    private int mCurrentIndex = 0;


    /**
     * Заменяет метку наименованием знака зодиака
     * @return Возвращает новую строку, где метка была заменена текущим именем знака зодиака
     * */
     String getCurrentUrlZodiac() {
        return SITE.replace(MARK_OF_ZODIAC, ARRAY_OF_ZODIACS[mCurrentIndex]);
    }

    /**
     * Возвращает из массива наименование знака зодиака
     * @return Имя текущего знака зодиака
     * */
     String getCurrentNameZodiac() {
        return ARRAY_OF_ZODIACS[mCurrentIndex];
    }


    /**
     * Переходим на следующий элемент коллекцию
     * Увеличивает индекс на единицу, позволяя работать уже со следующим элементом массива
     * */
     void nextElement() {
        if((mCurrentIndex + 1) <= ARRAY_OF_ZODIACS.length) {
            mCurrentIndex++;
        }
    }

    /**
     * Сверяет текущий индекс с размером массива
     * Возвращает логическое значение true, если не все элементы были пройдены
     * Возвращает false, если все элементы были пройдены
     * */
     boolean hasNext() {
         return mCurrentIndex != ARRAY_OF_ZODIACS.length;
     }
}