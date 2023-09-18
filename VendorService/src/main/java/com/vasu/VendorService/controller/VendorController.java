package com.vasu.VendorService.controller;

import com.vasu.VendorService.model.VendorRequest;
import com.vasu.VendorService.model.VendorResponse;
import com.vasu.VendorService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;


    @PostMapping
    public ResponseEntity<String> createVendor(@RequestBody VendorRequest vendorRequest){

        String vendorStatus = vendorService.createVendor(vendorRequest);

        return new ResponseEntity<>(vendorStatus, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorResponse> getVendorById(@PathVariable("id") Long vendorId){

        VendorResponse vendorResponse = vendorService.getVendorById(vendorId);

        return new ResponseEntity<>(vendorResponse, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVendor(
            @PathVariable("id") Long vendorId, @RequestBody VendorRequest vendorRequest
    ){

        String vendorStatus = vendorService.updateVendor(vendorId, vendorRequest);

        return new ResponseEntity<>(vendorStatus, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVendor(@PathVariable("id") Long vendorId){

        String vendorStatus = vendorService.deleteVendor(vendorId);

        return new ResponseEntity<>(vendorStatus, HttpStatus.OK);
    }


}
