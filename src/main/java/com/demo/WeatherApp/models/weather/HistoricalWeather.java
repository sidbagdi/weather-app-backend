package com.demo.WeatherApp.models.weather;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="weathername")
public class HistoricalWeather implements Serializable{

    private static final long serialVersionUID = 9220439974378909216L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index")
    private int index;

    @Column(name = "lat")
    private float lat;

    @Column(name = "lon")
    private float lon;

    @Column(name = "temperature")
    private float temperature;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private long time;

    protected HistoricalWeather() {}

    public HistoricalWeather(int index, float lat, float lon, float temperature, Date date, long time) {
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
        this.date = date;
        this.time = time;
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public float getTemperature() {
        return temperature;
    }

    public Date getDate() {
        return date;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "HistoricalWeather{" +
                "index=" + index +
                ", lat=" + lat +
                ", lon=" + lon +
                ", temperature=" + temperature +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}