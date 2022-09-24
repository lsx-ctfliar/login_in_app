package com.example.login_in_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeOneMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_one_message);

        TextView changeone = findViewById(R.id.change_nicheng);
        EditText changemsg = findViewById(R.id.update_account);

        TextView newuser = findViewById(R.id.UpdateUser);

        changeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_nicheng = changemsg.getText().toString();
                System.out.println(new_nicheng);

                newuser.setText(new_nicheng);
                String flag = newuser.getText().toString();
                Log.d("改了之后的文本框文本", flag);
            }
        });
    }
}