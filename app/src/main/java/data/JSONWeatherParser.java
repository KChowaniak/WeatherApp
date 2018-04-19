package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by Ina on 09.04.2018.
 */

public class JSONWeatherParser {
    public static Weather getWeather(String data) {
        Weather weather = new Weather();

        //tworzenie JsonObject z data

        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            JSONObject coorObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coorObj));
            place.setLon(Utils.getFloat("lon", coorObj));

            //sys obj
            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastupdate(Utils.getInt("dt", jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.place = place;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            JSONObject mainObject = Utils.getObject("main", jsonObject);
            weather.currentCondition.setHumidity(Utils.getInt("humidity", mainObject));
            weather.currentCondition.setPressure(Utils.getInt("pressure", mainObject));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min", mainObject));
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max", mainObject));
            weather.currentCondition.setTemperature(Utils.getDouble("temp", mainObject));


            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            weather.wind.setDeg(Utils.getFloat("deg", windObj));

            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
