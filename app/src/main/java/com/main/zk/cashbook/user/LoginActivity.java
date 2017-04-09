package com.main.zk.cashbook.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.main.zk.cashbook.R;
import com.main.zk.cashbook.util.SharedPreferencesUtil;
import com.main.zk.cashbook.util.SharedPreferencesUtil.Key;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private SharedPreferencesUtil spUtil;
    private EditText login_user, login_pwd, login_pwd_again;
    private Button login_true;
    private boolean isRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        spUtil = SharedPreferencesUtil.getInstance(this);
        login_user = (EditText) findViewById(R.id.login_user);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_pwd_again = (EditText) findViewById(R.id.login_pwd_again);
        login_true = (Button) findViewById(R.id.login_true);
        isRegistered = spUtil.getBoolean(Key.IsRegistered.name(), false);
        if (isRegistered) {
            login_user.setEnabled(false);
            login_user.setText(spUtil.getString(Key.UserName.name(),""));
            login_pwd_again.setVisibility(View.GONE);
        }
        login_true.setOnClickListener(listener);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getResources().getString(R.string.wait));

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_true:
                    progressDialog.show();
                    String userName = login_user.getText().toString().trim();
                    String pwd = login_pwd.getText().toString().trim();
                    if (pwd.equals("") || userName.equals("")){
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.account_pwd_empty), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    if (isRegistered){
                        progressDialog.cancel();
                        if (spUtil.getString(Key.Password.name(),"").equals(pwd)){
                            Intent it = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.account_pwd_error), Toast.LENGTH_SHORT).show();
                            break;
                        }
                    } else {
                        String pwdAgain = login_pwd_again.getText().toString().trim();
                        progressDialog.cancel();
                        if (pwdAgain.equals("")){
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.account_pwd_empty), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (!pwd.equals(pwdAgain)){ //两次密码不一样
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.two_pwd_match), Toast.LENGTH_SHORT).show();
                            break;
                        }
                        isRegistered = true;
                        ArrayList<SharedPreferencesUtil.Record> records = new ArrayList<>();
                        records.add(new SharedPreferencesUtil.Record(Key.IsRegistered.name(), isRegistered));
                        records.add(new SharedPreferencesUtil.Record(Key.UserName.name(), userName));
                        records.add(new SharedPreferencesUtil.Record(Key.Password.name(), pwd));

                        spUtil.applyValue(records);

                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
