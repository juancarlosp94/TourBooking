package com.group5.tourbooking.mapper;

import com.group5.tourbooking.model.Policy;
import com.group5.tourbooking.dto.PolicyDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper

public class PolicyMapper {
        public PolicyDto policyToDto(Policy policy){
            if (policy == null) {
                return null;
            }

            PolicyDto dto= new PolicyDto();

            dto.setId(policy.getId());
            dto.setName(policy.getName());
            dto.setDescription(policy.getDescription());

            return dto;
        }

        public Policy dtoToPolicy(PolicyDto policyDto){
            if (policyDto == null) {
                return null;
            }

            Policy policy= new Policy();

            policy.setId(policyDto.getId());
            policy.setName(policyDto.getName());
            policy.setDescription(policyDto.getDescription());
            return policy;
        }
}
