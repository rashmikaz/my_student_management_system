package org.example.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Signuptm {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String username;
    private String phoneNumber;
    private String password;
}
