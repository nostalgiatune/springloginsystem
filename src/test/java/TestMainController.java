//
//import com.tuoppi.springsecuresession.config.RootConfig;
//import com.tuoppi.springsecuresession.config.WebConfig;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {
//    WebConfig.class,
//    RootConfig.class
//})
//@WebAppConfiguration
//public class TestMainController {
//    
//    @Autowired
//    private WebApplicationContext ctx;
//    
//    private MockMvc mockMvc;
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void testRootGet() throws Exception {
//        mockMvc.perform(get("/"))
//                .andExpect(view().name("index"));
//    }
//    
//    @Test
//    @Ignore
//    public void login() throws Exception {
//        mockMvc.perform(get("/login"))
//                .andExpect(model().hasErrors());
//    }
//    
//}
