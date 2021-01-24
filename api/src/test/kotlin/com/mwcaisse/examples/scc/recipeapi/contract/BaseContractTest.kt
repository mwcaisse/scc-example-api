package com.mwcaisse.examples.scc.recipeapi.contract

import com.mwcaisse.examples.scc.recipeapi.controller.RecipeController
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.context.WebApplicationContext

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
open class BaseContractTest {

    @Autowired
    lateinit var webApplicationContext : WebApplicationContext

    @BeforeAll
    fun setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext)
    }

}