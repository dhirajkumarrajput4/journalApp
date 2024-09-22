package in.dhirajrajput.response_request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class WeatherResponse {

    @JsonProperty("request")
    private Request request;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("current")
    private Current current;

    @Data
    public static class Request {
        @JsonProperty("type")
        private String type;

        @JsonProperty("query")
        private String query;

        @JsonProperty("language")
        private String language;

        @JsonProperty("unit")
        private String unit;
    }

    @Data
    public static class Location {
        @JsonProperty("name")
        private String name;

        @JsonProperty("country")
        private String country;

        @JsonProperty("region")
        private String region;

        @JsonProperty("lat")
        private String lat;

        @JsonProperty("lon")
        private String lon;

        @JsonProperty("timezone_id")
        private String timezoneId;

        @JsonProperty("localtime")
        private String localtime;

        @JsonProperty("localtime_epoch")
        private long localtimeEpoch;

        @JsonProperty("utc_offset")
        private String utcOffset;
    }

    @Data
    public static class Current {
        @JsonProperty("observation_time")
        private String observationTime;

        @JsonProperty("temperature")
        private int temperature;

        @JsonProperty("weather_code")
        private int weatherCode;

        @JsonProperty("weather_icons")
        private List<String> weatherIcons;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("wind_speed")
        private int windSpeed;

        @JsonProperty("wind_degree")
        private int windDegree;

        @JsonProperty("wind_dir")
        private String windDir;

        @JsonProperty("pressure")
        private int pressure;

        @JsonProperty("precip")
        private double precip;

        @JsonProperty("humidity")
        private int humidity;

        @JsonProperty("cloudcover")
        private int cloudCover;

        @JsonProperty("feelslike")
        private int feelsLike;

        @JsonProperty("uv_index")
        private int uvIndex;

        @JsonProperty("visibility")
        private int visibility;

        @JsonProperty("is_day")
        private String isDay;
    }
}


