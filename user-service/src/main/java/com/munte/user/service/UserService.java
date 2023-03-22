package com.munte.user.service;

import com.munte.user.entity.AppUser;
import com.munte.user.repository.UserRepository;
import com.munte.user.valueobject.Department;
import com.munte.user.valueobject.ResponseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public AppUser saveUser(AppUser user) {
        log.info("Inside of saveUser from UserService");
        return userRepository.save(user);
    }

    public ResponseTemplate getUserWithDepartment(Long userId) {
        log.info("Inside of getUserWithDepartment from UserService");
        ResponseTemplate responseTemplate = new ResponseTemplate();
        AppUser user = userRepository.findByUserId(userId);
        Department department = restTemplate
                .getForObject("http://DEPARTMENT-SERVICE/departments/" +
                        user.getDepartmentId(), Department.class);

        responseTemplate.setDepartment(department);
        responseTemplate.setUser(user);

        return responseTemplate;
    }
}
