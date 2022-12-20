package com.example.mykpp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>{
    private List<CarModel> carModelsList = new ArrayList<>();
    private Context mContext;
    DatabaseReference reference;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item,parent,false);
        return new ViewHolder(view);
    }

    public CarAdapter(Context mContext, List<CarModel> carModelsList) {
        this.mContext = mContext;
        this.carModelsList = carModelsList;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CarModel carModel = carModelsList.get(position);
        holder.carNum.setText("Номера авто: " + carModel.getCarNum());
        holder.carMark.setText("Марка: " + carModel.getCarMark());
        holder.carModel.setText("Модель: " + carModel.getCarModel());
        holder.carColor.setText("Цвет: " + carModel.getCarColor());
        holder.carTypeTC.setText("Тип ТС: " + carModel.getCarTypeTC());
        holder.carNumPTC.setText("Номер ПТС: " + carModel.getCarNumPTC());
        if (carModel.getCarAccess().equals("false")) {
            holder.access.setText("Проезд: запрещён");
        } else {
            holder.access.setText("Проезд: разрешён");
        }
    }

    @Override
    public int getItemCount() {
        return carModelsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView carNum, carMark, carModel, carColor, carTypeTC, carNumPTC, access;
        CardView carCard;
        public ViewHolder(View itemView) {
            super(itemView);
            carNum = itemView.findViewById(R.id.textNum);
            carMark = itemView.findViewById(R.id.textMark);
            carModel = itemView.findViewById(R.id.textModel);
            carColor = itemView.findViewById(R.id.textColor);
            carTypeTC = itemView.findViewById(R.id.textTC);
            carNumPTC = itemView.findViewById(R.id.textPTC);
            carCard = itemView.findViewById(R.id.carItem);
            access = itemView.findViewById(R.id.textAccess);
        }
    }

}
