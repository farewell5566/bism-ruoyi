package com.bism.common.core.utils;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpringUtils implements BeanFactoryPostProcessor {

    private  static ConfigurableListableBeanFactory beanFactory ;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        SpringUtils.beanFactory = configurableListableBeanFactory;
    }

    public static <T> T getBean(String name){
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return (T)beanFactory.getBean(clazz);
    }



}
