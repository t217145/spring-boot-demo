package com.cyrus822.demo.GraphQL.backend.policy.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cyrus822.demo.GraphQL.backend.policy.domains.Policy;
import com.cyrus822.demo.GraphQL.backend.policy.repos.PolicyRepo;

@RestController
public class PolicyController {
    
    @Autowired
    private PolicyRepo repo;

    @PostMapping("/getPolicyKey")
    public String getPolicy(@RequestBody int policyId){
        String rtn = null;
        Optional<Policy> oPolicy = repo.findById(policyId);
        if(oPolicy.isPresent()){
            rtn = oPolicy.get().getPolicyKey();
        }
        return rtn;
    }

    @PostMapping("/getPolicyStatusByKey")
    public String getPolicyStatusByKey(@RequestBody String policyKey){
        String rtn = null;
        Optional<Policy> oPolicy = repo.getPolicyByPolicyKey(policyKey);
        if(oPolicy.isPresent()){
            rtn = oPolicy.get().getStatus();
        }
        return rtn;
    }

    @PostMapping("/getPolicyApplicantByKey")
    public int getPolicyApplicantByKey(@RequestBody String policyKey){
        int rtn = -1;
        Optional<Policy> oPolicy = repo.getPolicyByPolicyKey(policyKey);
        if(oPolicy.isPresent()){
            rtn = oPolicy.get().getApplicantId();
        }
        return rtn;
    }
    
    @PostMapping("/getPolicyBeneficialByKey")
    public int getPolicyBeneficialByKey(@RequestBody String policyKey){
        int rtn = -1;
        Optional<Policy> oPolicy = repo.getPolicyByPolicyKey(policyKey);
        if(oPolicy.isPresent()){
            rtn = oPolicy.get().getBeneficialId();
        }
        return rtn;
    }
    
    @PostMapping("/getPolicyAmtByKey")
    public float getPolicyAmtByKey(@RequestBody String policyKey){
        float rtn = -1.0f;
        Optional<Policy> oPolicy = repo.getPolicyByPolicyKey(policyKey);
        if(oPolicy.isPresent()){
            rtn = oPolicy.get().getAmt();
        }
        return rtn;
    }      
}