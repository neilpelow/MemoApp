package com.example.neilpelow.memoapp; /**
 * Created by neilpelow on 11/11/15.
 */

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neilpelow.memoapp.R;

class CustomAdapter extends ArrayAdapter<Memos> {

    public CustomAdapter (Context context) {
        super(context, R.layout.custom_row);
    }

    @Override
    public void add(Memos object) {
        super.add(object);
    }

    @Override
    public Memos getItem(int position) {
        return super.getItem(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater memoInflater = LayoutInflater.from(getContext());
        View customView = convertView;
        if (customView == null) {
            customView = memoInflater.inflate(R.layout.custom_row, null);
        }

        Memos memos = getItem(position);
        //String singleMemoItem = getItem(position);
        TextView memoText = (TextView) customView.findViewById(R.id.memoText);
        ImageView memoImage = (ImageView) customView.findViewById(R.id.placeholderImage);

        memoText.setText(memos.get_memoname());
        memoImage.setImageResource(R.drawable.ic_launcher);
        return customView;

    }
}