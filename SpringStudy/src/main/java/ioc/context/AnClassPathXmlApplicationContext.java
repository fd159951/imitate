package ioc.context;

import ioc.newannotation.NewResource;
import ioc.newannotation.NewService;
import org.apache.commons.lang.StringUtils;
import ioc.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**通过注解注入Bean
 * @author fd
 * @date 2019-4-9
 */
public class AnClassPathXmlApplicationContext {

    private String packageName;

    private static ConcurrentHashMap<String,Object> beansMap = null;

    /**
     *
     * @param packageName 扫包范围
     */
    public AnClassPathXmlApplicationContext(String packageName) throws Exception {
        this.packageName = packageName;
        beansMap = new ConcurrentHashMap<String,Object>();
        initBeans();
        initEntryField();
    }

    /**
     * 向所有bean中注入属性
     * @throws Exception
     */

    public void initEntryField() throws Exception {
        //todo 多个bean之间有依赖如何处理
        for (Map.Entry<String,Object> entry : beansMap.entrySet()){
            Object bean = entry.getValue();
            attrAssign(bean);
        }
    }

    /**
     * 获取Bean
     * @param beanId
     * @return
     * @throws Exception
     */
    public Object getBean(String beanId) throws Exception {
        if(StringUtils.isEmpty(beanId)){
            throw new Exception("BeanId不能为空");
        }
        Object object = beansMap.get(beanId);
        if(object == null){
            throw new Exception("object not found");
        }
        return object;
    }

    /**
     * 扫包初始化Bean
     * @throws Exception
     */
    public void initBeans() throws Exception {
        // 1.扫描包，获取所有类
        List<Class<?>> classList = ClassUtil.getClasses(packageName);

        // 2.判断类是否有注解
        findClassAnnotation(classList);

        // 3.使用反射初始化Bean
        if(beansMap == null || beansMap.isEmpty()){
            throw new Exception("该包下无注解");
        }
    }

    /**
     * 获取class
     * @param classList
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void findClassAnnotation(List<Class<?>> classList) throws IllegalAccessException, InstantiationException {
        for(Class<?> tempClass: classList){
            NewService newService = tempClass.getAnnotation(NewService.class);
            if(newService != null){
                //todo 通过name获取class
                //获取类名
                String className = tempClass.getSimpleName();
                beansMap.put(ClassUtil.toLowerCaseFirstOne(className),tempClass.newInstance());
                continue;
            }
        }
    }

    /**
     * 扫描Bean中的注入属性
     * @param object
     * @throws Exception
     */
    public void attrAssign(Object object) throws Exception {
        // 1.使用反射机制，获取当前类的所有属性
        Class<?> classInfo = object.getClass();
        Field[] declaredField = classInfo.getDeclaredFields();
        for (Field field : declaredField){
            NewResource newResource = field.getAnnotation(NewResource.class);
            if(newResource != null){
                String beanId = field.getName();
                Object bean = getBean(beanId);
                if(bean != null){
                    //允许访问私有属性
                    field.setAccessible(true);
                    field.set(object,bean);
                }
            }

        }
    }

}
