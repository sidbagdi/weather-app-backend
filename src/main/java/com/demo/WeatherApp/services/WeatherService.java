package com.demo.WeatherApp.services;

import com.demo.WeatherApp.models.HistoricalDataApiResponse;
import com.demo.WeatherApp.models.WeatherAPIResponse;
import com.demo.WeatherApp.models.weather.HistoricalWeather;
import com.demo.WeatherApp.models.weather.MinMaxTemperature;

import java.util.List;

public interface WeatherService {

    WeatherAPIResponse getCurrentWeather(String cityName);

    List<HistoricalWeather> getHistoricalData(float lat, float lon);


}
