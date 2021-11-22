package com.demo.WeatherApp.models;
import com.demo.WeatherApp.models.historical.Current;
import com.demo.WeatherApp.models.weather.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalDataApiResponse {

    private float lat;
    private float lon;
    private String timezone;
    private int timezone_offset;
    private Current current;
    private List<Current> hourly;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public List<Current> getHourly() {
        return hourly;
    }

    public void setHourly(List<Current> hourly) {
        this.hourly = hourly;
    }
}
