package com.zhangys.service;

import com.zhangys.service.iface.DemoIface;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
public class DemoIfaceImpl implements DemoIface {

    @Override
    public void hello() {

    }

}
