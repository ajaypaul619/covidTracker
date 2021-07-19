package com.example.covidtracker.services;

import com.example.covidtracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CoronaDataService {
    private static  final String URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
  private List<LocationStats> locationStatsList=new ArrayList<>();

    public List<LocationStats> getLocationStatsList() {
        return locationStatsList;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void getCoronaData() throws IOException, InterruptedException {
             List<LocationStats> newStats=new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            StringReader csvReader=new StringReader(httpResponse.body());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);

            for (CSVRecord record : records) {
                LocationStats locationStats=new LocationStats();
                locationStats.setProvince_state(record.get("Province_State"));
                int latestDayCases= Integer.parseInt(record.get(record.size()-1));
                int previousDayCases= Integer.parseInt(record.get(record.size()-2));
                locationStats.setDifferenceInCases(latestDayCases-previousDayCases);
                locationStats.setLatest_total(Integer.parseInt(record.get(record.size()-1)));
                newStats.add(locationStats);

            }
            this.locationStatsList=newStats;




    }

}
