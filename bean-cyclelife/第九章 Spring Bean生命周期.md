# Spring Bean生命周期

## Spring Bean元信息配置阶段

BeanDefinition的配置方式：

* 面向资源
  * XML配置方式：直接通过xml定义bean信息`<bean > </bean>`
  * properties资源的配置方式：和xml的配置类似，也需要手动加载配置文件，只不过配置文件的格式不同。
* 面向注解：使用@Bean、@Component、@Configuration等方式声明的bean。
* 面向API：[ApiregistryBeanDefinitionDemo.java](https://github.com/wkk1994/spring-ioc-learn/blob/master/spring-bean/src/main/java/com/wkk/learn/spring/ioc/bean/definition/ApiregistryBeanDefinitionDemo.java)

## Spring Bean元信息的解析阶段

* 面向资源 BeanDefinition 解析
  * BeanDefinitionReader
  * XML 解析器 - BeanDefinitionParser
* 面向注解 BeanDefinition 解析
  * AnnotatedBeanDefinitionReader：该类是注解BeanDefinition解析的类，是单独实现的并没有实现`BeanDefinitionReader`，因为注解BeanDefinition的解析不需要从文件中获取，所以单独实现，AnnotatedBeanDefinitionReader的默认的Bean名称生成器是`AnnotationBeanNameGenerator`，可以通过`AnnotatedBeanDefinitionReader#setBeanNameGenerator`方法设置自定义的Bean名称生成器。

## Spring Bean元信息注册

Spring BeanDefinition的注册接口为`BeanDefinitionRegistry`，`DefaultListableBeanFactory`实现了该接口。注册方法为`DefaultListableBeanFactory#registerBeanDefinition`。

注册过程：

* BeanDefinition信息的校验；
* beanDefinitionMap是否已经存在了指定beanName的BeanDefinition信息；
* 已经存在的话检查是否可以覆盖`isAllowBeanDefinitionOverriding`，可以覆盖的话就直接put到beanDefinitionMap中；不能覆盖抛出BeanDefinitionOverrideException异常；
* 不存在时，检查BeanFactory是否在创建Bean实例过程中，如果是则需要加锁（beanDefinitionMap）进行put操作，不是直接对beanDefinitionMap进行put操作。在这个put操作后还会对beanDefinitionNames进行add操作，将beanName放到list中；
* 如果beanName的元信息已经存在，并且已经实例化完成，则重新设置BeanDefinition的信息。

> beanDefinitionMap使用ConcurrentHashMap实现，是线程安全的，但是如果BeanFactory是在初始化bean实例过程中对beanDefinitionMap进行put操作，可能导致BeanFactory在其他线程中正在遍历beanDefinitionMap的操作失败，所以在对beanDefinitionMap进行put操作时候会加锁。
> beanDefinitionNames的作用：因为beanDefinitionMap是无序的，所以通过一个FIFO的list保存beanName声明的顺序。

