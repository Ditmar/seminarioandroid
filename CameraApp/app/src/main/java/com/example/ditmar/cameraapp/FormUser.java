package com.example.ditmar.cameraapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ditmar.cameraapp.utils.ParamsConnection;
import com.example.ditmar.cameraapp.utils.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class FormUser extends AppCompatActivity implements View.OnClickListener {

    private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_user);
        LoadComponents();
    }
    private void LoadComponents() {
        Button btn = (Button)this.findViewById(R.id.sendbtn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //()this.findViewById(R.id.sendbtn);
        TextView name = (TextView)this.findViewById(R.id.name);
        TextView edad = (TextView)this.findViewById(R.id.edad);
        RadioGroup radio = (RadioGroup)this.findViewById(R.id.radioGroup);

        TextView altura = (TextView)this.findViewById(R.id.altura);
        TextView peso = (TextView)this.findViewById(R.id.peso);
        TextView password = (TextView)this.findViewById(R.id.password);
        String sexo = "";
        if (radio.getCheckedRadioButtonId() == R.id.radioButton) {
            sexo = "maculino";
        } else {
            sexo = "femenino";
        }


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("name", name.getText());
        params.put("altura", altura.getText());
        params.put("peso", peso.getText());
        params.put("edad", edad.getText());
        params.put("sexo", sexo);
        params.put("email", "salud_android@gmail.com");
        params.put("password", password.getText());


        client.post(ParamsConnection.REST_USER_POST, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String msn = response.getString("msn");
                    String id = response.getString("id");
                    UserData.ID = id;
                    if (msn != null) {
                        Intent camera = new Intent(root, MainActivity.class);
                        root.startActivity(camera);
                    } else {
                        Toast.makeText(root, "ERROR AL enviar los datos", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //AsyncHttpClient.log.w(LOG_TAG, "onSuccess(int, Header[], JSONObject) was not overriden, but callback was received");
            }
        });

    }
}
