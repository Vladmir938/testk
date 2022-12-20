package com.example.mykpp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccessCar extends Fragment {

    FirebaseUser fuser;
    private RecyclerView mRvData;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private CarAdapter carAdapter;
    private List<CarModel> carModelList = new ArrayList<>();
    private LinearLayout checkCar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_access_car, container, false);
        mRvData = view.findViewById(R.id.carList);
        checkCar = view.findViewById(R.id.checkCar);
        initView();
        return view;
    }

    private void initView() {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference().child("Cars");
        mRvData.setLayoutManager(new LinearLayoutManager(getContext()));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carModelList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    CarModel carModel = dataSnapshot1.getValue(CarModel.class);
                    fuser = FirebaseAuth.getInstance().getCurrentUser();
                    String userNow = fuser.getUid();

                    if (carModel.getCarDriver().equals(userNow)) {
                        carModelList.add(carModel);
                        if (carModelList.size() != 0) {
                            mRvData.setVisibility(View.VISIBLE);
                            checkCar.setVisibility(View.GONE);
                        } else {
                            checkCar.setVisibility(View.VISIBLE);
                            mRvData.setVisibility(View.GONE);
                        }
                    }  else {
                        checkCar.setVisibility(View.VISIBLE);
                        mRvData.setVisibility(View.GONE);
                    }
                }
                carAdapter = new CarAdapter(getContext(), carModelList);
                mRvData.setAdapter(carAdapter);
                carAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}