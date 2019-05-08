package com.example.sideproject_04.SearchStudy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sideproject_04.R;

import java.util.ArrayList;
import java.util.List;

public class SearchStudy_MainActivity_Cus extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchStudy_MyAdapter searchStudyMyAdapter;
    SearchView searchview;
    List<SearchStudy_FruitsData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchstudy_activity_main_cus);

        list.add(new SearchStudy_FruitsData("Apple", "https://cdn.dribbble.com/users/4859/screenshots/6374407/online_doctor_consultation_4x.png"));
        list.add(new SearchStudy_FruitsData("Banana", "https://cdn.dribbble.com/users/4859/screenshots/6381781/summer_is_comming_4x.png"));
        list.add(new SearchStudy_FruitsData("Grapes", "https://cdn.dribbble.com/users/25514/screenshots/6302983/vshred_dribbble_mobile_design_4x.png"));
        list.add(new SearchStudy_FruitsData("JackFruit", "https://cdn.dribbble.com/users/4859/screenshots/6238939/car-polling_4x.png"));
        list.add(new SearchStudy_FruitsData("Lemon", "https://cdn.dribbble.com/users/25514/screenshots/6155639/firefox_monitor_branding_grid_4x.png"));
        list.add(new SearchStudy_FruitsData("Mango", "https://cdn.dribbble.com/users/4859/screenshots/6087140/news_4x.png"));
        list.add(new SearchStudy_FruitsData("Orange", "https://cdn.dribbble.com/users/929812/screenshots/5901693/steal_this_look_4x.jpg"));
        list.add(new SearchStudy_FruitsData("Papaya", "https://cdn.dribbble.com/users/25514/screenshots/5859087/2018_4x.jpg"));
        list.add(new SearchStudy_FruitsData("pear", "https://cdn.dribbble.com/users/929812/screenshots/5856250/the_dashboard_4x.jpg"));
        list.add(new SearchStudy_FruitsData("Pineapple", "https://cdn.dribbble.com/users/4859/screenshots/5781139/02_4x.png"));

        recyclerView = findViewById(R.id.searchstudy_recycler_view_cus);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchStudyMyAdapter = new SearchStudy_MyAdapter(SearchStudy_MainActivity_Cus.this, list);
        recyclerView.setAdapter(searchStudyMyAdapter);

        searchview = findViewById(R.id.searchstudy_recycler_searchview);
        searchview.setActivated(true);
        searchview.setQueryHint("Type your keyword here!");
        searchview.onActionViewExpanded();
        searchview.setIconified(false);
        searchview.clearFocus();

        /* Code for changing the textcolor and hint color for the search view */
        SearchView.SearchAutoComplete searchAutoComplete =
                (SearchView.SearchAutoComplete)searchview.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.GRAY);
        searchAutoComplete.setTextColor(Color.WHITE);

        /*Code for changing the search icon */
        ImageView searchIcon = (ImageView)searchview.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
//        searchIcon.setImageResource(R.drawable.ic_launcher_background);

        /*Code for changing the voice search icon */
        ImageView voiceIcon = (ImageView)searchview.findViewById(android.support.v7.appcompat.R.id.search_voice_btn);
//        voiceIcon.setImageResource(R.drawable.ic_launcher_background);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (searchStudyMyAdapter != null){
                    searchStudyMyAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchview.isIconified()) {
            searchview.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}