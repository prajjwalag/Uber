package com.prajjwal.project.Uber.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DriverDTO {

    private Long id;
    private UserDTO user;
    private Double rating;
    private Boolean available;
    private String vehicleId;

}
