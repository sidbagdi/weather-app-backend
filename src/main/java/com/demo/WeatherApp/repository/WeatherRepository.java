package com.demo.WeatherApp.repository;

import com.demo.WeatherApp.models.weather.HistoricalWeather;
import com.demo.WeatherApp.models.weather.MinMaxTemperature;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("weatherRepository")
public interface WeatherRepository extends CrudRepository<HistoricalWeather, Long> {

    @Query(value = "SELECT w from HistoricalWeather w where w.lat = :lat and w.lon = :lon")
    List<HistoricalWeather> findByLatAndLon(@Param("lat") float lat, @Param("lon") float lon);

    List<HistoricalWeather> findAllByLatAndLon(float lat, float lon);

    @Query(value = "SELECT new com.demo.WeatherApp.models.weather.MinMaxTemperature(MIN(w.temperature), MAX(w.temperature)) FROM HistoricalWeather w where w.lat = :lat and w.lon = :lon GROUP BY w.date")
    List<MinMaxTemperature> minMaxTemperature(@Param("lat") float lat, @Param("lon") float lon);

    @Modifying
    void deleteHistoricalWeatherByLatAndLon(float lat, float lon);

    @Modifying
    @Query(value = "TRUNCATE table HistoricalWeather", nativeQuery = true)
    void truncate();

    @Modifying
    @Query(value = "INSERT INTO weathertable values(:index, :lat, :lon, :temperature, :date, :time)", nativeQuery = true)
    public void insert(@Param("index") float index, @Param("lat") float lat, @Param("lon") float lon, @Param("temperature") float temperature, @Param("date") Date date, @Param("time") long time);
}
