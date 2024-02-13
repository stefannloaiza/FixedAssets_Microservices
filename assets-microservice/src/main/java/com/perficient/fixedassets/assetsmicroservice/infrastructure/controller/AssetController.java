package com.perficient.fixedassets.assetsmicroservice.infrastructure.controller;

import com.perficient.fixedassets.assetsmicroservice.application.usecase.AssetUseCase;
import com.perficient.fixedassets.assetsmicroservice.domain.models.dto.AssetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetUseCase assetUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<AssetDTO> getAllAsset() {
        return assetUseCase.getAllAssets();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetUseCase.getAsset(id));
    }

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<AssetDTO> getAssetByStatus(@PathVariable String status) {
        return assetUseCase.getAssetsByStatus(status);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createAsset(@RequestBody AssetDTO assetDTO) {
        assetUseCase.createAsset(assetDTO);
        return ResponseEntity.ok("Asset created successfully");
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateAsset(@PathVariable Long id, @RequestBody AssetDTO assetDTO) {
        return Boolean.TRUE.equals(assetUseCase.updateAsset(id, assetDTO)) ?
                ResponseEntity.ok("Asset updated successfully") :
                ResponseEntity.notFound().build();
    }
}