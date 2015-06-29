package com.example.trzyapplik.trzyapplik;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    int myNumber, userNumber, proba=0;
    TextView textView;
    TextView textView2;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        myNumber = rand.nextInt((100-0)+1)+0;

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        sharedPreferences = getSharedPreferences("com.example.trzyapplik.trzyapplik", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textView2.setText("Rekord: " + sharedPreferences.getInt("rekord", 100));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void newGame(View view) {
        Random rand = new Random();
        myNumber = rand.nextInt((100-0)+1)+0;
        proba=0;
        textView.setText("Proba: " + proba);
    }

    public void wezLosuj(View view) {
        proba++;
        EditText editText = (EditText)findViewById(R.id.editText);
        userNumber = Integer.parseInt(editText.getText().toString());
        String message="";
        if(myNumber > userNumber){
            message = "za malo";
        }
        else if(myNumber < userNumber){
            message = "za duzo";
        }
        else if(myNumber == userNumber){
            message = "elegancko miszczu";
            if(proba < sharedPreferences.getInt("rekord", 100)){
                editor.putInt("rekord", proba);
                editor.commit();
                textView2.setText("Rekord: " + sharedPreferences.getInt("rekord", 100));
            }
        }

        Context context = getApplicationContext();
        int trwanie = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, trwanie);
        toast.show();
        textView.setText("Proba: " + proba);
    }
}
