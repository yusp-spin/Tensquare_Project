package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService service;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", service.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", service.findById(spitId));
    }

    @PostMapping("")
    public Result save(@RequestBody Spit spit) {
        service.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@RequestBody Spit spit, @PathVariable String spitId) {
        spit.set_id(spitId);
        service.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        service.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentByud(@PathVariable String parentid,
                                   @PathVariable int page,@PathVariable int size){
        Page<Spit> pageData=service.findByParentid(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        //判断当前用户是否已经点赞，目前没做认证，暂时把userid写死
        String userid="111";
        //判断当前用户是否已经点赞
        if(redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
            return new Result(false,StatusCode.REPERROR,"不能重复点赞");
        }
        service.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid,1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }


}
