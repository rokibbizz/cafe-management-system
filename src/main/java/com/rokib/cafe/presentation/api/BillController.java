package com.rokib.cafe.presentation.api;

import com.itextpdf.text.DocumentException;
import com.rokib.cafe.application.usecase.IBillUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private IBillUseCase billUseCase;

    @PostMapping("/generateReport")
    public ResponseEntity<String> generateReport(@RequestBody Map<String,Object> requestMap) throws DocumentException, FileNotFoundException {
        return billUseCase.generateReport(requestMap);
    }

}
