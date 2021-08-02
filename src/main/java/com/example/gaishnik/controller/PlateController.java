package com.example.gaishnik.controller;

import com.example.gaishnik.model.Plate;
import com.example.gaishnik.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class PlateController {

    private PlateService plateService;

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    @GetMapping("/random")
    public String random() {
        return plateService.getRandomPlate().toString();
    }

    @GetMapping("/next")
    public String next() {
        return plateService.getNextPlate().toString();
    }
}
