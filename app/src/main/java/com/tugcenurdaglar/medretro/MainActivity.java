package com.tugcenurdaglar.medretro;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.tugcenurdaglar.medretro.Models.Bilgiler;
import com.tugcenurdaglar.medretro.RestApi.ManagerAll;
import com.tugcenurdaglar.medretro.adapters.adapterbilgi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Bilgiler> list;
    android.widget.ListView ListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();

        istek();

    }
    public void tanimla()
    {

        ListView=(ListView)findViewById(R.id.list_view);
    }
    public void istek(){
        list = new ArrayList<>();

        Call<List<Bilgiler>>bilgiList= ManagerAll.getInstance().getBilgiler();
        bilgiList.enqueue(new Callback<List<Bilgiler>>() {
            @Override
            public void onResponse(Call<List<Bilgiler>> call, Response<List<Bilgiler>> response) {
                if(response.isSuccessful())  {
                    list=response.body();
                    adapterbilgi adp=new adapterbilgi(list,getApplicationContext());
                    ListView.setAdapter(adp);

                }

            }

            @Override
            public void onFailure(Call<List<Bilgiler>> call, Throwable t) {

            }
        });

    }
}