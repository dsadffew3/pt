package com.sd.pt.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sd.pt.entity.Users;
import com.sd.pt.mapper.UserMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = "用户管理接口") // 控制器描述
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUsers")
    @ApiOperation(value = "获取所有用户", notes = "返回所有用户的列表") // 接口描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取用户列表"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public List<Users> getUsers() {
        List<Users> users = userMapper.find();
        return users;
    }

    @GetMapping("/checkPriority/{id}/{password}")
    @ApiOperation(value = "检查用户权限", notes = "根据用户ID和密码检查用户权限") // 接口描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户权限检查成功"),
            @ApiResponse(code = 404, message = "用户不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public boolean checkPriority(@PathVariable int id, @PathVariable String password) {
        Users user = userMapper.selectById(id);
        if (user == null) return false;
        if (id == user.getId())
            if (Objects.equals(password, user.getPassword()))
                if (Objects.equals("admin", user.getRole()))
                    return true;
        return false;
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", notes = "添加一个新用户") // 接口描述
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "Users") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户添加成功"),
            @ApiResponse(code = 400, message = "用户添加失败"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String addUser(Users user) {
        int result = userMapper.insert(user);
        if (result == 0)
            return "插入失败";
        else {
            return "插入成功";
        }
    }

    @PutMapping("/resetUser")
    @ApiOperation(value = "重置用户信息", notes = "根据用户信息重置用户") // 接口描述
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "Users") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户信息重置成功"),
            @ApiResponse(code = 400, message = "用户不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public boolean resetUser(Users user) {
        if (user == null) return false;
        int result = userMapper.updateById(user);
        return result > 0;
    }

    @DeleteMapping("/delUser/{id}")
    @ApiOperation(value = "删除用户", notes = "根据用户ID删除用户") // 接口描述
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户删除成功"),
            @ApiResponse(code = 400, message = "用户删除失败"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String delUser(@PathVariable int id) {
        int result = userMapper.deleteById(id);
        if (result == 0)
            return "删除失败";
        else {
            return "删除成功";
        }
    }

    @GetMapping("/logUser")
    @ApiOperation(value = "用户登录", notes = "根据用户ID和密码登录") // 接口描述
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 404, message = "用户不存在"),
            @ApiResponse(code = 401, message = "密码错误"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public ResponseEntity<String> logUser(@RequestParam int id, @RequestParam String password) {
        Users user = userMapper.selectById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("该用户不存在");
        }

        if (id == user.getId() && Objects.equals(password, user.getPassword())) {
            return ResponseEntity.ok("登录成功");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("密码错误");
        }
    }

    @GetMapping("/getyourLocation")
    @ApiOperation(value = "获取当前用户IP地址", notes = "返回当前用户的IP地址") // 接口描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取IP地址"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String getuIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // 如果经过多个代理，X-Forwarded-For 可能包含多个 IP 地址
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim(); // 取第一个 IP 地址
        }
        return ipAddress;
    }

    @GetMapping("/getLocation/{id}")
    @ApiOperation(value = "获取用户IP地址", notes = "根据用户ID获取用户的IP地址") // 接口描述
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取用户IP地址"),
            @ApiResponse(code = 404, message = "用户不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String getIP(@PathVariable int id) {
        Users user = userMapper.selectById(id);
        if (user == null) {
            return "该用户不存在";
        } else {
            return user.getIpAddress();
        }
    }

    @PutMapping("/setLocation/{id}")
    @ApiOperation(value = "设置用户IP地址", notes = "根据用户ID设置用户的IP地址") // 接口描述
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功设置用户IP地址"),
            @ApiResponse(code = 404, message = "用户不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public boolean setIP(HttpServletRequest request, @PathVariable int id) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // 如果经过多个代理，X-Forwarded-For 可能包含多个 IP 地址
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        Users user = new Users();
        user.setIpAddress(ipAddress);
        UpdateWrapper<Users> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        int rows = userMapper.update(user, updateWrapper);
        return rows > 0;
    }
}