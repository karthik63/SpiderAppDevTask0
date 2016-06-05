package com.example.samharris.spidertask1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences shared;

    private int count = 0;

    TextView countView;
    Button incrButt;
    Button resetButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared = getSharedPreferences("saveCount", Context.MODE_PRIVATE);

        //set count to saved count value or set it to 0 if no shared preferences
        count = shared.getInt("count", 0);

        countView = (TextView)findViewById(R.id.countView);

        incrButt = (Button) findViewById(R.id.incrButt);

        resetButt = (Button) findViewById(R.id.resetButt);

        countView.setText(String.valueOf(count));

        incrButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                count+=1;
                countView.setText(String.valueOf(count));
            }
        });

        resetButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                count = 0;
                countView.setText("0");
                shared.edit().remove("shared_pref_key").apply();
            }
        });

    }

    //Save count onStop
    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences shared = getSharedPreferences("saveCount", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("count",count);

        editor.apply();
    }

    //To display about page
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.aboutMenu) {
            Intent settingsIntent = new Intent(this, AboutPage.class);
            startActivity(settingsIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
