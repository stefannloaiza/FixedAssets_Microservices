package com.perficient.fixedassets.assetsmicroservice.application.validations;

import com.perficient.fixedassets.assetsmicroservice.domain.entity.Asset;
import com.perficient.fixedassets.assetsmicroservice.domain.models.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class AssetValidation {
    private static List<ErrorResponse> errorResponseList;

    static {
        errorResponseList = new ArrayList<>();
    }

    private AssetValidation() {
    }

    public static List<ErrorResponse> checkAsset(Asset asset) {
        errorResponseList = new ArrayList<>();

        if (Objects.isNull(asset)) {
            errorResponseList.add(new ErrorResponse("Asset not found", HttpStatus.BAD_REQUEST));
            log.error("Asset not found: {}", asset);
        }
        if (Objects.isNull(asset.getName()) || asset.getName().isBlank()) {
            errorResponseList.add(new ErrorResponse("Asset name is required", HttpStatus.BAD_REQUEST));
            log.error("Asset name is required: {}", asset);
        }
        if (Objects.isNull(asset.getCode()) || asset.getCode().isBlank()) {
            errorResponseList.add(new ErrorResponse("Asset code is required", HttpStatus.BAD_REQUEST));
            log.error("Asset code is required: {}", asset);
        }
        if (Objects.isNull(asset.getStatus())) {
            errorResponseList.add(new ErrorResponse("Asset status is required", HttpStatus.BAD_REQUEST));
            log.error("Asset status is required: {}", asset);
        }

        return errorResponseList;
    }
}
