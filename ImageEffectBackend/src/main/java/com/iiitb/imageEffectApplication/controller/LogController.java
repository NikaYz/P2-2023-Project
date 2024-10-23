package com.iiitb.imageEffectApplication.controller;

import com.iiitb.imageEffectApplication.model.LogModel;
import com.iiitb.imageEffectApplication.service.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private LoggingService loggingService;

    @GetMapping("")
    public List<LogModel> getAllLogs() {
        return loggingService.getAllLogs();
    }


    @GetMapping("/{effectName}")
    public List<LogModel> getLogsByEffect(@PathVariable String effectName) {
        return loggingService.getLogsByEffect(effectName);
    }
    //To do

    // this has been added by us to apply filter effect by timestamps
    @GetMapping("/timestamp/{startTime},{endTime}")
    public List<LogModel> getLogsBetweenTimestamp(@PathVariable String startTime,@PathVariable String endTime) {
            return loggingService.getLogsBetweenTimestamp(startTime, endTime);
    }
    // your work ends
    @DeleteMapping("")
    public ResponseEntity<String> clearLogs() {
        loggingService.clearLogs();
        return ResponseEntity.ok("Logs cleared successfully");
    }
}
