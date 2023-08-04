package pers.wmx.test.spring;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
   * <h1>该类为容器类，线程安全，单例容器</h1>
   * @author  szw
   */
public class BeanContainer {
    // 定义存储所有被标记的目标对象的容器
    // 主要存储class和Object通过使用class利用反射创建出来的实例对象
    private final ConcurrentHashMap<Class<?>,Object> BEAN_MAP=new ConcurrentHashMap<Class<?>, Object>();

    // 用于存放我们的定义好的注解  List<Class<?extends Annotation>> 表示只能存放继承于Annotation的注解
    private List<Class<?extends Annotation>> ANNOTATION_LIST= Arrays.asList(
            Controller.class, Service.class, Component.class, Repository.class
    );// 只要是被这四个标签标记的，我们就要用BEAN_MAP来管理

    // 私有化无参构造函数
    private BeanContainer() {}

    // 提供外界能访问的获取beanContraner的实例的方法
     public BeanContainer getInstance(){
        return ContainerEnum.HOLDER.beanContainer;
     }

    // 私有的内部枚举类 用枚举类实现单例：为了防止用户通过反射和序列化对我们的单例造成破坏
     // 且线程安全
    private enum ContainerEnum{
        HOLDER;// 用于存放 BeanContainer实例
        private BeanContainer beanContainer;
        ContainerEnum(){
            beanContainer=new BeanContainer();
        }
    }
     /**
       * <h2>扫描并且加载所有被注解标记的bean</h2>
       * @param packageName 所要加载的包路径
       */
    public void loadBean(String packageName) throws Exception{
        // 使用我们之前创建的加载class的工具类加载该包路径下的所有class文件
        Set<Class<?>> classSet= ClassUtil.extractPackageClass(packageName);
        if (classSet==null||classSet.isEmpty()){
            return;// 如果没有加载到class那么直接返回，不用进行之后的过滤操作
        }
    }

}
