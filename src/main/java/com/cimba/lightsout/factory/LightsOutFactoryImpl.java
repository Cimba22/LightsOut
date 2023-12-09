package com.cimba.lightsout.factory;

import com.cimba.lightsout.LightsOut;

public class LightsOutFactoryImpl implements LightsOutFactory{
    @Override
    public LightsOut createLightsOut(int size) {
        return new LightsOut(size);
    }
}
