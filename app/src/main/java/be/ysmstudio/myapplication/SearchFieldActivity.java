package be.ysmstudio.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import be.ysmstudio.myapplication.databinding.ActivitySearchFieldBinding;

public class SearchFieldActivity extends AppCompatActivity {

    private ActivitySearchFieldBinding binding;

    private SearchEngine currentSearchEngine = SearchEngine.GOOGLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchFieldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentSearchEngine = (SearchEngine) getIntent().getExtras().get("id");
        binding.textViewSearchEngine.setText(currentSearchEngine.getName() + "에서 검색합니다.");
        getSupportActionBar().setTitle(currentSearchEngine.getName() + "Search");

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(SearchFieldActivity.this)
                        .setTitle("알림")
                        .setMessage("\"" + binding.editText.getText().toString() + "\"\n해당 단어로 검색하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentSearchEngine.getUrlPrefix() + URLDecoder.decode(binding.editText.getText().toString(), "UTF-8")));
                                    startActivity(intent);
                                } catch (UnsupportedEncodingException e) {
                                    Log.e("Error", "Decode error. Invalid input");
                                }
                            }
                        }).setNegativeButton("아니오", null)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_field, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SearchEngine searchEngine = SearchEngine.GOOGLE;
        switch (item.getItemId()) {
            case R.id.item_naver:
                searchEngine = SearchEngine.NAVER;
                break;
            case R.id.item_daum:
                searchEngine = SearchEngine.DAUM;
                break;
        }
        Intent intent = new Intent(this, SearchFieldActivity.class);
        intent.putExtra("id", searchEngine);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}