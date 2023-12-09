package com.cimba.lightsout.factory;

import com.cimba.lightsout.LightsOut;

public interface LightsOutFactory {
    LightsOut createLightsOut(int size);
}
