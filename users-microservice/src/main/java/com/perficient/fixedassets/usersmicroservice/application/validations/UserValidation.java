package com.perficient.fixedassets.usersmicroservice.application.validations;

import com.perficient.fixedassets.usersmicroservice.domain.entity.User;
import com.perficient.fixedassets.usersmicroservice.domain.models.response.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {

    private static List<ErrorResponse> errorResponseList;

    static {
        errorResponseList = new ArrayList<>();
    }

    private UserValidation() {
    }

    public static List<ErrorResponse> checkUser(User user) {

        errorResponseList = new ArrayList<>();

        if (user.getName() == null || user.getName().isEmpty()) {
            errorResponseList.add(new ErrorResponse("Name cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            errorResponseList.add(new ErrorResponse("Username cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            errorResponseList.add(new ErrorResponse("Email cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getPhone() == null || user.getPhone() == 0) {
            errorResponseList.add(new ErrorResponse("Phone cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            errorResponseList.add(new ErrorResponse("Password cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            errorResponseList.add(new ErrorResponse("Role cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getRegistrationDate() == null) {
            errorResponseList.add(new ErrorResponse("Registration Date cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getLastLogin() == null) {
            errorResponseList.add(new ErrorResponse("Last Login cannot be empty", HttpStatus.BAD_REQUEST));
        }

        if (user.getActive() == null) {
            errorResponseList.add(new ErrorResponse("Active cannot be empty", HttpStatus.BAD_REQUEST));
        }

        return errorResponseList;
    }

}
