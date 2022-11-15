package com.dailycodebuffer.user.service;

import ch.qos.logback.classic.spi.TurboFilterList;
import com.dailycodebuffer.user.VO.Department;
import com.dailycodebuffer.user.VO.ResponseTemplateVo;
import com.dailycodebuffer.user.entity.User;
import com.dailycodebuffer.user.repository.UserRepository;
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
    public User saveUser(User user){
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVo getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVo vo= new ResponseTemplateVo();
        User user= userRepository.findByuserId(userId);
        Department department=
                restTemplate.getForObject("http://localhost:9001/departments/"+ user.getDepartmentId(),Department.class);
        vo.setUser(user);
       vo.setDepartment(department);
       return vo;
    }
}
