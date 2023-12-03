package org.example.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Coursetm {
   private String courseid;
    private String coursename ;
    private String description;
    private String duration ;
    private String qualification;
    private String cost;
}
