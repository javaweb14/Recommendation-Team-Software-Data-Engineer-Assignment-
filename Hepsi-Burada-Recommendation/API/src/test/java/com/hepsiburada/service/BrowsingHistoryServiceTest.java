package com.hepsiburada.service;

import com.hepsiburada.database.model.BrowsingHistory;
import com.hepsiburada.database.repository.BrowsingHistoryRepository;
import com.hepsiburada.service.impl.BrowsingHistoryServiceImpl;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BrowsingHistoryServiceTest {

    @InjectMocks
    private final BrowsingHistoryService browsingHistoryService = new BrowsingHistoryServiceImpl();

    @Mock
    BrowsingHistoryRepository browsingHistoryRepository;

    @Test
    public void whenHaveTenProducts_getLastTenProductsViewedByUserId_returnTenProducts() {
        BrowsingHistory browsingHistory = new BrowsingHistory();
        browsingHistory.setUserId("user-74");
        browsingHistory.setProductId("product-1");
        browsingHistory.setProduceTime(new Date());

        BrowsingHistory browsingHistory1 = new BrowsingHistory();
        browsingHistory1.setUserId("user-74");
        browsingHistory1.setProductId("product-2");
        browsingHistory1.setProduceTime(new Date());

        BrowsingHistory browsingHistory2 = new BrowsingHistory();
        browsingHistory2.setUserId("user-74");
        browsingHistory2.setProductId("product-3");
        browsingHistory2.setProduceTime(new Date());

        BrowsingHistory browsingHistory3 = new BrowsingHistory();
        browsingHistory3.setUserId("user-74");
        browsingHistory3.setProductId("product-4");
        browsingHistory3.setProduceTime(new Date());

        BrowsingHistory browsingHistory4 = new BrowsingHistory();
        browsingHistory4.setUserId("user-74");
        browsingHistory4.setProductId("product-5");
        browsingHistory4.setProduceTime(new Date());

        BrowsingHistory browsingHistory5 = new BrowsingHistory();
        browsingHistory5.setUserId("user-74");
        browsingHistory5.setProductId("product-6");
        browsingHistory5.setProduceTime(new Date());

        BrowsingHistory browsingHistory6 = new BrowsingHistory();
        browsingHistory6.setUserId("user-74");
        browsingHistory6.setProductId("product-7");
        browsingHistory6.setProduceTime(new Date());

        BrowsingHistory browsingHistory7 = new BrowsingHistory();
        browsingHistory7.setUserId("user-74");
        browsingHistory7.setProductId("product-8");
        browsingHistory7.setProduceTime(new Date());

        BrowsingHistory browsingHistory8 = new BrowsingHistory();
        browsingHistory8.setUserId("user-74");
        browsingHistory8.setProductId("product-9");
        browsingHistory8.setProduceTime(new Date());

        BrowsingHistory browsingHistory9 = new BrowsingHistory();
        browsingHistory9.setUserId("user-74");
        browsingHistory9.setProductId("product-10");
        browsingHistory9.setProduceTime(new Date());

        List<BrowsingHistory> browsingHistories = new ArrayList<>();

        browsingHistories.add(browsingHistory);
        browsingHistories.add(browsingHistory1);
        browsingHistories.add(browsingHistory2);
        browsingHistories.add(browsingHistory3);
        browsingHistories.add(browsingHistory4);
        browsingHistories.add(browsingHistory5);
        browsingHistories.add(browsingHistory6);
        browsingHistories.add(browsingHistory7);
        browsingHistories.add(browsingHistory8);
        browsingHistories.add(browsingHistory9);

        Mockito.when(browsingHistoryRepository.findByUserId("user-74")).thenReturn(browsingHistories);

        assertEquals(10, browsingHistoryService.getLastTenProductsViewedByUserId("user-74").get().getProducts().size());
    }

    @Test
    public void whenHaveTwoProducts_getLastTenProductsViewedByUserId_returnZeroProducts() {

        BrowsingHistory browsingHistory = new BrowsingHistory();
        browsingHistory.setUserId("user-50");
        browsingHistory.setProductId("product-1");
        browsingHistory.setProduceTime(new Date());

        BrowsingHistory browsingHistory1 = new BrowsingHistory();
        browsingHistory1.setUserId("user-50");
        browsingHistory1.setProductId("product-2");
        browsingHistory1.setProduceTime(new Date());

        List<BrowsingHistory> browsingHistories = new ArrayList<>();

        browsingHistories.add(browsingHistory);
        browsingHistories.add(browsingHistory1);

        Mockito.when(browsingHistoryRepository.findByUserId("user-50")).thenReturn(browsingHistories);

        assertEquals(0, browsingHistoryService.getLastTenProductsViewedByUserId("user-50").get().getProducts().size());
    }


    @Test
    public void orderByProducerDate_getLastTenProductsViewedByUserId() {
        BrowsingHistory browsingHistory = new BrowsingHistory();
        browsingHistory.setUserId("user-74");
        browsingHistory.setProductId("product-1");
        browsingHistory.setProduceTime(new Date(System.currentTimeMillis() - 3600 * 1000));

        BrowsingHistory browsingHistory1 = new BrowsingHistory();
        browsingHistory1.setUserId("user-74");
        browsingHistory1.setProductId("product-2");
        browsingHistory1.setProduceTime(new Date(System.currentTimeMillis() - 3600 * 6000));

        BrowsingHistory browsingHistory2 = new BrowsingHistory();
        browsingHistory2.setUserId("user-74");
        browsingHistory2.setProductId("product-3");
        browsingHistory2.setProduceTime(new Date(System.currentTimeMillis() - 3600 * 3000));

        BrowsingHistory browsingHistory3 = new BrowsingHistory();
        browsingHistory3.setUserId("user-74");
        browsingHistory3.setProductId("product-4");
        browsingHistory3.setProduceTime(new Date());

        BrowsingHistory browsingHistory4 = new BrowsingHistory();
        browsingHistory4.setUserId("user-74");
        browsingHistory4.setProductId("product-5");
        browsingHistory4.setProduceTime(new Date());

        List<BrowsingHistory> browsingHistories = new ArrayList<>();

        browsingHistories.add(browsingHistory);
        browsingHistories.add(browsingHistory1);
        browsingHistories.add(browsingHistory2);
        browsingHistories.add(browsingHistory3);
        browsingHistories.add(browsingHistory4);


        Mockito.when(browsingHistoryRepository.findByUserId("user-74")).thenReturn(browsingHistories);


        assertEquals(browsingHistory1.getProductId(), browsingHistoryService.getLastTenProductsViewedByUserId("user-74").get().getProducts().get(0));

    }

}
