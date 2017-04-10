package com.terry.rxjavapractice;

import com.hwangjr.rxbus.Bus;

/**
 * Created by tengchengwei on 2017/4/10.
 */

public final class RxBus {
    private static Bus sBus;

    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }
}
