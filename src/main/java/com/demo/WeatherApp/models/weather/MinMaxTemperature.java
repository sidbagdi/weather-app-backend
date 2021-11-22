package com.demo.WeatherApp.models.weather;

public class MinMaxTemperature {

    private float temp_min;
    private float temp_max;

    public MinMaxTemperature(float temp_min, float temp_max) {
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }
}
