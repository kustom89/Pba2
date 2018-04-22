package com.example.andre.pba2;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.andre.pba2.models.PendingFood;

public class MainActivity extends AppCompatActivity  {

    private PendingsFoodFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainActivityFragment = (PendingsFoodFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog= new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_pending);

                ImageButton button= dialog.findViewById(R.id.savePendingFoodBtn);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText input=dialog.findViewById(R.id.pendingEt);
                        String name=input.getText().toString();
                        EditText input2=dialog.findViewById(R.id.pending2Et);
                        String descripcion=input2.getText().toString();

                        if(name.trim().length()>0&& descripcion.trim().length()>0){
                            PendingFood pendingFood=new PendingFood();
                            pendingFood.setName(name);
                            pendingFood.setDescripcion(descripcion);
                            pendingFood.setDone(false);
                            pendingFood.save();

                            Log.d("SAVE", String.valueOf(pendingFood));

                            mainActivityFragment.updateList(pendingFood);
                        }
                        dialog.dismiss();

                    }
                });
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
