package com.cafecomdeploy.loanSimulation.controller;

import com.cafecomdeploy.loanSimulation.response.LoanResponse;
import com.cafecomdeploy.loanSimulation.service.Loan.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private ILoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> confirmLoan(@RequestParam(required = false) Long id) {
        LoanResponse response = loanService.confirmSimulation(id);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
