package com.rokib.cafe.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String contactNumber;
    private String email;
    private String status;

}
