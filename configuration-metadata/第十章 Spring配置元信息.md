# Spring配置元信息(Configuration Metadata)

## Spring配置元信息

Spring配置元信息大致可以分为五类，分别是：

* Spring Bean配置元信息，代表的类是BeanDefinition；
* Spring 属性元信息，代表的类是PropertyValues
* Spring容器配置元信息，这类的元信息是没有API可以直接操作的，它是作用于IoC容器，可以将所有的配置元信息都看作是Spring容器的配置元信息。
* Spring外部化配置元信息，代表类PropertySource，这一类元信息是用来控制Spring的配置的来源，以及读取顺序的。
* Spring Profile元信息，Spring3.1新添加的，代表类@Profile，用来在不同环境激活不同的配置。
