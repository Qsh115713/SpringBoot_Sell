package com.sky.controller;

import com.sky.dto.OrderDTO;
import com.sky.enums.ResultEnum;
import com.sky.exception.SellException;
import com.sky.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("page", page);
        map.put("size", size);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            orderService.cancel(orderDTO);
            map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常{}", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            map.put("orderDTO", orderDTO);
        } catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常{}", e.getMessage());
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("order/detail", map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        map.put("url", "/sell/seller/order/list");
        try {
            OrderDTO orderDTO = orderService.findByOrderId(orderId);
            orderService.finish(orderDTO);
            map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMsg());
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常{}", e.getMessage());
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error", map);
        }
        return new ModelAndView("common/success");
    }
}
