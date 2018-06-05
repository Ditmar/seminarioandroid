package com.example.ditmar.cameraapp;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ditmar.cameraapp.utils.LoaderImg;
import com.example.ditmar.cameraapp.utils.OnLoadCompleImg;
import com.example.ditmar.cameraapp.utils.ParamsConnection;
import com.example.ditmar.cameraapp.utils.UserData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Profile extends AppCompatActivity implements OnLoadCompleImg {
    private ImageView IMG;
    private TextView TXT;
    private Profile root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (UserData.IMG != null) {
            IMG.setImageBitmap(UserData.IMG);
        }

        LoadComponents();
        LoadExternalData();

    }

    private void LoadExternalData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(ParamsConnection.REST_USER_POST + "/" + UserData.ID, new  JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("avatar");
                    String photo = data.getString(0);
                    UserData.PHOTOURL = photo;
                    if (UserData.IMG == null) {
                        LoaderImg img = new LoaderImg();
                        img.setOnloadCompleteImg(IMG,1,root);
                        img.execute(photo);
                    }

                    String name = response.getString("name");
                    UserData.NAME = name;
                    String altura = response.getString("altura");
                    String peso = response.getString("peso");
                    String edad = response.getString("edad");
                    String sexo = response.getString("sexo");
                    TXT.setText(" " + name + " " + peso + " " + edad + " " + sexo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            });
    }

    private void LoadComponents() {
        IMG = (ImageView)this.findViewById(R.id.imageView);
        TXT = (TextView)this.findViewById(R.id.textView7);
    }

    @Override
    public void OnloadCompleteImgResult(ImageView img, int position, Bitmap imgsourceimg) {
        UserData.IMG = imgsourceimg;
        img.setImageBitmap(imgsourceimg);
    }
}
