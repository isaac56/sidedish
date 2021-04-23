package team16.sidedish.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team16.sidedish.dto.response.list.MenuListResponseDto;
import team16.sidedish.service.ProductService;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<MenuListResponseDto> menu() {
        return ResponseEntity.ok(productService.menu());
    }
}
