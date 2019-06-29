package com.example.myotto;

import com.squareup.otto.Bus;

public class OttoBus extends Bus {
    private volatile static OttoBus bus;

    private OttoBus() {
    }

    public static OttoBus getInstance() {
        if (bus == null) {
            synchronized (OttoBus.class) {
                if (bus == null) {
                    bus = new OttoBus();
                }
            }
        }
        return bus;
    }
}
