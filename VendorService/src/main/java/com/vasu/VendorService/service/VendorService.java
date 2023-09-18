package com.vasu.VendorService.service;

import com.vasu.VendorService.model.VendorRequest;
import com.vasu.VendorService.model.VendorResponse;

public interface VendorService {
    String createVendor(VendorRequest vendorRequest);

    VendorResponse getVendorById(Long vendorId);

    String updateVendor(Long vendorId, VendorRequest vendorRequest);

    String deleteVendor(Long vendorId);
}
