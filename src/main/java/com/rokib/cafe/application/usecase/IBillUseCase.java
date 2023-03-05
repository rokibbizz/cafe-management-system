package com.rokib.cafe.application.usecase;

import com.itextpdf.text.DocumentException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.Map;

public interface IBillUseCase {
    ResponseEntity<String> generateReport(Map<String, Object> requestMap) throws FileNotFoundException, DocumentException;
}
