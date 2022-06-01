package com.mihail.suppubot.dto.incomming_data;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentData {

    private String city;
    private String district;
    private String address;
    private String phone;
    private String workingHours;
    private String description;
    private String googleMapsLink;

}
