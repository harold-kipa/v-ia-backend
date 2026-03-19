package com.v_ia_backend.kipa.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.v_ia_backend.kipa.dto.request.LoginRequest;
import com.v_ia_backend.kipa.dto.request.MovementFilesFinalRequest;
import com.v_ia_backend.kipa.dto.request.MovementFilesRequest;
import com.v_ia_backend.kipa.dto.request.MovementFilterRequest;
import com.v_ia_backend.kipa.entity.Movements;
// import com.v_ia_backend.kipa.dto.request.MovementsRequest; 
import com.v_ia_backend.kipa.service.MovementServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movement")
public class MovementsController {

    @Autowired
    private MovementServiceImpl movementService;

    public MovementsController(MovementServiceImpl movementService) {
        this.movementService = movementService;
    }
    @PostMapping("/get/files")
    public ResponseEntity<Resource> downloadZip(@RequestBody MovementFilesFinalRequest req) throws IOException {

        Path zipPath = movementService.buildZipToTempFile(req); // crea el zip en disco

        FileSystemResource resource = new FileSystemResource(zipPath.toFile());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivos.zip")
            .contentType(MediaType.parseMediaType("application/zip"))
            .contentLength(Files.size(zipPath))
            .body(resource);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getMovementController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementById(id));
        // Movements pdfResponse = movementService.getMovementById(id);
        // return ResponseEntity.ok()
        //             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= massiveMachinerySolicitude.pdf")
        //             .contentType(MediaType.APPLICATION_PDF)
        //             .body(pdfResponse.getPoContractId().getFileId().getFileUrl());

    }

    @GetMapping("/get/capex/{id}")
    public ResponseEntity<Object> getMovementCapexController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementByCapex(id));

    }

    @GetMapping("/get/opex/{id}")
    public ResponseEntity<Object> getMovementOpexController(@PathVariable Long id) {
        return ResponseEntity.ok(movementService.getMovementByOpex(id));

    }

    @PostMapping("/get-filter")
    public ResponseEntity<Object> getAllMovementsByFilterController(@Valid @RequestBody MovementFilterRequest movementFilterRequest) {
        return ResponseEntity.ok(movementService.getAllMovementsByFilter(movementFilterRequest));
    }
}
