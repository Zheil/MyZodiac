package com.zheil.zodiac.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zheil.zodiac.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Класс-адаптер для настройки, выполнения UI-элемента RecycleView
 * RecycleView осуществляет вывод знаков зодиака, гороскопов, изображений
 * */
public class RecycleViewZodiac extends RecyclerView.Adapter<RecycleViewZodiac.ViewHolder> {

    /**Массив содержит наименования знаков зодиака*/
    private final ArrayList<String> LIST_NAME_OF_ZODIAC;


    /**
     * Содержит значения:
     * "key" - имя знака зодиака
     * "value" - гороскоп для знака зодиака
     * */
    private final HashMap MAP_DESCRIPTION_OF_ZODIAC;



    /**Название директории, где нужно искать изображения.*/
    private final String NAME_OF_PATH_DRAWABLE = "drawable";



    /**Контекст для доступа к базовым функциям приложения*/
    private Context mContext;



    /**
     * Конструктор класса, нужен для инициализации, получения данных из вне.
     * Вызывает функцию для считывания всех ключей из HashMap
     * @param zodiac Данные о знаках зодиака. Имена и гороскоп
     * */
    RecycleViewZodiac(HashMap<String, String> zodiac) {
        MAP_DESCRIPTION_OF_ZODIAC = zodiac;
        LIST_NAME_OF_ZODIAC = new ArrayList<>();
        addKeys(zodiac);
    }


    /**Считывает все ключи из HashMap и сохраняет их в отдельную коллекцию*/
    private void addKeys(HashMap<String, String> zodiac) {
        Set<String> keys = new HashSet<>(zodiac.keySet());
        keys.addAll(zodiac.keySet());
        LIST_NAME_OF_ZODIAC.addAll(keys);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(parent.getContext(), R.layout.recycle_view_item_zodiac, null);
        mContext = parent.getContext();
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvNameOfZodiac.setText(LIST_NAME_OF_ZODIAC.get(position));
        holder.mTvDescription.setText((String) MAP_DESCRIPTION_OF_ZODIAC.get(LIST_NAME_OF_ZODIAC.get(position)));
        Picasso.with(mContext)
                .load(mContext.getResources().getIdentifier(
                        LIST_NAME_OF_ZODIAC.get(position),
                        NAME_OF_PATH_DRAWABLE,
                        mContext.getApplicationContext().getPackageName()))
                .into(holder.mIvZodiac);
    }

    @Override
    public int getItemCount() {
        return LIST_NAME_OF_ZODIAC.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mIvZodiac;
        TextView mTvNameOfZodiac, mTvDescription;

         ViewHolder(View itemView) {
            super(itemView);
            mIvZodiac = itemView.findViewById(R.id.iv_imageZodiac);
            mTvNameOfZodiac = itemView.findViewById(R.id.tv_NameOfZodiac);
            mTvDescription = itemView.findViewById(R.id.tv_DescriptZodiac);
        }
    }
}