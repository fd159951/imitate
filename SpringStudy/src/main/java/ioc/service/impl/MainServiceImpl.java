package ioc.service.impl;

import ioc.newannotation.NewResource;
import ioc.newannotation.NewService;
import ioc.service.DoService;
import ioc.service.MainService;

@NewService
public class MainServiceImpl implements MainService {

    @NewResource
    DoService doServiceImpl;

    public void doSomething(String s) {
        doServiceImpl.saySomething(s);
    }
}
