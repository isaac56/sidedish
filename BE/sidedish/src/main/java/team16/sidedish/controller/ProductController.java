package team16.sidedish.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team16.sidedish.dto.response.BestMenuResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.dto.response.list.BestMenuListResponseDto;
import team16.sidedish.dto.response.list.MenuListResponseDto;
import team16.sidedish.service.ProductService;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/menu")
    public ResponseEntity<MenuListResponseDto> menuList() {
        return ResponseEntity.ok(productService.menuList());
    }

    @GetMapping("/menu/{detail_hash}")
    public ResponseEntity<MenuResponseDto> menu(@PathVariable("detail_hash") String detailHash) {
        return ResponseEntity.ok(productService.menu(detailHash));
    }

    @GetMapping("/best")
    public ResponseEntity<BestMenuListResponseDto> bestMenuList() {
        return ResponseEntity.ok(productService.bestMenuList());
    }

    @GetMapping("/best/{id}")
    public ResponseEntity<BestMenuResponseDto> bestMenu(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.bestMenu(id));
    }

    @GetMapping("/main")
    public ResponseEntity<MenuResponseDto> mainMenu() {
        return null;
    }
}
