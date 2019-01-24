//package com.decathlon;
//
//import javax.ws.rs.core.MediaType;
//
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.decathlon.controller.ProductController;
//import com.decathlon.dto.ProductDto;
//import com.decathlon.service.ProductService;
//import com.sun.jersey.api.client.RequestBuilder;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = ProductController.class, secure = false))
//public class DecathlonApplicationTests {
//
//	@Autowired
//	public MockMvc mockMvc;
//	
//	@MockBean
//	public ProductService productService;
//	
//
//	public void getMapping(){
//		
//		ProductDto productDto=new ProductDto(1,"laptap","desc","medium","electronics");
//		
//		Mockito.when(productService.fetchProductDetailsById(Mockito.anyInt())).thenReturn(productDto);
//	
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/v1/public/products/{productId}").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
//		String excepted = " {id:1,productName:Ball,productDecription:Ball descp,productLevel:small,productSport:sports}";
//
//		JSONAssert.assertEquals(excepted, mvcResult.getResponse()
//				.getContentAsString(), false);
//	}
//}
