package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Policy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PolicyService {

    Policy createPolicy(Policy policy);
    List<Policy> findAllPolicy();
    Policy findPolicyById(Long id);
    Policy updatePolicy(Policy policy);
    void deletePolicy(Long id);
}
