package bap0031.comp3710.csse.eng.auburn.edu.view;


import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import bap0031.comp3710.csse.eng.auburn.edu.R;

public class MainActivity extends AppCompatActivity implements GameFragment.OnFragmentInteractionListener, AboutFragment.OnFragmentInteractionListener, DemoFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment gridFragment = GameFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder, gridFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SharedPreferences sp = getSharedPreferences("setttings", Context.MODE_PRIVATE);
        menu.findItem(R.id.sound_on_off).setChecked(sp.getBoolean("sound", true));
        menu.findItem(R.id.demo_on_off).setChecked(sp.getBoolean("demo", false));
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        SharedPreferences sp = getSharedPreferences("setttings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        switch (id) {
            case R.id.about_page:
                replaceFragment(AboutFragment.newInstance());
                return true;
            case R.id.sound_on_off:
                item.setChecked(!item.isChecked());
                editor.putBoolean("sound", item.isChecked());
                editor.apply();
                return true;
            case R.id.demo_on_off:
                replaceFragment(DemoFragment.newInstance());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (!fragmentManager.popBackStackImmediate(fragment.getClass().getName(), 0)) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_holder, fragment, fragment.toString());
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.addToBackStack(fragment.toString());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
