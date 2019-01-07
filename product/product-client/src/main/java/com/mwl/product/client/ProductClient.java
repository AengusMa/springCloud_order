package com.mwl.product.client;

import com.mwl.product.client.ProductClient.ProductClientFallback;
import com.mwl.product.common.DecreaseStockInput;
import com.mwl.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mawenlong
 * @date 2018/12/19
 */
@FeignClient(name = "product",fallback = ProductClient.ProductClientFallback.class)
public interface ProductClient {

    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

    @GetMapping("/msg")
    String productMsg();

    @Component
    static class ProductClientFallback implements ProductClient{

        @Override
        public List<ProductInfoOutput> listForOrder(List<String> productIdList) {
            System.out.println("listForOrder服务降级");
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
            System.out.println("decreaseStock服务降级");
        }

        @Override
        public String productMsg() {
            System.out.println("decreaseStock服务降级");
            return null;
        }
    }
}
