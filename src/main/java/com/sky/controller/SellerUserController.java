package com.sky.controller;

import com.sky.constant.CookieConstant;
import com.sky.constant.ProjectConstant;
import com.sky.constant.RedisConstant;
import com.sky.dataobject.SellerDetail;
import com.sky.enums.ResultEnum;
import com.sky.service.SellerService;
import com.sky.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    private SellerService sellerService;

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        SellerDetail sellerDetail = sellerService.findSellerDetailByOpenid(openid);
        if (sellerDetail == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }

        //设置到Redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //设置到Cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token);

        return new ModelAndView("redirect:" + ProjectConstant.projectUrl + "/sell/seller/order/list");
    }

    @GetMapping("logout")
    public void logout() {

    }
}
