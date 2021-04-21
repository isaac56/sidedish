package team16.sidedish.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team16.sidedish.dto.response.BestMenuResponseDto;
import team16.sidedish.dto.response.MenuResponseDto;
import team16.sidedish.dto.response.list.BestMenuListResponseDto;

import java.util.Arrays;

@RequestMapping("/test")
@RestController
public class ResponseController {

    @GetMapping("/best")
    public ResponseEntity<BestMenuListResponseDto> best() {
        MenuResponseDto menuResponseDto1 = new MenuResponseDto();
        menuResponseDto1.setDetailHash("H9881");
        menuResponseDto1.setImage("https://cdn.bmf.kr/_data/product/H9881/f2a7b4df359c850b1a9eb57e17ddf6fc.jpg");
        menuResponseDto1.setAlt("[소중한식사] 경상도 한상차림");
        menuResponseDto1.setDeliveryType(Arrays.asList("새벽배송", "전국택배"));
        menuResponseDto1.setTitle("[소중한식사] 경상도 한상차림");
        menuResponseDto1.setDescription("경상도 명물 요리 세 가지를 한 상에!");
        menuResponseDto1.setNPrice("39,000");
        menuResponseDto1.setSPrice("31,200원");
        menuResponseDto1.setBadge(Arrays.asList("이벤트특가"));

        MenuResponseDto menuResponseDto2 = new MenuResponseDto();
        menuResponseDto2.setDetailHash("HDF4C");
        menuResponseDto2.setImage("https://cdn.bmf.kr/_data/product/HDF4C/954b78f9111bbef54ede9fdcdf1298d8.jpg");
        menuResponseDto2.setAlt("[딩고] 시그니처 3종 SET");
        menuResponseDto2.setDeliveryType(Arrays.asList("새벽배송", "전국택배"));
        menuResponseDto2.setTitle("[딩고] 시그니처 3종 SET");
        menuResponseDto2.setDescription("오늘 저녁은 이상하게 안주빨을 세워보고 싶다!");
        menuResponseDto2.setNPrice("32,800");
        menuResponseDto2.setSPrice("24,000원");
        menuResponseDto2.setBadge(Arrays.asList("이벤트특가"));

        BestMenuResponseDto bestMenuResponseDto1 = new BestMenuResponseDto();
        bestMenuResponseDto1.setCategoryId("17011200");
        bestMenuResponseDto1.setName("할인특가 세트상품");
        bestMenuResponseDto1.setItems(Arrays.asList(menuResponseDto1, menuResponseDto2));

        BestMenuListResponseDto bestMenuListResponseDto = new BestMenuListResponseDto();
        bestMenuListResponseDto.setBody(Arrays.asList(bestMenuResponseDto1));

        return new ResponseEntity(bestMenuListResponseDto, HttpStatus.OK);
    }
}
