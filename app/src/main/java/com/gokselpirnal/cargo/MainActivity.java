package com.gokselpirnal.cargo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gokselpirnal.cargo.cargo.Address;
import com.gokselpirnal.cargo.cargo.Cargo;
import com.gokselpirnal.cargo.packages.ButtonChanged;
import com.gokselpirnal.cargo.packages.ButtonClicked;
import com.gokselpirnal.cargo.packages.ImageClicked;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.activity_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cargo.deliver(new ButtonChanged("I am from MainActivity"));
            }
        });

        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.add(R.id.fragment_container,DemoFragment.newInstance());
        fts.commit();
    }

    @Address
    public void clickedToButton(ButtonClicked pkg){
        Log.d("Cargo","Yaay! New package: " + pkg.getClass().getSimpleName());
        btn.setText("I am from Fragment");
    }

    @Address
    public void clickedToImage(ImageClicked pkg){
        Log.d("Cargo","Yaay! New package: " + pkg.getClass().getSimpleName());
    }

    @Override
    public void onStart() {
        super.onStart();
        Cargo.openTheBranch(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Cargo.closeTheBranch(this);
    }
}
