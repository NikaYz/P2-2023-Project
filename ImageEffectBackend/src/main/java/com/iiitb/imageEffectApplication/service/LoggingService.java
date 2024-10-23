package com.iiitb.imageEffectApplication.service;
import com.iiitb.imageEffectApplication.model.LogModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.io.File;
import java.util.Scanner;

@Service
public class LoggingService {

    public void addLog(String fileName, String effectName, String optionValues) throws  Exception {
        try {
            //to get the path for file to add a new log into the file
            String currentDir = System.getProperty("user.dir") + "/ImageEffectBackend.txt";
            //this will check if file exist , then append the file else create a new file
            BufferedWriter f_writer = new BufferedWriter(new FileWriter(currentDir, true));
            LocalDateTime currentDateTime = LocalDateTime.now();
            //To connvert Date into string format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd,yyyy HH:mm:ss a");
            String formattedDateTime = currentDateTime.format(formatter);
            String text = "'" + formattedDateTime + "' " + fileName + " " + effectName + " " + optionValues + "\n";
            f_writer.write(text);

            System.out.println("File is created successfully with the content.");
            f_writer.close();
        }

        catch (IOException e) {
            System.out.println("ERROR WHILE WRITING");
        }
    }
    public List<LogModel> getAllLogs() {
        //to get all the logs from the text document
        try {
            //to read from a file
            String currentDir = System.getProperty("user.dir") + "/ImageEffectBackend.txt";
            File myObj = new File(currentDir);
            Scanner myReader = new Scanner(myObj);
            ArrayList<LogModel> logs = new ArrayList<>();
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                //to break the line into parts so that it can be easily be broken down into different variables
                String[] parts = data.split("\\s+");
                if (parts.length >= 4) {
                    String date =  parts[0].substring(1) + " " + parts[1] + " " + parts[2] + " " + parts[3].toLowerCase().substring(0,parts[3].indexOf("'"));
                    String name = parts[4];
                    String file = parts[5];
                    String value = parts.length > 6 ? parts[6] : ""; // handle case when no value is present

                    LogModel log = new LogModel(date, name, file, value);
                    logs.add(log);
                }
            }
            myReader.close();

            return logs;
        } catch (IOException e) {
            System.out.println("FILE DOESN'T EXIST");
        }
        return  new ArrayList<LogModel>();
    }




    public List<LogModel> getLogsByEffect(String effectName) {
        //to get all the logs from the text document
        List<LogModel> logs = getAllLogs();
        List<LogModel> filterLogs  = new ArrayList<>();
        for(LogModel l : logs){
            //filtering the logs by effect name but user has to enter the whole effect name in any case
            if(l.getEffectName().toLowerCase().equals(effectName.toLowerCase())){
                filterLogs.add(l);
            }
        }
        return filterLogs;
    }
    public List<LogModel> getLogsBetweenTimestamp(String startTime , String endTime) {
        //to convert strings into dateTimeFormat
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateStartTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime dateEndTime = LocalDateTime.parse(endTime, formatter);
        List<LogModel> logs = getAllLogs();
        List<LogModel> filterLogs  = new ArrayList<>();
        for(LogModel l : logs){
            //used substring to remove AM PM to remove from timestamp as it's 24 hour clock
            String t = l.getTimestamp().substring(0,l.getTimestamp().length()-3);

            DateTimeFormatter formater = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("MMM dd,yyyy HH:mm:ss")
                    .toFormatter();
            LocalDateTime dateBetween = LocalDateTime.parse(t, formater);
            //to check whether the date exist between two dates
            if (dateBetween.isAfter(dateStartTime) && dateBetween.isBefore(dateEndTime)){
                filterLogs.add(l);
            }
        }
        return filterLogs;
    }

    public void clearLogs() {
        //to get the file path in which data of logs is stored
        String currentDir = System.getProperty("user.dir") + "/ImageEffectBackend.txt";
        File file = new File(currentDir);
        //if file exist then delete data from the file, else print error
        if (file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            System.out.println("Failed to delete the file");
        }

    }
}
