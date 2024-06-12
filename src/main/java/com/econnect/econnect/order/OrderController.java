package com.econnect.econnect.order;

import com.econnect.econnect.cart.CartDtos;
import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/")
    public CommonResult order(@RequestBody CartDtos carts, @RequestParam("uid") String uid,
                              @RequestParam("usePoint") Integer usePoint) {
        System.out.println(carts.toString());
        System.out.println("carts : " + carts.getCarts().toString());
        return orderService.order(carts.getCarts(), uid, usePoint);
    }
}
