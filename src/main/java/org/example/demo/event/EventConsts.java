package org.example.demo.event;

// mvvmfx事件枚举
public enum EventConsts {

    ShowMainView("ShowMainView"),
    SideMenuSelected("SideMenuSelected");

    private String key;

    EventConsts(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }


}
