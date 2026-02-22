package com.example.tp10firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import android.app.AlertDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MsgAdapter extends RecyclerView.Adapter<MsgViewHolder> {

    private List<Message> messageList;

    public MsgAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MsgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.msgitem, parent, false);
        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MsgViewHolder holder, int position) {
        Message msg = messageList.get(position);

        holder.msgTv.setText(msg.getMsg());
        holder.authorTv.setText("- " + msg.getAuthor());

        // suppression
        holder.itemView.setOnLongClickListener(v -> {
            DatabaseReference ref = FirebaseDatabase
                    .getInstance()
                    .getReference("msg");
            ref.child(msg.getKey()).removeValue();
            return true;
        });

        // modification
        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            android.widget.EditText input = new android.widget.EditText(v.getContext());
            input.setText(msg.getMsg());
            builder.setView(input);

            builder.setPositiveButton("Modifier", (dialog, which) -> {
                DatabaseReference ref = FirebaseDatabase
                        .getInstance()
                        .getReference("msg");
                msg.setMsg(input.getText().toString());
                ref.child(msg.getKey()).setValue(msg);
            });

            builder.setNegativeButton("Annuler", null);
            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}