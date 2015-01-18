package com.example.vvm.shpplistview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new ColorsAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ColorsAdapter adapter = (ColorsAdapter) parent.getAdapter();
                adapter.setRandomColor(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private static class ColorsAdapter extends BaseAdapter {

        private static final int MAX_ELEMENT = 255;
        private Integer[] mColors;
        private LayoutInflater mInflater;
        private final Random mRandom;

        private ColorsAdapter(Context context) {
            mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            mRandom = new Random();
            mColors = new Integer[1000];
            for (int i = 0; i < mColors.length; i++) {
                mColors[i] = Color.rgb(mRandom.nextInt(MAX_ELEMENT),
                        mRandom.nextInt(MAX_ELEMENT),
                        mRandom.nextInt(MAX_ELEMENT));
            }
        }

        @Override
        public int getCount() {
            return mColors.length;
        }

        @Override
        public Object getItem(int position) {
            return mColors[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void setColor(int position, Integer newColor) {
            mColors[position] = newColor;
        }

        public void setRandomColor(int position) {
            setColor(position, Color.rgb(mRandom.nextInt(MAX_ELEMENT),
                    mRandom.nextInt(MAX_ELEMENT),
                    mRandom.nextInt(MAX_ELEMENT)));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item, parent, false);
                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(android.R.id.text1);
                holder.color = convertView.findViewById(R.id.color);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final Integer color = mColors[position];
            holder.color.setBackgroundColor(color);
            holder.text.setText(color.toString());

            return convertView;
        }

        private static class ViewHolder {
            TextView text;
            View color;
        }

    }

}
