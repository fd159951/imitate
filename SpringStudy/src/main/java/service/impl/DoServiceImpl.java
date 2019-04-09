package service.impl;

import newannotation.NewService;
import service.DoService;

@NewService
public class DoServiceImpl implements DoService {
    public void saySomething(String s) {
        System.out.println("************"+s);
    }
}
