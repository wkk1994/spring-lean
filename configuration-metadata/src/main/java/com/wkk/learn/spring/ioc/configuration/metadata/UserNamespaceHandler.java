package com.wkk.learn.spring.ioc.configuration.metadata;

import com.wkk.learn.spring.ioc.overview.dependency.model.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Description 解析user元素的 {@link NamespaceHandler} 实现
 * @Author Wangkunkun
 * @Date 2021/5/16 20:42
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }

    private static class UserBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

        @Override
        protected Class<?> getBeanClass(Element element) {
            return User.class;
        }

        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            String id = element.getAttribute("id");
            if(StringUtils.hasText(id)) {
                builder.addPropertyValue("id", id);
            }
            String name = element.getAttribute("name");
            if(StringUtils.hasText(name)) {
                builder.addPropertyValue("name", name);
            }
            String city = element.getAttribute("city");
            if(StringUtils.hasText(city)) {
                builder.addPropertyValue("city", city);
            }
        }
    }
}
