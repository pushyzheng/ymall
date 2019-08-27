package site.pushy.ymall.service;

import site.pushy.ymall.pojo.dto.Product;

import java.util.List;

public interface RecordService {

    List<Product> listItemRecord(Long userId);

    void saveItemRecord(Long productId, Long userId);

}
