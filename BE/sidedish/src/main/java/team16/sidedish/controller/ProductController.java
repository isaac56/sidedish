package team16.sidedish.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team16.sidedish.domain.enums.CategoryEnum;
import team16.sidedish.dto.response.BestMenuResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.dto.response.detail.DetailResponseDto;
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

    @GetMapping("/best")
    public ResponseEntity<BestMenuListResponseDto> bestMenuList() {
        return ResponseEntity.ok(productService.getBestAllMenuList());
    }

    @GetMapping("/best/{categoryId}")
    public ResponseEntity<BestMenuResponseDto> bestMenu(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(productService.getBestCategoryMenuList(categoryId));
    }

    @GetMapping("/main")
    public ResponseEntity<MenuListResponseDto> mainMenu() {
        return ResponseEntity.ok(productService.getMenuList(CategoryEnum.MAIN));
    }

    @GetMapping("/main/{hash}")
    public ResponseEntity<MenuResponseDto> mainMenuByHash(@PathVariable String hash) {
        return ResponseEntity.ok(productService.getMenu(CategoryEnum.MAIN, hash));
    }

    @GetMapping("/soup")
    public ResponseEntity<MenuListResponseDto> soupMenu() {
        return ResponseEntity.ok(productService.getMenuList(CategoryEnum.SOUP));
    }

    @GetMapping("/soup/{hash}")
    public ResponseEntity<MenuResponseDto> soupMenuByHash(@PathVariable String hash) {
        return ResponseEntity.ok(productService.getMenu(CategoryEnum.SOUP, hash));
    }

    @GetMapping("/side")
    public ResponseEntity<MenuListResponseDto> sideMenu() {
        return ResponseEntity.ok(productService.getMenuList(CategoryEnum.SIDE));
    }

    @GetMapping("/side/{hash}")
    public ResponseEntity<MenuResponseDto> sideMenuByHash(@PathVariable String hash) {
        return ResponseEntity.ok(productService.getMenu(CategoryEnum.SIDE, hash));
    }

    @GetMapping("/detail/{hash}")
    public ResponseEntity<DetailResponseDto> detailInfo(@PathVariable String hash) {
        return ResponseEntity.ok(productService.getDetail(hash));
    }
}
