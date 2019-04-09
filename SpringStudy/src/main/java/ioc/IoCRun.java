package ioc;

import ioc.context.AnClassPathXmlApplicationContext;
import ioc.domain.Student;
import ioc.context.ClassPathXmlApplicationContext;
import ioc.service.MainService;


public class IoCRun {

    public static void main(String[] arg) throws Exception {

        ioCByAnnotation();
    }

    public static void ioCByXML() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new
                ClassPathXmlApplicationContext("spring.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("student");
        student.setAge(10);
        System.out.println(student);
    }

    public static void ioCByAnnotation() throws Exception {
        AnClassPathXmlApplicationContext anClassPathXmlApplicationContext = new
                AnClassPathXmlApplicationContext("ioc.service.impl");
        MainService mainService = (MainService) anClassPathXmlApplicationContext.getBean("mainServiceImpl");
        mainService.doSomething("as");
    }

}
