package cn.shper.mvppandemo.apistore;

import cn.shper.okhttppan.OkHttpPan;
import cn.shper.okhttppan.callback.HttpCallback;

/**
 * Author: Shper
 * Description: Main Api
 * Version: V0.1 2016/12/28
 */
public class MainApiStore {

    private static final String WEATHER_URL = "http://www.weather.com.cn/adat/sk/";

    /**
     * 获取天气详情
     */
    public static void getWeather(String cityId, HttpCallback callback) {
        OkHttpPan.get().url(WEATHER_URL + cityId + ".html").build().execute(null, callback);
    }

}
