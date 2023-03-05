package com.rokib.cafe.domain.utils;

import com.rokib.cafe.application.repository.IUserRepository;
import com.rokib.cafe.domain.AllEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class Util {

    @Autowired
    private IUserRepository userRepository;

    public static String getUUID() {
        Date data = new Date();
        long time = data.getTime();
        return "BILL-" + time;
    }

    public void sendEmail(String message, AllEnums.EmailReceiver emailReceiver, Object others) {
        if (emailReceiver == AllEnums.EmailReceiver.ALLADMIN) {
            List<String> allAdminEmail = userRepository.gellAllAdmin();

        }
    }

    public static boolean isNullOrWhiteSpace(String data) {
        return Objects.isNull(data) || data.trim().length() == 0;
    }
}
