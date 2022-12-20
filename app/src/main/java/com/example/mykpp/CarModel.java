package com.example.mykpp;

public class CarModel {

    private String carNum, carMark, carModel, carColor, carTypeTC, carNumPTC, carDriver, carAccess;

    private CarModel() {

    }

    public CarModel(String carNum, String carMark, String carModel, String carColor, String carTypeTC,
                    String carNumPTC, String carDriver, String carAccess) {
        this.carNum = carNum;
        this.carMark = carMark;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carTypeTC = carTypeTC;
        this.carNumPTC = carNumPTC;
        this.carDriver = carDriver;
        this.carAccess = carAccess;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarTypeTC() {
        return carTypeTC;
    }

    public void setCarTypeTC(String carTypeTC) {
        this.carTypeTC = carTypeTC;
    }

    public String getCarNumPTC() {
        return carNumPTC;
    }

    public void setCarNumPTC(String carNumPTC) {
        this.carNumPTC = carNumPTC;
    }

    public String getCarDriver() {
        return carDriver;
    }

    public void setCarDriver(String carDriver) {
        this.carDriver = carDriver;
    }

    public String getCarAccess() {
        return carAccess;
    }

    public void setCarAccess(String carAccess) {
        this.carAccess = carAccess;
    }
}
