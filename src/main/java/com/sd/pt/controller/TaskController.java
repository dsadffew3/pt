package com.sd.pt.controller;
import com.sd.pt.entity.Tasks;
import com.sd.pt.entity.Users;
import com.sd.pt.mapper.TaskMapper;
import com.sd.pt.mapper.UserMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Api(tags = "任务管理接口") // 控制器描述
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getTask")
    @ApiOperation(value = "获取所有任务", notes = "返回所有任务的列表") // 接口描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功获取任务列表"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public List<Tasks> test() {
        List<Tasks> tasks = taskMapper.find();
        return tasks;
    }

    @PutMapping("/resetTask")
    @ApiOperation(value = "重置任务", notes = "根据任务信息重置任务") // 接口描述
    @ApiImplicitParam(name = "task", value = "任务信息", required = true, dataType = "Tasks") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "任务重置成功"),
            @ApiResponse(code = 400, message = "任务不存在"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String resetTask(@RequestBody Tasks task) {
        if (task == null) return "任务不存在";
        int result = taskMapper.updateById(task);
        if (result > 0) return "修改成功";
        return "修改失败";
    }

    @PostMapping("/addTask")
    @ApiOperation(value = "添加任务", notes = "添加一个新任务") // 接口描述
    @ApiImplicitParam(name = "task", value = "任务信息", required = true, dataType = "Tasks") // 参数描述
    @ApiResponses({
            @ApiResponse(code = 200, message = "任务添加成功"),
            @ApiResponse(code = 400, message = "任务安排不合理"),
            @ApiResponse(code = 500, message = "服务器内部错误")
    })
    public String addUser(Tasks task) {
        int abCounts = 0;
        int cCounts = 0;
        int availCounts = 0;

        String key = userMapper.getRole(task.getEmployee1());
        int value = userMapper.getava(task.getEmployee1());
        if (key != null) {
            if (key.equals("A") || key.equals("B")) abCounts++;
            else cCounts++;
        }
        if (value == 1) availCounts++;

        key = userMapper.getRole(task.getEmployee2());
        value = userMapper.getava(task.getEmployee2());
        if (key != null) {
            if (key.equals("A") || key.equals("B")) abCounts++;
            else cCounts++;
        }
        if (value == 1) availCounts++;

        key = userMapper.getRole(task.getEmployee3());
        value = userMapper.getava(task.getEmployee3());
        if (key != null) {
            if (key.equals("A") || key.equals("B")) abCounts++;
            else cCounts++;
        }
        if (value == 1) availCounts++;

        if (abCounts + cCounts < 3 || abCounts > cCounts || availCounts < 3)
            return "任务安排不合理";
        else {
            int result = taskMapper.insert(task);
            if (result == 0)
                return "插入失败";
            else {
                return "插入成功";
            }
        }
    }
}




