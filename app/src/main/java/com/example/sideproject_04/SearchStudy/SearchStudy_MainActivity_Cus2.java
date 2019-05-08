package com.example.sideproject_04.SearchStudy;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sideproject_04.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchStudy_MainActivity_Cus2 extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchStudy_MyAdapter searchStudyMyAdapter;
    List<SearchStudy_FruitsData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchstudy_activity_main_cus);

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

        recyclerView = findViewById(R.id.searchstudy_recycler_view_cus);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchStudyMyAdapter = new SearchStudy_MyAdapter(SearchStudy_MainActivity_Cus2.this,list);
        recyclerView.setAdapter(searchStudyMyAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setActivated(true);
        searchView.setQueryHint("Type your keyword here!");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();

        MenuItem searchItem = menu.findItem(R.id.searchstudy_recycler_searchview);
        searchItem.expandActionView();
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                //DO SOMETHING WHEN THE SEARCHVIEW IS CLOSING
                back();
                return false;
            }
        });

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat
                .getActionView(searchItem);
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
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
        }

        for (TextView textView : findChildrenByClass(searchView, TextView.class)) {
            textView.setTextColor(Color.WHITE);
        }
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void back() {
        finish();
    }

    public static <V extends View> Collection<V> findChildrenByClass(
            ViewGroup viewGroup, Class<V> clazz) {
        return gatherChildrenByClass(viewGroup, clazz, new ArrayList<V>());
    }

    private static <V extends View> Collection<V> gatherChildrenByClass(
            ViewGroup viewGroup, Class<V> clazz, Collection<V> childrenFound) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (clazz.isAssignableFrom(child.getClass())) {
                childrenFound.add((V) child);
            }
            if (child instanceof ViewGroup) {
                gatherChildrenByClass((ViewGroup) child, clazz, childrenFound);
            }
        }

        return childrenFound;
    }

}