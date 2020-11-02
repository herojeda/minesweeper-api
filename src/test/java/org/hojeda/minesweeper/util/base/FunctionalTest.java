package org.hojeda.minesweeper.util.base;

import org.hojeda.minesweeper.configuration.MainApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class FunctionalTest {

    private static boolean isInitialized = false;
    private MainApplication main;

    protected final String localAddress;
    protected final String baseUrl;

    public FunctionalTest() {
        try {
            localAddress = InetAddress.getLocalHost().getHostAddress();
            baseUrl = "http://:host:8080".replace(":host", localAddress);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

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
