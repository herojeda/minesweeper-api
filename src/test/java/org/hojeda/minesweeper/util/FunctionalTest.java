package org.hojeda.minesweeper.util;

import org.hojeda.minesweeper.configuration.MainApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class FunctionalTest {

    private static boolean isInitialized = false;
    private MainApplication main;

    @BeforeEach
    public void init() {
        if (!isInitialized) {
            main = new MainApplication();
            main.init();
            isInitialized = true;
        }
    }

    @AfterEach
    public void cleanUp() throws InterruptedException {
        main.destroy();
        Thread.sleep(100);
        isInitialized = false;
    }

}
