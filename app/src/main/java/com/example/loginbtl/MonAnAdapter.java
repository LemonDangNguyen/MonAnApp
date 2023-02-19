package com.example.loginbtl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MonAnAdapter extends BaseAdapter {
    ArrayList<MonAn> mListMonAn;
    Context context;

    public MonAnAdapter(ArrayList<MonAn> mListMonAn, Context context) {
        this.mListMonAn = mListMonAn;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mListMonAn.size();
    }

    @Override
    public Object getItem(int position) {
        return mListMonAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_monan, null);
        MonAn monAn =  mListMonAn.get(position);
        ImageView imageView = view.findViewById(R.id.anhMonAn);
        TextView textViewMonAn = view.findViewById(R.id.tenMonAn);
        TextView textViewcongThuc = view.findViewById(R.id.congThucNau);

        byte[] anh = monAn.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imageView.setImageBitmap(bitmap);
        textViewMonAn.setText(monAn.getTenMonAn());
        textViewcongThuc.setText(monAn.getCongThuc());
        return view;
    }
}
