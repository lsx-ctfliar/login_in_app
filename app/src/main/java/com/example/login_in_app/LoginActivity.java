package com.example.login_in_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {


    private Boolean bPwdSwitch = false;
    private EditText etPwd;
    private EditText etAccount;
    private CheckBox cbRememberPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final ImageView ivPwdSwitch= findViewById(R.id.iv_pwd_switch);
        etPwd = findViewById(R.id.et_pwd);
        Button btLogin = findViewById(R.id.bt_login);
        Button btRegister=findViewById(R.id.bt_register);
        etAccount=findViewById(R.id.et_account);
        cbRememberPwd = findViewById(R.id.cb_remember_pwd);



        String spFileName = getResources().getString(R.string.shared_preferences_file_name);
        String accountKey = getResources().getString(R.string.login_account_name);
        String passwordKey = getResources().getString(R.string.login_password);
        String rememberPasswordkey = getResources().getString(R.string.login_remember_password);


        //记住密码
        SharedPreferences spFile = getSharedPreferences(
                spFileName,
                MODE_PRIVATE);
        String account = spFile.getString(accountKey, null);
        String password = spFile.getString(passwordKey, null);
        Boolean rememberPassword = spFile.getBoolean(
                rememberPasswordkey,
                false);

        if (account != null && !TextUtils.isEmpty(account)) {
            etAccount.setText(account);
        }

        if (password != null && !TextUtils.isEmpty(password)) {
            etPwd.setText(password);
        }

        cbRememberPwd.setChecked(rememberPassword);

        //设置密码可不可见
        ivPwdSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bPwdSwitch = !bPwdSwitch;
                if(bPwdSwitch){
//                    点击的时候把，，不可见的图标换成可见的眼睛图标资源
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_24);
//                    设置文本输入不可见
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else{
                    ivPwdSwitch.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD |
                            InputType.TYPE_CLASS_TEXT);
                    etPwd.setTypeface(Typeface.DEFAULT);
                }




            }
        });

        //转到主页面
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    String spFileName = getResources()
                            .getString(R.string.shared_preferences_file_name);
                    String accountKey = getResources()
                            .getString(R.string.login_account_name);
                    String passwordKey =  getResources()
                            .getString(R.string.login_password);
                    String rememberPasswordKey = getResources()
                            .getString(R.string.login_remember_password);

                    SharedPreferences spFile = getSharedPreferences(
                            spFileName,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = spFile.edit();

                    if (cbRememberPwd.isChecked()) {
                        String password = etPwd.getText().toString();
                        String account = etAccount.getText().toString();

                        editor.putString(accountKey, account);
                        editor.putString(passwordKey, password);
                        editor.putBoolean(rememberPasswordKey, true);
                        editor.apply();
                    } else {
                        editor.remove(accountKey);
                        editor.remove(passwordKey);
                        editor.remove(rememberPasswordKey);
                        editor.apply();
                    }

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                    intent.putExtra(MESSAGE_STRING,message);
                    startActivity(intent);

                }
            }
        });

        //转到注册界面
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                    intent.putExtra(MESSAGE_STRING,message);
                startActivity(intent);
            }
        });
    }


}