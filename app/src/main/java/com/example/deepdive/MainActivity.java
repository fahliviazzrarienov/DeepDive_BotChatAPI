package com.example.deepdive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL =
                    "http://api.brainshop.ai/get?bid=162670&key=bypfmOpjSpJ0Te2C&uid=[uid]&msg=";

    private TextView mYouText;
    private TextView mBotText;
    private EditText mInputText;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYouText = findViewById(R.id.you_text);
        mBotText = findViewById(R.id.bot_text);

        mInputText = findViewById(R.id.input_text);

        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String requestText = mInputText.getText().toString();

                mYouText.setText("You : "+ requestText);

                mRequestQueue  = Volley.newRequestQueue(MainActivity.this);
                String url = API_URL ;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mBotText.setText("Robot : " + response);
                        Log.i("console.log", response);
                    }
                } , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mBotText.setText("Robot : That didn't work!");
                    }
                });
                mRequestQueue.add(stringRequest);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}