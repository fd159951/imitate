import context.AnClassPathXmlApplicationContext;
import domain.Student;
import context.ClassPathXmlApplicationContext;
import service.MainService;

public class main {

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
                AnClassPathXmlApplicationContext("service.impl");
        MainService mainService = (MainService) anClassPathXmlApplicationContext.getBean("mainServiceImpl");
        mainService.doSomething("as");
    }

}
