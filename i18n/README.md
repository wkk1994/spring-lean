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