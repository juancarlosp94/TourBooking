package com.group5.tourbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group5.tourbooking.aws.ImageUploadService;
import com.group5.tourbooking.dto.PolicyDto;
import com.group5.tourbooking.mapper.PolicyMapper;
import com.group5.tourbooking.model.Policy;
import com.group5.tourbooking.service.PolicyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/policy")

public class PolicyController {
        @Autowired
        PolicyService policyService;
        @Autowired
        PolicyMapper policyMapper;

        //CREATE
        @PostMapping(consumes = "multipart/form-data")
        ResponseEntity<?> createPolicy(
                @RequestParam("policy") String policyJson) throws IOException {

            ObjectMapper objectMapper = new ObjectMapper();
            PolicyDto policyDto = objectMapper.readValue(policyJson, PolicyDto.class);

            Policy policyCreated= new Policy();
            policyCreated =policyMapper.dtoToPolicy(policyDto);
            //ESTO ESTA BIEN, SE TIENE QUE RESOLVER LO DE AWS S3 PARA USARLO


            policyService.createPolicy(policyCreated);

            return ResponseEntity.ok(policyMapper.policyToDto(policyCreated));
        }
        //READ
        @GetMapping
        public ResponseEntity<List<PolicyDto>> getAllPolicy(){
            List<Policy> categories= policyService.findAllPolicy();

            List<PolicyDto> policyDtos= categories.stream()
                    .map(policyMapper::policyToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(policyDtos);
        }
        //READ BY ID
        @GetMapping("/{id}")
        public ResponseEntity<PolicyDto> getPolicyById(
                @PathVariable Long id){
            Policy policy= policyService.findPolicyById(id);
            PolicyDto policyDto= policyMapper.policyToDto(policy);

            return new ResponseEntity<>(policyDto, HttpStatus.OK);
        }

        //UPDATE
        @PutMapping(value = "/{id}", consumes = "multipart/form-data")
        public ResponseEntity<PolicyDto> updatePolicy(
                @RequestParam("policy") String policyJson) throws JsonProcessingException {

            ObjectMapper objectMapper = new ObjectMapper();
            PolicyDto policyDto = objectMapper.readValue(policyJson, PolicyDto.class);

            Policy existingPolicy = policyService.findPolicyById(policyDto.getId());

            if (existingPolicy == null) {
                throw new EntityNotFoundException("No se encontró una política con el ID especificado");
            }

            // Actualizar el nombre si se proporciona un nuevo nombre no vacío
            if (policyDto.getName() != null && !policyDto.getName().trim().isEmpty()) {
                existingPolicy.setName(policyDto.getName());
            }
            if (policyDto.getDescription() != null && !policyDto.getDescription().trim().isEmpty()) {
                existingPolicy.setDescription(policyDto.getDescription());
            }

            // Guardar los cambios en la base de datos
            Policy updatedPolicy = policyService.updatePolicy(existingPolicy);
            PolicyDto responseDto = policyMapper.policyToDto(updatedPolicy);

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }


        //DELETE
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePolicy(
                @PathVariable Long id
        ){
            Policy existingPolicy= policyService.findPolicyById(id);
            if(existingPolicy == null) {
                throw new EntityNotFoundException("No se encontró un tour con el ID especificado");
            }
            policyService.deletePolicy(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


