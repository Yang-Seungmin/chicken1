package be.ysmstudio.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import be.ysmstudio.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSearchGoogle.setOnClickListener(this);
        binding.buttonSearchNaver.setOnClickListener(this);
        binding.buttonSearchDaum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SearchEngine searchEngine = SearchEngine.GOOGLE;
        switch (v.getId()) {
            case R.id.button_search_naver: searchEngine = SearchEngine.NAVER; break;
            case R.id.button_search_daum:  searchEngine = SearchEngine.DAUM; break;
        }
        Intent intent = new Intent(this, SearchFieldActivity.class);
        intent.putExtra("id", searchEngine);
        startActivity(intent);
    }
}