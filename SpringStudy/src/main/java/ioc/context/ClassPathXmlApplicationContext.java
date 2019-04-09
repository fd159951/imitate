package ioc.context;


import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**通过XML定义注入Bean
 * @author fd
 * @date 2019-4-9
 */
public class ClassPathXmlApplicationContext {
    // XML读取路径
    private String xmlPath;

    public ClassPathXmlApplicationContext(String xmlPath){
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId) throws Exception {
        if(StringUtils.isEmpty(beanId)){
            throw new Exception("beanID不能为空");
        }
        // 1.解析XML文件
        List<Element> readerXML = readXML();
        if(readerXML == null || readerXML.isEmpty()){
            throw new Exception("XML文件为空");
        }
        // 2.获取bean节点信息
        String className = findBeanById(readerXML,beanId);
        // 3.使用反射机制初始化
        Object instanceBean = instanceBean(className);

        return instanceBean;
    }

    /**
     * 通过beanID获取className
     * @param readerXML
     * @param beanId
     * @return
     */
    public String findBeanById(List<Element> readerXML,String beanId){
        for(Element element : readerXML){
            String xmlBeanId = element.attributeValue("id");
            if(StringUtils.isEmpty(xmlBeanId)){
                continue;
            }
            if(xmlBeanId.endsWith(beanId)){
                String beanClass = element.attributeValue("class");
                return beanClass;
            }
        }
        return null;
    }

    /**
     * 初始化对象
     * @param className
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object instanceBean(String className) throws Exception {
        if(StringUtils.isEmpty(className)){
            throw new Exception("配置错误");
        }
        Class<?> classInfo = Class.forName(className);
        return classInfo.newInstance();
    }

    /**
     * 解析XML
     * @return
     * @throws DocumentException
     */
    public List<Element> readXML() throws DocumentException {
        // 1.读取XML文件
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourceAsStream(xmlPath));

        // 2.获取根节点
        Element rootElement = document.getRootElement();

        // 3.获取子节点
        List<Element> elementList = rootElement.elements();

        return elementList;
    }



    /**
     *获取上下文路径
     * @param xmlPath
     * @return
     */
    public InputStream getResourceAsStream(String xmlPath){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }

}
