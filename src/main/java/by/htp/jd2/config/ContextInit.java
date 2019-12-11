package by.htp.jd2.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ContextInit {


    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private ContextInit() {
    }

    public static Object getContextBean(Class<?> bean) {

        return context.getBean(bean);
    }


}
