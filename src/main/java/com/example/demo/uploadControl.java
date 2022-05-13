package com.example.demo;


import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

public class uploadControl {

    @Autowired
     PaymentRepository service;

    @PostMapping(path = "/upload", consumes = MULTIPART_FORM_DATA_VALUE)
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {                               // Receive a data from the client ind file
        List<Payment> paymentList = new ArrayList<>();                                                                  // BL L of Payments Array payment list
        InputStream inputStreamN = file.getInputStream();
        CsvParserSettings setting = new CsvParserSettings();                                                            //Create parser to parse info to DB
        setting.setHeaderExtractionEnabled(true);                                                                       //If there is a header then dont process it
        CsvParser parser = new CsvParser(setting);                                                                      // Creater Csv parser w parser settings
        List<Record> parseAllRecords = parser.parseAllRecords(inputStreamN);                                            //Fetch records from InputStream
        parseAllRecords.forEach(record -> {                                                                             //parse all records for each
            Payment thePayment = new Payment();
            thePayment.setIban(record.getString("debitorIban"));                                                      // Get from Record and parse it to Payment new
            thePayment.setAmount(Float.parseFloat(record.getString("amount")));
        });

        return "Upload done.";

    }
}

