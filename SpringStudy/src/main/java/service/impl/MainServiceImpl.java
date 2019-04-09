package service.impl;

import newannotation.NewResource;
import newannotation.NewService;
import service.DoService;
import service.MainService;

import javax.annotation.Resource;

@NewService
public class MainServiceImpl implements MainService {

    @NewResource
    DoService doServiceImpl;

    public void doSomething(String s) {
        doServiceImpl.saySomething(s);
    }
}
