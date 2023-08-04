package pers.wmx.test.spring;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class ClassUtil {
     /**
       * <h2>根据传入的包名获取 包下面的class对象集合</h2>
       * @param packageName {@link String}
       * @return 类集合
       */
    public static Set<Class<?>> extractPackageClass(String packageName) throws Exception{//Class<?>的问号表示可以存储任意类型的类，加不加问号都可以
        // 获取到类加载器
        ClassLoader classLoader=getClassLoader();
        // 获取到url
        URL url= classLoader.getResource(packageName.replace(".", "/"));
        if (url==null){
            return null;
        }
        Set<Class<?>> classSet=null;
        if (url.getProtocol().equals("file")){//获取到协议，并判断是不是file协议类型
            classSet=new HashSet<Class<?>>();
            // url.getPath()  /E:/YouCan/minispring/target/classes/com/miniframework/core/annotation
            File file=new File(url.getPath());
            // url.getPath()获取到用户传入的包名文件所处的系统中的绝对路径后，我们要做的就是把所有class文件加载到Set中
            getExtractClassSet(file,classSet,packageName);

        }

        return classSet;
    }
     /**
       * <h2>获取目标路径下所有的class文件和子文件夹下面的class文件</h2>
       * @param fileSource,classSet,packageName
       * @return
       */
    private static void getExtractClassSet(File fileSource, final Set<Class<?>> classSet, final String packageName){
        if (!fileSource.isDirectory()){// 如果是传入的不是是文件夹直接返回
            return;
        }
        // 如果是文件夹，那么我们就获取当前文件夹下面的所有class文件
        File file[]=fileSource.listFiles(new FileFilter() {// listFiles获取当前文件夹下面的所有文件或者文件夹
            // 在这里我们只关心所有的子文件夹和文件
            public boolean accept(File dir) {
                if (dir.isDirectory()){
                    return true;// 如果是文件夹，那么便是我们所需要的
                }else {// 那么就是文件
                    // 筛选出所有class文件
                    String abslutFilePath=dir.getAbsolutePath();// 获取文件的绝对值路径
                    if (abslutFilePath.endsWith(".class")){// 判断是不是class文件
                        addClassToSet(abslutFilePath);
                    }
                }
                return false;// 返回false表示其他文件不是我们所需要的
            }
            // 根据文件的绝对路径获取并创建class对象存入classSet
            private void addClassToSet(String abslutFilePath) {
              //1 从绝对路径中获取包含有package的类名
                // 比如 /E:/YouCan/minispring/target/classes/com/miniframework/core/annotation/test.class -->com.miniframework.core.annotation.test
                abslutFilePath = abslutFilePath.replace(File.separator, ".");// 把不同系统的绝对路径中的/更换为.
                String substring = abslutFilePath.substring(abslutFilePath.indexOf(packageName));
                String classname = substring.substring(0, substring.lastIndexOf("."));
                Class<?> clazz = getClassUsePath(classname);
                classSet.add(clazz);
                //2 通过反射获取到类，并创建对象存入set
            }
        });
        // 如果当前文件夹下还有文件夹 递归调用该方法继续查找
        if (file!=null){ // 这里为什么要用if，因为foeeach的时候如果file为空，会报错
            for (File e:file){
                getExtractClassSet(e,classSet,packageName);
            }
        }
    }

     /**
       * <h2>通过传入的参数获取class对象</h2>
       * @param classname package+className‘
       * @return class 对象
       */
    private static Class<?> getClassUsePath(String classname){
        Class clazz=null;
        try {
           clazz=Class.forName(classname);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException("获取class失败");
        }
        return clazz;
    }


    /**
       * <h2>获取当前类加载器</h2>
       * @return 类加载器
       */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = getClassLoader();
        System.out.println(extractPackageClass("com.miniframework.core.annotation.utils"));
    }
}
