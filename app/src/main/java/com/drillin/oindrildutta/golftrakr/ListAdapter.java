package com.drillin.oindrildutta.golftrakr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private final Context context;
    private final int[] holes;

    public ListAdapter(Context mcontext, int[] holez) {
        context = mcontext;
        holes = holez;
    }

    @Override
    public int getCount() {
        return holes.length;
    }

    @Override
    public Object getItem(int position) {
        return new Integer(holes[position]);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.holeLabel = (TextView)convertView.findViewById(R.id.holeLabel);
            holder.strokeCount = (TextView)convertView.findViewById(R.id.strokeCount);
            holder.strokeAdd = (Button)convertView.findViewById(R.id.addStroke);
            holder.strokeMinus = (Button)convertView.findViewById(R.id.subStroke);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLabel.setText("Hole "+(position+1)+":");
        holder.strokeCount.setText(holes[position] + "");
        holder.strokeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holes[position] > 0) {
                    holes[position]--;
                    holder.strokeCount.setText("" + holes[position]);
                }
            }
        });
        holder.strokeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holes[position] < 50) {
                    holes[position]++;
                    holder.strokeCount.setText("" + holes[position]);
                }
            }
        });
        return convertView;
    }

    private static class ViewHolder {
        TextView holeLabel;
        TextView strokeCount;
        Button strokeAdd;
        Button strokeMinus;
    }
}
