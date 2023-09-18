package com.vasu.VendorService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorRequest {
    private String name;
    private String email;
    private String address;
    private Long phone;
}