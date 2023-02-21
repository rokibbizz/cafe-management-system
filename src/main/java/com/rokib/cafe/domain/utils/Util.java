package com.rokib.cafe.domain.utils;

import com.rokib.cafe.application.repository.IUserRepository;
import com.rokib.cafe.domain.AllEnums;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Util {

    @Autowired
    private IUserRepository userRepository;

    public void sendEmail(String message, AllEnums.EmailReceiver emailReceiver,Object others){
        if(emailReceiver== AllEnums.EmailReceiver.ALLADMIN){
            List<String> allAdminEmail = userRepository.gellAllAdmin();

        }
    }
}
