package com.quickclinic.patient.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicUserInfoDto {
    private Long id;
    private String name;
    private String email;
}
