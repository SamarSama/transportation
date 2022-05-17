package com.example.transportation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class afterlogin extends AppCompatActivity {
String userphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("الرئيسية");
        Fragment MasahaFragment=new PostListFragment();
        userphone = getIntent().getStringExtra("phone");

        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container,MasahaFragment,MasahaFragment.getTag()).commit();
        // userNameTextView.setText(getIntent().getStringExtra("src"));
        // setSupportActionBar(toolbar);
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nvView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_menu_24, this.getTheme());

        View headerView = navigationView.getHeaderView(0);

        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Fragment fragment = null;
                switch (id) {
                    case R.id.home:
                        fragment = new PostListFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("phone", getIntent().getStringExtra("phone"));

                        fragment.setArguments(bundle);
                        DrawerLayout drawer = findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                        break;
                    case R.id.search:


                        break;
                    case R.id.sendpost:
                        fragment =new addpostFragment();
                         bundle = new Bundle();
                        bundle.putString("phone", getIntent().getStringExtra("phone"));

                        fragment.setArguments(bundle);
                         drawer = findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                        break;

                    case R.id.logout:

                        Paper.book().destroy();

                        Intent   intent = new Intent(afterlogin.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();

                        break;




                }

                return true;
            }
        });


    }



}






