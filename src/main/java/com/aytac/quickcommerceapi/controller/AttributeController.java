package com.aytac.quickcommerceapi.controller;

import com.aytac.quickcommerceapi.dto.request.AttributeCreateRequest;
import com.aytac.quickcommerceapi.dto.request.AttributeUpdateRequest;
import com.aytac.quickcommerceapi.dto.response.ApiResponse;
import com.aytac.quickcommerceapi.model.Attribute;
import com.aytac.quickcommerceapi.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attributes")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }


    @GetMapping
    @Operation(summary = "Get All Attributes",
            description = "Retrieves a list of all attributes")
    public ResponseEntity<ApiResponse<List<Attribute>>> getAllAttributes() {
        return new ResponseEntity<>(
                new ApiResponse<>(true, attributeService.getAllAttributes(), null), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Attribute By Id",
            description = "Retrieve specific attribute by id")
    public ResponseEntity<ApiResponse<Attribute>> getAttributeById(@PathVariable Long id) {

        Attribute attribute = attributeService.getAttributeById(id);

        if (attribute != null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(true, attribute, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(false, null, "404 - NotFound"), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attribute>> postAttribute(@RequestBody AttributeCreateRequest request) {
        Attribute attribute = attributeService.createAttribute(request);
        return new ResponseEntity<>(
                new ApiResponse<>(true, attribute, null), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Attribute>> updateAttribute(@PathVariable Long id, @RequestBody AttributeUpdateRequest request) {
        Attribute attribute = attributeService.updateAttribute(id, request);
        if (attribute != null) {
            return new ResponseEntity<>(
                    new ApiResponse<>(true, attribute, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(false, null, "404 - NotFound"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Attribute>> deleteAttribute(@PathVariable Long id) {
        boolean isDone = attributeService.deleteAttributeById(id);

        if (isDone) {
            return new ResponseEntity<>(
                    new ApiResponse<>(true, null, null), HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new ApiResponse<>(false, null, "404 - Not Found"), HttpStatus.NOT_FOUND);


    }


}
