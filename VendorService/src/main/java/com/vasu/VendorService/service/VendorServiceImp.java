package com.vasu.VendorService.service;

import com.vasu.VendorService.entity.Vendor;
import com.vasu.VendorService.exception.VendorException;
import com.vasu.VendorService.model.VendorRequest;
import com.vasu.VendorService.model.VendorResponse;
import com.vasu.VendorService.repository.VendorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;

@Service
public class VendorServiceImp implements VendorService{

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public String createVendor(VendorRequest vendorRequest) {

        Vendor vendor = Vendor.builder()
                .vendorName(vendorRequest.getName())
                .vendorPhone(vendorRequest.getPhone())
                .vendorAddress(vendorRequest.getAddress())
                .vendorEmail(vendorRequest.getEmail())
                .vendorRegistrationDate(Instant.now())
                .build();

        vendorRepository.save(vendor);

        return "Vendor Created Successfully !";
    }

    @Override
    public VendorResponse getVendorById(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorException("Vendor with given Id does not exist", "VENDOR_NOT_FOUND"));

        VendorResponse vendorResponse = new VendorResponse();
        BeanUtils.copyProperties(vendor, vendorResponse);

        return vendorResponse;
    }

    @Override
    public String updateVendor(Long vendorId, VendorRequest vendorRequest) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorException("Vendor with given Id does not exist", "VENDOR_NOT_FOUND"));

        if (vendorRequest.getName() != null) {
            vendor.setVendorName(vendorRequest.getName());
        }
        if (vendorRequest.getEmail() != null) {
            vendor.setVendorEmail(vendorRequest.getEmail());
        }
        if (vendorRequest.getAddress() != null) {
            vendor.setVendorAddress(vendorRequest.getAddress());
        }
        if (vendorRequest.getPhone() != null && vendorRequest.getPhone() > 0) {
            vendor.setVendorPhone(vendorRequest.getPhone());
        }

        vendorRepository.save(vendor);

        return "Vendor Updated Successfully !";
    }

    @Override
    public String deleteVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorException("Vendor with given Id does not exist", "VENDOR_NOT_FOUND"));
        vendorRepository.delete(vendor);

        return "Vendor Deleted Successfully !";
    }
}
