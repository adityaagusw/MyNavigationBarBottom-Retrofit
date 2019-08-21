package com.example.createnavigationbutton;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.createnavigationbutton.Fragment.ManageFragment;
import com.example.createnavigationbutton.Fragment.SettingFragment;
import com.example.createnavigationbutton.Fragment.UpdateFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id untuk navigation bottom yg ada di layout main
        BottomNavigationView navigationView = findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(this);

        //disini menampilkan fragment Management yang pertama
        displayFragment(new ManageFragment());

    }

    //method ini untuk menjadi button pindah layout fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                fragment = new ManageFragment();
                break;
            case R.id.menu_users:
                fragment = new UpdateFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingFragment();
                break;
        }

        //fragment tidak boleh kosong, maka di isi fragment yang ada di case
        if (fragment != null){
            displayFragment(fragment);
        }

        return false;
    }

    //untuk memanggil fragment dan mengganti fragment (ditimpah)
    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                //disini relative layout akan di replace menggantikan fragment
                .replace(R.id.relativeLayoutMain, fragment)
                .commit();
    }


}
