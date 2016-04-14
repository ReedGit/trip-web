package com.bysj.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bysj.model.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
      "classpath:spring-context.xml" })
public class CollectionServiceTest {

    @Resource(name="collectionService")
    private CollectionService collectionService;
    
    @Test
    public void deleteCollection(){
        Collection collection = new Collection();
        collection.setTravelId(2);
        collection.setUserId(6);
        
        boolean bool = collectionService.deleteCollection(collection);
        System.out.println(bool);
    }
}
