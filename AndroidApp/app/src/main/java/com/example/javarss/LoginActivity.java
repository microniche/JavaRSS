package com.example.javarss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

/**
 * Created by axelc on 1/27/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "JavaRSS";

    static final private String loginUrl = "http://localhost:8080/JavaRSS/login";
    static final private String logoutUrl = "http://localhost:8080/JavaRSS/logout";
    static final private String registerUrl = "http://localhost:8080/JavaRSS/register";

    String  usr;
    String  pwd;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        mContext = this;
        Button b = (Button) findViewById(R.id.login_button_signin);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
//                try {
//                    signIn(v);
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void signIn(View view) throws ExecutionException, InterruptedException {
        if (sendLoginRequest()!= null) {
            if (HttpHandler.previousSetCookie != null) {
                HttpHandler.cookiesToProvide = HttpHandler.previousSetCookie;
            }
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private String sendLoginRequest() throws ExecutionException, InterruptedException {
        EditText usernameField = (EditText) findViewById(R.id.login_email_field);
        EditText passwordField = (EditText) findViewById(R.id.login_password_field);
        usr = usernameField.getText().toString();
        pwd = passwordField.getText().toString();
        return new sendPostTask().execute(1).get();
    }

    static public String sendLogoutRequest() {
        return (HttpHandler.sendGet(logoutUrl));
    }

    private class sendPostTask extends AsyncTask<Integer, Void, String> {

        @Override
        protected String  doInBackground(Integer... params) {
            String result = null;
            int size = params.length;
            for (int i = 0; i < size; i++) {
                result = HttpHandler.sendPost(loginUrl, "mail", usr, "password", pwd);
            }
            return result;
        }
    }
}

