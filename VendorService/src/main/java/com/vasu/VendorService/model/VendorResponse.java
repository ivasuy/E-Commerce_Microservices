package com.vasu.VendorService.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponse {
    private Long vendorId;
    private String vendorName;
    private String vendorEmail;
    private String vendorAddress;
    private Long vendorPhone;
    private Instant vendorRegistrationDate;
}
