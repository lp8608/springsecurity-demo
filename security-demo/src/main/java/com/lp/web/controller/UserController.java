package com.lp.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lp.dto.User;
import com.lp.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @JsonView(User.UserSimpleView.class)
    @GetMapping
    @ApiOperation(value = "查询用户列表")
    public List<User> queryUser(UserQueryCondition queryCondition ,
                                @PageableDefault(page=2,size = 15,sort={"username","age"},direction = Sort.Direction.DESC) Pageable pageable){
        //System.out.println("queryCondition : " + ReflectionToStringBuilder.toString(queryCondition, ToStringStyle.MULTI_LINE_STYLE));
        //System.out.println("pageable : " + ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @JsonView(User.UserDetailView.class)
    @GetMapping("/{id:\\d+}")
    @ApiOperation("获取用户详情")
    public User getUserInfo(@ApiParam("用户ID") @PathVariable String id){
        //ControllerExcpeitonHandler中已经提前处理的这个异常，导致TimeInterceptor afterCompletion无法获取到error
        //throw new UserNotExistException(id);
        //由于ControllerExcpeitonHandler中没有处理这个异常，TimeInterceptor afterCompletion中能获取到这个异常
        //throw new RuntimeException("user not exist");
        User user = new User();
        user.setUsername("tom");
        return user;
    }


    @PostMapping
    @ApiOperation("创建用户")
    public User createUser(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
        return user;
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("更新用户")
    public User updateUser(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(error -> {
                FieldError fieldError = (FieldError)error;
                String errorMsg = fieldError.getField() +  fieldError.getDefaultMessage();
                System.out.println(errorMsg);
            });
        }
        user.setId("1");
        return user;
    }


    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable String id){
        System.out.println("delete  user where id = " + id);
    }



    @GetMapping("/me")
    public Object getMe(@AuthenticationPrincipal UserDetails userDetails){
        //return new SimpleResponse(SecurityContextHolder.getContext().getAuthentication());
        return userDetails;
    }
}
