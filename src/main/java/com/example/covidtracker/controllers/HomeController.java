package com.example.covidtracker.controllers;

import com.example.covidtracker.models.LocationStats;
import com.example.covidtracker.services.CoronaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    final
    CoronaDataService coronaDataService;
@Autowired
    public HomeController(CoronaDataService coronaDataService) {
        this.coronaDataService = coronaDataService;
    }

    @GetMapping("/")
    public String home(Model model){
    List<LocationStats> allStats=coronaDataService.getLocationStatsList();
    int totalReportedCases=allStats.stream().mapToInt(stat->stat.getLatest_total()).sum();
        model.addAttribute("locationStats",coronaDataService.getLocationStatsList());
        model.addAttribute("totalReportedCases",totalReportedCases);
        return  "home";
    }
}
