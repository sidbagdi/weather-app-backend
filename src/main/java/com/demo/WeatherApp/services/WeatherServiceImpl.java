package com.demo.WeatherApp.services;

import com.demo.WeatherApp.models.HistoricalDataApiResponse;
import com.demo.WeatherApp.models.WeatherAPIResponse;
import com.demo.WeatherApp.models.historical.Current;
import com.demo.WeatherApp.models.weather.HistoricalWeather;
import com.demo.WeatherApp.models.weather.MinMaxTemperature;
import com.demo.WeatherApp.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("weatherService")
@Transactional
public class WeatherServiceImpl implements WeatherService {


    @Autowired
    RestTemplate restTemplate;


    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public WeatherAPIResponse getCurrentWeather(String cityName) {

        final String baseUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=f9a7001e07e91dae569d5daf05788add&units=metric";

        try {
            URI uri = new URI(baseUrl);
            ResponseEntity<WeatherAPIResponse> result = restTemplate.getForEntity(uri, WeatherAPIResponse.class);
            System.out.println(result);
            return result.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (RestClientException re) {
            System.out.println(re);
        }
        return null;
    }

    @Override
    public List<HistoricalWeather> getHistoricalData(float lat, float lon) {

        System.out.println(lat + " " + lon);

//        weatherRepository.truncate();
//        weatherRepository.deleteHistoricalWeatherByLatAndLon(lat,lon);
        List<HistoricalWeather> response = weatherRepository.findAllByLatAndLon(lat, lon);
        System.out.println("DB RESPONSE " + response);
        if (response != null && response.size() > 0) {
            return response;
        } else {
            List<HistoricalDataApiResponse> historicalData = getHistoricalDataFromApi(lat, lon);

            List<HistoricalWeather> hourlyData = translateApiResponse(historicalData);

            for(HistoricalWeather h : hourlyData){
                weatherRepository.save(h);
            }

            response = weatherRepository.findAllByLatAndLon(lat, lon);
            System.out.println(response);
            return response;
        }


    }

    private List<HistoricalWeather> translateApiResponse(List<HistoricalDataApiResponse> historicalDataApiResponseList){

        List<HistoricalWeather> response = new ArrayList<>();
        int index = 0;
        for(HistoricalDataApiResponse h : historicalDataApiResponseList){
            for(Current c : h.getHourly()) {

                //Format Date to yyyyy-mm-dd using SimpleDateFormatter TODO
                Date date = new java.util.Date(c.getDt() * 1000);

                HistoricalWeather historicalWeather = new HistoricalWeather(index++, h.getLat(), h.getLon(), c.getTemp(), date, c.getDt());
                response.add(historicalWeather);
            }
        }

        return response;

    }


    private List<HistoricalDataApiResponse> getHistoricalDataFromApi(float lat, float lon) {
        long currentTime = System.currentTimeMillis() / 1000L;
        System.out.println(currentTime);
        List<HistoricalDataApiResponse> historicalWeather = new ArrayList<HistoricalDataApiResponse>();
        for (int day = 1; day <= 5; day++) {
            long offset = day * 24 * 60 * 60;
            String baseUrl = "http://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat + "&lon=" + lon + "&units=metric&dt=" + (currentTime - offset) + "&appid=f9a7001e07e91dae569d5daf05788add";
            try {
                URI uri = new URI(baseUrl);
                ResponseEntity<HistoricalDataApiResponse> result = restTemplate.getForEntity(uri, HistoricalDataApiResponse.class);
                System.out.println(result);
                historicalWeather.add(result.getBody());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (RestClientException re) {
                System.out.println(re);
            }
        }
        return historicalWeather;
    }


}


