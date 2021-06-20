# Spring国际化

## Spring国际化的使用场景

* 普通国际化场景：主要使用Spirng国际化的场景获取文本文案，使用比较少。
* Bean Validation：校验国际化文案。
* Web站点页面渲染：根据不同的语言对站点的内容进行本地化的展示。
* Web MVC错误小心的显示：比如在不同的地区或者语言下，对错误信息做本地化的展示。

## Spring国际化接口

Spring国际化的核心接口：`org.springframework.context.MessageSource`，它有两个开箱即用的实现类，分别是：`org.springframework.context.support.ResourceBundleMessageSource`和`org.springframework.context.support.ReloadableResourceBundleMessageSource`。

与接口相关的主要概念有：

* 文案模板编码（code）
* 文案模板参数（args）
* 区域（Locale）

## 层次性的MessageSource

Spring层次性接口回顾：
Spring中提供了很多层次性接口的实现，之间有提到的有：

* org.springframework.beans.factory.HierarchicalBeanFactory

  HierarchicalBeanFactory中提供了getParentBeanFactory方法可以获取到父BeanFactory。

* org.springframework.context.ApplicationContext

  ApplicationContext提供了getParent方法，可以获取到父ApplicationContext。

* org.springframework.beans.factory.config.BeanDefinition

  BeanDefinition提供了getParentName方式可以获取父ParentName。

Spring层次性国际化接口：

Spring国际化接口也提供了层次性的实现，实现类：`org.springframework.context.HierarchicalMessageSource`，它提供了getParentMessageSource方法可以获取到父MessageSource。

> 双亲委派：和Java的ClassLoader的双亲委派机制不同，Spring实现的双亲机制，是从下向上查找。双亲委派并没有规定一定要由上至下查找。