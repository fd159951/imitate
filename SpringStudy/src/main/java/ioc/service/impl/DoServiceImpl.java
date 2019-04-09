package ioc.service.impl;

import ioc.newannotation.NewService;
import ioc.service.DoService;

@NewService
public class DoServiceImpl implements DoService {
    public void saySomething(String s) {
        System.out.println("************"+s);
    }
}
