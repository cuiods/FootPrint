package com.codingfairy.web.controller;

import com.codingfairy.bl.service.UserService;
import com.codingfairy.bl.vo.*;
import com.codingfairy.util.enums.Gender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yyy on 2017/3/20.
 */
@Api(value="/user", description = "Application user API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "Get user info", notes = "Get detail info of a user",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<UserVo> getInfo(@PathVariable int id){
        return userService.getUser(id);
    }

    @ApiOperation(value = "edit user info", notes = "edit user's detail info",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/edit/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<UserVo> editInfo(@PathVariable int id, @RequestParam String avatar,
                                     @RequestParam Gender gender,@RequestParam String description){
        return userService.updateUser(id,avatar,gender,description);
    }

    @ApiOperation(value = "make the route", notes = "make route by combining stories",
                response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/makeRoute/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<RouteVo> makeRoute(@PathVariable int id,@RequestParam List<Integer> storyIds,
                                       @RequestParam String description){
        return userService.saveRoute(id,storyIds,description);
    }

    @ApiOperation(value = "delete the route", notes = "delete existed route",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/deleteRoute/{routeId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Void> deleteRoute(@PathVariable int routeId){
         return userService.deleteRoute(routeId);
    }

    @ApiOperation(value = "follow a user", notes = "follow a user",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/follow/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Boolean> follow(@PathVariable int id,@RequestParam int followId){
        return userService.follow(id,followId);
    }

    /**
     *
     * @param id 这个id是follow本身的id，不是用户的id
     * @return
     */
    @ApiOperation(value = "unfollow a user", notes = "cancel follow a user",
            response = ResultVo.class, produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/unfollow/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo<Void> unfollow(@PathVariable int id){
        return userService.unfollow(id);
    }





}
