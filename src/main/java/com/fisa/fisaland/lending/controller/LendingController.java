package com.fisa.fisaland.lending.controller;

import com.fisa.fisaland.lending.dto.LendingRequestDto;
import com.fisa.fisaland.lending.dto.ReturnRequestDto;
import com.fisa.fisaland.lending.service.LendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/village")
public class LendingController {
    @Autowired
    private LendingService lendingService;

    @PostMapping("/rent")
    public void borrowProduct(@RequestBody LendingRequestDto requestDto) {
        lendingService.createLending(requestDto);
    }

    @PostMapping("/giveback/request")
    public void requestProductReturn(@RequestBody ReturnRequestDto requestDto) {
        lendingService.requestReturn(requestDto);
    }

    @PostMapping("/giveback/approve")
    public void approveProductReturn(@RequestBody Long rentalId) {
        lendingService.approveReturn(rentalId);
    }
}
