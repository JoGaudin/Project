package com.example.project;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomBaseAdaptater extends BaseAdapter {

    Context ctx;
    String[] categories;
    int[] categoryImages;
    LayoutInflater inflater;

    public CustomBaseAdaptater(Context ctx, String[] categories, int[] categoryImages){
        this.ctx = ctx;
        this.categories = categories;
        this.categoryImages = categoryImages;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        ImageView catImg = (ImageView) convertView.findViewById(R.id.imageIcon);
        txtView.setText(categories[position]);
        catImg.setImageResource(categoryImages[position]);
        return convertView;
    }
}
