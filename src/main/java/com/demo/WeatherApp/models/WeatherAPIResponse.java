package com.demo.WeatherApp.models;

import com.demo.WeatherApp.models.weather.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherAPIResponse {

    private Coordinates coordinates;
    private List<Weather> weather;
    private String base;
    private MainField main;
    private int visibility;
    private Wind wind;
    private Clouds cloud;
    private String dt;
    private SysData sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public SysData getSys() {
        return sys;
    }

    public void setSys(SysData sys) {
        this.sys = sys;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonProperty(value = "coord")
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainField getMain() {
        return main;
    }

    public void setMain(MainField main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getCloud() {
        return cloud;
    }

    public void setCloud(Clouds cloud) {
        this.cloud = cloud;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
