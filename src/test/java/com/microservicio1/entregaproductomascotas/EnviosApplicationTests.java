package com.microservicio1.entregaproductomascotas;

import org.junit.jupiter.api.*;

class EnviosApplicationTests {

    @BeforeEach
    void setUp() {
        System.out.println("Antes de cada test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Después de cada test");
    }

    @Test
    void contextLoads() {
        Assertions.assertTrue(true);
    }
}