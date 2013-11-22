package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanHelper {

    private static ApplicationContext CONTEXT;
    
    private BeanHelper()
    {
        //no instance
    }

    public synchronized static Object getBean(String beanName) {
        
        if(CONTEXT == null)
        {
            CONTEXT = new ClassPathXmlApplicationContext("spring.xml");
        }
        
        return CONTEXT.getBean(beanName);
      }
}
