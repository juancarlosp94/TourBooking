package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Policy;
import com.group5.tourbooking.repository.PolicyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PolicyServiceImpl implements PolicyService{
    @Autowired
    PolicyRepository policyRepository;
    @Override
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public List<Policy> findAllPolicy() {
        return policyRepository.findAll();
    }

    @Override
    public Policy findPolicyById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return policyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can´t find Policy with ID: " + id));    }

    @Override
    public Policy updatePolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);

    }
}
