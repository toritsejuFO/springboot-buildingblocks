package com.stacksimplify.restservices.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomErrorDetails {
    private Date timestamps;
    private String message;
    private String errordetails;
}
