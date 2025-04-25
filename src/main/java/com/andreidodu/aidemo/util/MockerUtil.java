package com.andreidodu.aidemo.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockerUtil {

    public List<String> getRealMockData() {
        List<String> realMockData = new ArrayList<>();
        realMockData.add("How beautiful this is!");
        realMockData.add("Horrible, avoid it.");
        realMockData.add("I don't know what to say.");
        realMockData.add("What crazy nonsense!");
        return realMockData;
    }

}
