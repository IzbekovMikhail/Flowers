package com.example.flowers;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Flower> mFlowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlowers = new ArrayList<>();

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        FlowerAdapter adapter = new FlowerAdapter(mFlowers);
        mRecyclerView.setAdapter(adapter);

        FlowersAPI flowersAPI = FlowersAPI.retrofit.create(FlowersAPI.class);

        final Call<List<Flower>> call = flowersAPI.getData();

        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if (response.isSuccessful()) {
                    mFlowers.addAll(response.body());
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    ResponseBody errorBody = response.errorBody();
                    try {
                        Toast.makeText(MainActivity.this, errorBody.string(),
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Что-то пошло не так",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
