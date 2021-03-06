package com.example.caloriesnculture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    public ArrayList<FoodDTO> listCustom = new ArrayList<>();
    CustomViewHolder holder;



    // ListView에 보여질 Item 수
    @Override
    public int getCount() {
        return listCustom.size();
    }

    // 하나의 Item(ImageView 1, TextView 2)
    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, null, false);

            holder = new CustomViewHolder(convertView);


            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        FoodDTO dto = listCustom.get(position);


        holder.textTitle.setText(dto.getTitle());
        holder.textContent.setText(dto.getContent());

        return convertView;
    }

    public void clear() {
        listCustom.clear();
    }

    class CustomViewHolder {

        TextView textTitle;
        TextView textContent;
        public CustomViewHolder(View convertView){

            textTitle = (TextView) convertView.findViewById(R.id.text_title);
            textContent = (TextView) convertView.findViewById(R.id.text_content);
        }

    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(FoodDTO dto) {
        listCustom.add(dto);
    }
}