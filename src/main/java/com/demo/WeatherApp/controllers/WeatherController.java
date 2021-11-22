package com.demo.WeatherApp.controllers;

import com.demo.WeatherApp.models.ApiResponse;
import com.demo.WeatherApp.models.HistoricalDataApiResponse;
import com.demo.WeatherApp.models.WeatherAPIResponse;
import com.demo.WeatherApp.models.weather.HistoricalWeather;
import com.demo.WeatherApp.models.weather.MinMaxTemperature;
import com.demo.WeatherApp.models.weather.Weather;
import com.demo.WeatherApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherController {


    @Autowired
    private WeatherService weatherService;

    @CrossOrigin
    @GetMapping(value="/")
    @ResponseBody
    public List<String> test(){
        List<String> temp = new ArrayList<>();
        temp.add("Hello");
        return temp;
    }
    @CrossOrigin
    @GetMapping(value = "/historicalWeather")
    @ResponseBody
    public List<HistoricalWeather> getHistoricalWeather(@RequestParam(value = "lat") String lat, @RequestParam(value = "lon") String lon){
        return weatherService.getHistoricalData(Float.parseFloat(lat), Float.parseFloat(lon));
    }

    @CrossOrigin
    @GetMapping(value = "/currentWeather")
    @ResponseBody
    public WeatherAPIResponse getCurrentWeather(@RequestParam(value = "city") String cityName){
        return weatherService.getCurrentWeather(cityName);
    }



}
