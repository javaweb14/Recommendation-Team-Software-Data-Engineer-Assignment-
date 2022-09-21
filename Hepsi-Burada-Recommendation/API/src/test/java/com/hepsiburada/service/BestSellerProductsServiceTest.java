package com.hepsiburada.service;

import com.hepsiburada.database.model.BrowsingHistory;
import com.hepsiburada.database.model.OrderItems;
import com.hepsiburada.database.model.Products;
import com.hepsiburada.database.repository.BrowsingHistoryRepository;
import com.hepsiburada.database.repository.OrderItemsRepository;
import com.hepsiburada.database.repository.ProductsRepository;
import com.hepsiburada.dto.BrowsingHistoryResponseDto;
import com.hepsiburada.service.impl.BestSellerProductsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BestSellerProductsServiceTest {

    @InjectMocks
    private final BestSellerProductsService browsingHistoryService = new BestSellerProductsServiceImpl();

    @Mock
    BrowsingHistoryRepository browsingHistoryRepository;

    @Mock
    OrderItemsRepository orderItemsRepository;

    @Mock
    ProductsRepository productsRepository;



    @Test
    public void whenGivePersonalizedUserId_getLastTenProductsViewedByUserId_returnTenProducts() {

        prepareElevenProductForBrowsingHistoryRepository();

        prepareProductRepository();

        prepareOrderItemsRepository();

        Optional<BrowsingHistoryResponseDto> browsingHistoryResponseDto = browsingHistoryService.getBestSellerProducts("user-74");

        assertEquals("personalized", browsingHistoryResponseDto.get().getType());
        assertEquals(10, browsingHistoryResponseDto.get().getProducts().size());
    }

    @Test
    public void whenGiveLessFiveProductsUserId_getLastTenProductsViewedByUserId_returnEmptyProducts() {

        prepareElevenProductForBrowsingHistoryRepository();

        prepareProductRepository();

        OrderItems orderItem8 = new OrderItems();
        orderItem8.setProductId("product-8");
        orderItem8.setQuantity(4);

        OrderItems orderItem9 = new OrderItems();
        orderItem9.setProductId("product-9");
        orderItem9.setQuantity(4);

        OrderItems orderItem10 = new OrderItems();
        orderItem10.setProductId("product-10");
        orderItem10.setQuantity(20);

        List<OrderItems> orderItems = new ArrayList<>();
        orderItems.add(orderItem8);
        orderItems.add(orderItem9);
        orderItems.add(orderItem10);

        Mockito.when(orderItemsRepository.findAll()).thenReturn(orderItems);


        Optional<BrowsingHistoryResponseDto> browsingHistoryResponseDto = browsingHistoryService.getBestSellerProducts("user-74");

        assertEquals("personalized", browsingHistoryResponseDto.get().getType());
        assertEquals(0, browsingHistoryResponseDto.get().getProducts().size());
    }

    @Test
    public void whenGiveNonPersonalizedUserId_getLastTenProductsViewedByUserId_returnTenProducts() {

        prepareOrderItemsRepository();

        Optional<BrowsingHistoryResponseDto> browsingHistoryResponseDto = browsingHistoryService.getBestSellerProducts("user-74");

        assertEquals("non-personalized", browsingHistoryResponseDto.get().getType());
        assertEquals(10, browsingHistoryResponseDto.get().getProducts().size());
    }

    @Test
    public void whenGiveLessFiveProductsNonPersonalizedUserId_getLastTenProductsViewedByUserId_returnEmptyProducts() {

        OrderItems orderItem8 = new OrderItems();
        orderItem8.setProductId("product-8");
        orderItem8.setQuantity(4);

        OrderItems orderItem9 = new OrderItems();
        orderItem9.setProductId("product-9");
        orderItem9.setQuantity(4);

        OrderItems orderItem10 = new OrderItems();
        orderItem10.setProductId("product-10");
        orderItem10.setQuantity(20);

        List<OrderItems> orderItems = new ArrayList<>();
        orderItems.add(orderItem8);
        orderItems.add(orderItem9);
        orderItems.add(orderItem10);

        Mockito.when(orderItemsRepository.findAll()).thenReturn(orderItems);

        Optional<BrowsingHistoryResponseDto> browsingHistoryResponseDto = browsingHistoryService.getBestSellerProducts("user-74");

        assertEquals("non-personalized", browsingHistoryResponseDto.get().getType());
        assertEquals(0, browsingHistoryResponseDto.get().getProducts().size());

    }

    private void prepareOrderItemsRepository() {

        OrderItems orderItem1 = new OrderItems();
        orderItem1.setProductId("product-1");
        orderItem1.setQuantity(5);

        OrderItems orderItem2 = new OrderItems();
        orderItem2.setProductId("product-2");
        orderItem2.setQuantity(8);

        OrderItems orderItem3 = new OrderItems();
        orderItem3.setProductId("product-3");
        orderItem3.setQuantity(1);

        OrderItems orderItem4 = new OrderItems();
        orderItem4.setProductId("product-4");
        orderItem4.setQuantity(4);

        OrderItems orderItem5 = new OrderItems();
        orderItem5.setProductId("product-5");
        orderItem5.setQuantity(4);

        OrderItems orderItem6 = new OrderItems();
        orderItem6.setProductId("product-6");
        orderItem6.setQuantity(4);

        OrderItems orderItem7 = new OrderItems();
        orderItem7.setProductId("product-7");
        orderItem7.setQuantity(4);

        OrderItems orderItem8 = new OrderItems();
        orderItem8.setProductId("product-8");
        orderItem8.setQuantity(4);

        OrderItems orderItem9 = new OrderItems();
        orderItem9.setProductId("product-9");
        orderItem9.setQuantity(4);

        OrderItems orderItem10 = new OrderItems();
        orderItem10.setProductId("product-10");
        orderItem10.setQuantity(20);

        List<OrderItems> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);
        orderItems.add(orderItem5);
        orderItems.add(orderItem6);
        orderItems.add(orderItem7);
        orderItems.add(orderItem8);
        orderItems.add(orderItem9);
        orderItems.add(orderItem10);

        Mockito.when(orderItemsRepository.findAll()).thenReturn(orderItems);
    }

    private void prepareProductRepository() {
        Products product1 = new Products();
        product1.setProductId("product-1");
        product1.setCategoryId("category-1");

        Products product2 = new Products();
        product2.setProductId("product-2");
        product2.setCategoryId("category-1");

        Products product3 = new Products();
        product3.setProductId("product-3");
        product3.setCategoryId("category-1");

        Products product4 = new Products();
        product4.setProductId("product-4");
        product4.setCategoryId("category-1");

        Products product5 = new Products();
        product5.setProductId("product-5");
        product5.setCategoryId("category-2");

        Products product6 = new Products();
        product6.setProductId("product-6");
        product6.setCategoryId("category-2");

        Products product7 = new Products();
        product7.setProductId("product-7");
        product7.setCategoryId("category-3");

        Products product8 = new Products();
        product8.setProductId("product-8");
        product8.setCategoryId("category-1");

        Products product9 = new Products();
        product9.setProductId("product-9");
        product9.setCategoryId("category-1");

        Products product10 = new Products();
        product10.setProductId("product-10");
        product10.setCategoryId("category-1");


        Mockito.when(productsRepository.findByProductId("product-1")).thenReturn(Optional.of(product1));
        Mockito.when(productsRepository.findByProductId("product-2")).thenReturn(Optional.of(product2));
        Mockito.when(productsRepository.findByProductId("product-3")).thenReturn(Optional.of(product3));
        Mockito.when(productsRepository.findByProductId("product-4")).thenReturn(Optional.of(product4));
        Mockito.when(productsRepository.findByProductId("product-5")).thenReturn(Optional.of(product5));
        Mockito.when(productsRepository.findByProductId("product-6")).thenReturn(Optional.of(product6));
        Mockito.when(productsRepository.findByProductId("product-7")).thenReturn(Optional.of(product7));
        Mockito.when(productsRepository.findByProductId("product-8")).thenReturn(Optional.of(product8));
        Mockito.when(productsRepository.findByProductId("product-9")).thenReturn(Optional.of(product9));
        Mockito.when(productsRepository.findByProductId("product-10")).thenReturn(Optional.of(product10));

        List<Products> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product6);
        productsList.add(product7);
        productsList.add(product8);
        productsList.add(product9);
        productsList.add(product10);

        Mockito.when(productsRepository.findAll()).thenReturn(productsList);



    }

    private void prepareElevenProductForBrowsingHistoryRepository() {


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

        BrowsingHistory browsingHistory10 = new BrowsingHistory();
        browsingHistory10.setUserId("user-74");
        browsingHistory10.setProductId("product-1");
        browsingHistory10.setProduceTime(new Date());


        List<BrowsingHistory> browsingHistories = new ArrayList<>();
        browsingHistories.add(browsingHistory1);
        browsingHistories.add(browsingHistory2);
        browsingHistories.add(browsingHistory3);
        browsingHistories.add(browsingHistory4);
        browsingHistories.add(browsingHistory5);
        browsingHistories.add(browsingHistory6);
        browsingHistories.add(browsingHistory7);
        browsingHistories.add(browsingHistory9);
        browsingHistories.add(browsingHistory10);

        Mockito.when(browsingHistoryRepository.findByUserId("user-74")).thenReturn(browsingHistories);
    }
}
