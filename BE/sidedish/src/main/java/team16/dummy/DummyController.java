package team16.dummy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team16.dummy.requestDTO.Category;
import team16.dummy.requestDTO.DetailDataWrapper;
import team16.dummy.requestDTO.SimpleData;

import java.util.List;

@ConditionalOnProperty("custom.dummy.import")
@RestController
@ResponseStatus(HttpStatus.NO_CONTENT)
@RequestMapping("/dummy")
public class DummyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private DummyService dummyService;

    @Autowired
    public DummyController(DummyService dummyService) {
        this.dummyService = dummyService;
    }

    @PostMapping("/best")
    public void insertBest(@RequestBody List<Category> categoryList) {
        for (Category bestCategory : categoryList) {
            dummyService.importSimpleData(bestCategory.getItems(), bestCategory.getName(), true);
        }
    }

    @PostMapping("/main")
    public void insertMain(@RequestBody List<SimpleData> simpleDataList) {
        dummyService.importSimpleData(simpleDataList, "main", false);
    }

    @PostMapping("/soup")
    public void insertSoup(@RequestBody List<SimpleData> simpleDataList) {
        dummyService.importSimpleData(simpleDataList, "soup", false);
    }

    @PostMapping("/side")
    public void insertSide(@RequestBody List<SimpleData> simpleDataList) {
        dummyService.importSimpleData(simpleDataList, "side", false);
    }

    @PutMapping("/detail")
    public void updateDetail(@RequestBody List<DetailDataWrapper> detailDataWrapperList) {
        dummyService.updateDetailData(detailDataWrapperList);
    }
}
