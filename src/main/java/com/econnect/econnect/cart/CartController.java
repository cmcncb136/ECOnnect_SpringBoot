package com.econnect.econnect.cart;

import com.econnect.econnect.result.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/add/")
    public CommonResult add(@RequestParam("uid") String uid, @RequestParam("productId") String productId) {
        return cartService.add(uid, productId);
    }

    @PostMapping("/remove/")
    public void remove(@RequestParam("id") Integer id) {
        cartService.delete(id);
    }

    @GetMapping("/plus/")
    public void plus(@RequestParam("id") Integer id) {
        cartService.plusCount(id);
    }

    @GetMapping("/minus/")
    public void minus(@RequestParam("id") Integer id) {
        cartService.minusCount(id);
    }

    @GetMapping("/find/")
    public CartDto find(@RequestParam("id") Integer id){
        Cart cart = cartService.find(id);
        if(cart == null) return null;

        return CartDto.toDto(cart);
    }

    @GetMapping("/selected/")
    public Boolean selected(@RequestParam("id") Integer id,
                            @RequestParam("selected") Boolean selected) {
        return cartService.selected(id, selected);
    }

    @PostMapping("/findByMemberId/")
    public List<CartDto> findByMemberId(@RequestParam("uid") String uid) {
        return cartService.cartToCartDto(
                cartService.findByMemberId(uid)
        );
    }
}