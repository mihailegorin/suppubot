package com.mihail.suppubot.dto.responses;

import com.mihail.suppubot.entity.Department;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {

    private Integer id;
    private String city;
    private String district;
    private String address;
    private String phone;
    private String workingHours;
    private String description;
    private String googleMapsLink;

    public DepartmentResponse(Department department) {
        this.id = department.getId();
        this.city = department.getCity();
        this.district = department.getDistrict();
        this.address = department.getAddress();
        this.phone = department.getPhone();
        this.workingHours = department.getWorkingHours();
        this.description = department.getDescription();
        this.googleMapsLink = department.getGoogleMapsLink();
    }
}
