package com.v_ia_backend.kipa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v_ia_backend.kipa.service.AniSubaccountsServiceImpl;

@RestController
@RequestMapping("/ani-subaccounts")
public class AniSubaccountsController {

    @Autowired
    private AniSubaccountsServiceImpl aniSubaccountsService;

    public AniSubaccountsController(AniSubaccountsServiceImpl aniSubaccountsService) {
        this.aniSubaccountsService = aniSubaccountsService;
    }

    @GetMapping("/get-all/project")
    public ResponseEntity<Object> getAllAniSubaccountsProjectController() {
        return ResponseEntity.ok(aniSubaccountsService.getAllAniSubaccountsProject());
    }

    @GetMapping("/get-all/ani")
    public ResponseEntity<Object> getAllAniSubaccountsAniController() {
        return ResponseEntity.ok(aniSubaccountsService.getAllAniSubaccountsAni());
    }
}
