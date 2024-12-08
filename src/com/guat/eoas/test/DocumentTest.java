package com.guat.eoas.test;

import com.guat.eoas.pojo.Documents;
import com.guat.eoas.service.DocumentService;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DocumentTest {

    private final DocumentService service = new DocumentService();

    @Test
    void getDocumentTest() throws ParseException {
        String start = "2010-01-01 00:00:00.0";
        String end = "2024-12-31 23:59:59.0";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(start);
        Timestamp startTime = new Timestamp(startDate.getTime());
        Timestamp endTime = new Timestamp(endDate.getTime());
        List<Documents> documents = service.getDocuments(null, null, null, startTime, endTime, null, null);
        for (Documents document : documents) {
            System.out.println(document.getUpdated_at());
        }
    }
}
