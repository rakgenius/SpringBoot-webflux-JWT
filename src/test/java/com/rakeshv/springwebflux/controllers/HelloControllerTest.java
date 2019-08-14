package com.rakeshv.springwebflux.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    @Test
    void sayHello() {
        HelloController controller = new HelloController();
        String result = controller.sayHello("Dolly", new BindingAwareModelMap());
        assertEquals("hello", result);
    }
}
