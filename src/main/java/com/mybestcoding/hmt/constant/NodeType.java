package com.mybestcoding.hmt.constant;

/**
 * 节点类型
 */
public enum NodeType {
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    LIGHTINTENSITY("LightIntensity"),
    FLAMEALARM("FlameAlarm");

    private String type;

    NodeType(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }
}
