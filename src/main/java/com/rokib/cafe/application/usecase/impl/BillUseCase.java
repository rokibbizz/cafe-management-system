package com.rokib.cafe.application.usecase.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.rokib.cafe.application.repository.IBillRepository;
import com.rokib.cafe.application.usecase.IBillUseCase;
import com.rokib.cafe.domain.entities.Bill;
import com.rokib.cafe.domain.utils.Util;
import com.rokib.cafe.presentation.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

@Service
public class BillUseCase implements IBillUseCase {

    @Autowired
    private IBillRepository billRepository;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) throws FileNotFoundException, DocumentException {
        String fileName;
        if (requestMap.containsKey("isGenerate") && (Boolean) requestMap.get("isGenerate")) {
            fileName = (String) requestMap.get("uuid");
        } else {
            fileName = Util.getUUID();
            requestMap.put("uuid", fileName);
            insertBill(requestMap);
        }
        String data = "Name: " + requestMap.get("name") + "\n" + "Contact Number: " + requestMap.get("contactNumber")
                + "\n" + "Email: " + requestMap.get("email") + "\n" + "Payment Method: " + requestMap.get("paymentMethod");
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Admin\\Documents\\pdf\\"+fileName+".pdf"));
        document.open();
        setRectangleInPdf(document);

        return null;
    }

    private void setRectangleInPdf(Document document) throws DocumentException {

        Rectangle rectangle = new Rectangle(577,825,18,15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBorderColor(BaseColor.BLACK);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }

    private void insertBill(Map<String, Object> requestMap) {
        Bill bill = Bill.builder()
                .uuid((String) requestMap.get("uuid"))
                .name((String) requestMap.get("name"))
                .email((String) requestMap.get("email"))
                .contactNumber((String) requestMap.get("contactNumber"))
                .paymentMethod((String) requestMap.get("paymentMethod"))
                .total(Integer.valueOf((String) requestMap.get("total")))
                .productDetail((String) requestMap.get("productDetails"))
                .createdBy((String) authenticationFilter.getCurrentUser())
                .build();
        billRepository.save(bill);
    }
}
