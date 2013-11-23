package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanHelper {

    private static ApplicationContext CONTEXT;

    private BeanHelper()
    {
        // no instance
    }

    public static Object getBean(String beanName)
    {

        synchronized (BeanHelper.class)
        {

            if (CONTEXT == null)
            {
                CONTEXT = new ClassPathXmlApplicationContext("spring.xml");
            }
        }

        return CONTEXT.getBean(beanName);
    }
}
