package org.hojeda.minesweeper.configuration;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Objects;

public class Context {

    private static Injector injector;

    public static void init() {
        if (Objects.isNull(injector)) injector = Guice.createInjector(new AppModule());
    }

    public static Injector getInjector() {
        return injector;
    }
}
