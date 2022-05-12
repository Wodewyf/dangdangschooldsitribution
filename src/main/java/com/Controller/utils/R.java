package com.Controller.utils;

import lombok.Data;

@Data
public class R {
    private String flag;
    private Object data;

    public R() {

    }

    public R(String flag) {
        this.flag = flag;
    }

    public R(String flag, Object data) {
        this.flag = flag;
        this.data = data;
    }
}
