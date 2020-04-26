//package com.atliwen.web;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.Matchers;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserControllerTest
//{
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Autowired
//    private WebApplicationContext wac;
//    //模拟MVC
//    private MockMvc mvc;
//
//    @Before
//    public void setUp() {
//        //初始化MockMvc对象
//        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//
//    @Test
//    public void add() throws Exception {
//        String json = "{\"age\": 26,\"gender\":\"女\",\"name\": \"婷婷\"}";
//        // 设置请求地址
//        mvc.perform(post("/user/v1/add")
//                // 设置请求类型
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                // 传json参数
//                .content(json))
//                // 断言 是否是 200
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("status").value("0000"))
//                // 断言 是否返回包含Y
//                .andExpect(content().string(Matchers.containsString("婷婷")))
//                .andDo(print());
//    }
//
//    @Test
//    public void queryLearnList() throws Exception {
//        mvc.perform(get("/user/v1/queryList"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("status").value("0000"))
//                .andExpect(content().string(Matchers.containsString("婷婷")));
//
//    }
//
//    @Test
//    public void update() throws Exception {
//        add();
//        String json = "{\"id\":1, \"name\": \"默默\"}";
//        // 设置请求地址
//        mvc.perform(post("/user/v1/update")
//                // 设置请求类型
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                // 传json参数
//                .content(json))
//                // 断言 是否是 200
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("status").value("0000"))
//                // 断言 是否返回包含Y
//                .andExpect(content().string(Matchers.containsString("默默")))
//                .andDo(print());
//
//    }
//
//
//    @Test
//    public void queryOne() throws Exception {
//        String json = "{\"name\": \"默默\"}";
//        // 设置请求地址
//        mvc.perform(post("/user/v1/queryOne")
//                // 设置请求类型
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                // 传json参数
//                .content(json))
//                // 断言 是否是 200
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("status").value("0000"))
//                // 断言 是否返回包含Y
//                .andExpect(content().string(Matchers.containsString("默默")))
//                .andDo(print());
//    }
//
//    @Test
//    public void queryWhereList() throws Exception {
//        add();
//        String json = "{\"age\": 26}";
//        // 设置请求地址
//        mvc.perform(post("/user/v1/queryWhereList")
//                // 设置请求类型
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                // 传json参数
//                .content(json))
//                // 断言 是否是 200
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("status").value("0000"))
//                .andDo(print());
//    }
//
//
//    @Test
//    public void delete() throws Exception {
//
//        // 设置请求地址
//        mvc.perform(MockMvcRequestBuilders.delete("/test/delete?id={id}", 1))
//                // 断言 是否是 204
//                .andExpect(status().isOk())
//                // 断言 是否是 200
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("status").value("0000"))
//                .andDo(print());
//    }
//}
