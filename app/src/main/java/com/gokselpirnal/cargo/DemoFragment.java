package com.gokselpirnal.cargo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gokselpirnal.cargo.cargo.Address;
import com.gokselpirnal.cargo.cargo.Cargo;
import com.gokselpirnal.cargo.packages.ButtonChanged;
import com.gokselpirnal.cargo.packages.ButtonClicked;
import com.gokselpirnal.cargo.packages.ImageClicked;

public class DemoFragment extends Fragment {
    private Button btn;
    private ImageView img;

    public static DemoFragment newInstance() {
        DemoFragment fragment = new DemoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_demo, container, false);

        btn = (Button) rootView.findViewById(R.id.btn);
        img = (ImageView) rootView.findViewById(R.id.img);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cargo.deliver(new ButtonClicked());
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cargo.deliver(new ImageClicked());
            }
        });

        return rootView;

    }

    @Address
    public void changeTitle(final ButtonChanged pkg) {
        Log.d("Cargo", "Yaay! New package: " + pkg.getClass().getSimpleName());
        btn.setText(pkg.getTitle());
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
