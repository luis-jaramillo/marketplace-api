package dev.lfjaramillos.marketplace.demo.web.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired private AuthController authController;


    @Test
    void createToken() {
    }



}