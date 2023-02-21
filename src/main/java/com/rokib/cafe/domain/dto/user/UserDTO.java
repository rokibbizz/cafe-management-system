package com.rokib.cafe.domain.dto.user;

import lombok.*;


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
