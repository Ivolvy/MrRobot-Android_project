package com.example.mgenty.mrrobot_android_project.communication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ChatHolder extends RecyclerView.ViewHolder {
    TextView nameView, textView;

    public ChatHolder(View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(android.R.id.text2);
        textView = (TextView) itemView.findViewById(android.R.id.text1);
    }
}
