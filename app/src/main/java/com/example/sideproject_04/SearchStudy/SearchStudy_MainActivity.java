package com.example.sideproject_04.SearchStudy;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sideproject_04.R;

import java.util.ArrayList;
import java.util.List;

public class SearchStudy_MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchStudy_MyAdapter searchStudyMyAdapter;
    List<SearchStudy_FruitsData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchstudy_activity_main);

        list.add(new SearchStudy_FruitsData("Apple","https://cdn.dribbble.com/users/4859/screenshots/6374407/online_doctor_consultation_4x.png"));
        list.add(new SearchStudy_FruitsData("Banana","https://cdn.dribbble.com/users/4859/screenshots/6381781/summer_is_comming_4x.png"));
        list.add(new SearchStudy_FruitsData("Grapes","https://cdn.dribbble.com/users/25514/screenshots/6302983/vshred_dribbble_mobile_design_4x.png"));
        list.add(new SearchStudy_FruitsData("JackFruit","https://cdn.dribbble.com/users/4859/screenshots/6238939/car-polling_4x.png"));
        list.add(new SearchStudy_FruitsData("Lemon","https://cdn.dribbble.com/users/25514/screenshots/6155639/firefox_monitor_branding_grid_4x.png"));
        list.add(new SearchStudy_FruitsData("Mango","https://cdn.dribbble.com/users/4859/screenshots/6087140/news_4x.png"));
        list.add(new SearchStudy_FruitsData("Orange","https://cdn.dribbble.com/users/929812/screenshots/5901693/steal_this_look_4x.jpg"));
        list.add(new SearchStudy_FruitsData("Papaya","https://cdn.dribbble.com/users/25514/screenshots/5859087/2018_4x.jpg"));
        list.add(new SearchStudy_FruitsData("pear","https://cdn.dribbble.com/users/929812/screenshots/5856250/the_dashboard_4x.jpg"));
        list.add(new SearchStudy_FruitsData("Pineapple","https://cdn.dribbble.com/users/4859/screenshots/5781139/02_4x.png"));

        recyclerView = findViewById(R.id.searchstudy_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchStudyMyAdapter = new SearchStudy_MyAdapter(SearchStudy_MainActivity.this,list);
        recyclerView.setAdapter(searchStudyMyAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (searchStudyMyAdapter != null){
                    searchStudyMyAdapter.getFilter().filter(newText);
                }

                return true;
            }
        });
        return true;
    }

}