package team16.sidedish.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team16.sidedish.domain.enums.CategoryEnum;
import team16.sidedish.dto.response.ApiResult;
import team16.sidedish.dto.response.CategoryResponseDto;
import team16.sidedish.dto.response.DetailInfoResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.exception.NotFoundException;
import team16.sidedish.service.ProductService;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/best")
    public ApiResult<List<CategoryResponseDto>> bestMenuList() {
        return ApiResult.succeed(productService.getBestCategories());
    }

    @GetMapping("/best/{categoryId}")
    public ApiResult<CategoryResponseDto> bestMenu(@PathVariable Integer categoryId) {
        if (!productService.isCategoryBest(categoryId)) {
            throw new NotFoundException(categoryId + "는 베스트 카테고리에 없습니다.");
        }
        return ApiResult.succeed(productService.getCategory(categoryId));
    }

    @GetMapping("/main")
    public ApiResult<CategoryResponseDto> mainMenu() {
        return ApiResult.succeed(productService.getCategory(CategoryEnum.MAIN.getId()));
    }

    @GetMapping("/main/{hash}")
    public ApiResult<MenuResponseDto> mainMenuByHash(@PathVariable String hash) {
        return ApiResult.succeed(productService.getMenu(CategoryEnum.MAIN, hash));
    }

    @GetMapping("/soup")
    public ApiResult<CategoryResponseDto> soupMenu() {
        return ApiResult.succeed(productService.getCategory(CategoryEnum.SOUP.getId()));
    }

    @GetMapping("/soup/{hash}")
    public ApiResult<MenuResponseDto> soupMenuByHash(@PathVariable String hash) {
        return ApiResult.succeed(productService.getMenu(CategoryEnum.SOUP, hash));
    }

    @GetMapping("/side")
    public ApiResult<CategoryResponseDto> sideMenu() {
        return ApiResult.succeed(productService.getCategory(CategoryEnum.SIDE.getId()));
    }

    @GetMapping("/side/{hash}")
    public ApiResult<MenuResponseDto> sideMenuByHash(@PathVariable String hash) {
        return ApiResult.succeed(productService.getMenu(CategoryEnum.SIDE, hash));
    }

    @GetMapping("/detail/{hash}")
    public ApiResult<DetailInfoResponseDto> detailInfo(@PathVariable String hash) {
        return ApiResult.succeed(productService.getDetail(hash));
    }
}
