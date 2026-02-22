package com.example.tp10firebase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import android.widget.Toast;
public class AddMessage extends AppCompatActivity {

    private EditText text, text2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);

        text = findViewById(R.id.editTextText);
        text2 = findViewById(R.id.editTextText2);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(v -> {
            String msg = text.getText().toString().trim();
            String author = text2.getText().toString().trim();

            if (msg.isEmpty()) {
                text.setError("Champ vide !");
                return;
            }
            if (author.isEmpty()) {
                text2.setError("Champ vide !");
                return;
            }
            addMsgDB(msg, author);
        });
    }

    private void addMsgDB(String msg, String author) {
        HashMap<String, Object> msgHashMap = new HashMap<>();
        msgHashMap.put("msg", msg);
        msgHashMap.put("author", author);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference msgRef = database.getReference("msg");

        String key = msgRef.push().getKey();
        msgHashMap.put("key", key);

        msgRef.child(key).setValue(msgHashMap).addOnCompleteListener(task -> {
            Toast.makeText(AddMessage.this, "Enregistré", Toast.LENGTH_LONG).show();
        });

        text.getText().clear();
        text2.getText().clear();
    }
}