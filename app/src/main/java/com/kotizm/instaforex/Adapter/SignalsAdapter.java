package com.kotizm.instaforex.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kotizm.instaforex.Model.Message;
import com.kotizm.instaforex.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SignalsAdapter extends RecyclerView.Adapter<SignalsView> {

    private List<Message> messageList;
    private SimpleDateFormat simpleDateFormat;
    private final WeakReference<LayoutInflater> mInflater;

    public SignalsAdapter(List<Message> messageList, LayoutInflater inflater) {
        mInflater = new WeakReference<>(inflater);
        this.messageList = messageList;

        simpleDateFormat = new SimpleDateFormat(
                "dd.MM.yyyy HH:mm:ss", Locale.getDefault());
    }

    @NonNull
    @Override
    public SignalsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = mInflater.get();

        if (inflater != null) {
            return new SignalsView(inflater.inflate(R.layout.activity_signals_item, parent, false));
        } else throw new RuntimeException("Problem with activity!");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SignalsView holder, int position) {

        holder.textView.setText("\nId: " + messageList.get(position).getId());
        holder.textView.append("\n\nActualTime:\n" + simpleDateFormat.format(
                messageList.get(position).getActualTime()*1000
        ));
        holder.textView.append("\n\nComment: " + messageList.get(position).getComment());
        holder.textView.append("\n\nPair: " + messageList.get(position).getPair());
        holder.textView.append("\n\nCmd: " + messageList.get(position).getCmd());
        holder.textView.append("\n\nTradingSystem: " + messageList.get(position).getTradingSystem());
        holder.textView.append("\n\nPeriod: " + messageList.get(position).getPeriod());
        holder.textView.append("\n\nPrice: " + messageList.get(position).getPrice());
        holder.textView.append("\n\nSl: " + messageList.get(position).getSl());
        holder.textView.append("\n\nTp: " + messageList.get(position).getTp() + "\n");
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }
}