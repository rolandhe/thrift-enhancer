package com.github.rolandhe.thrift.enhancer.stuff.pojo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * 指明list泛型元素的类型，同时可以指定list使用哪个实现(在反序列化时使用)。<br>
 *
 * <p>java的泛型只是语法糖，在编译完成后会丢失泛型类型，因此运行时不能获取泛型类型，
 * 需要使用注解来说明</p>
 *
 * @author rolandhe
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListContainer {

  Class<?> value();

  /**
   * list的实现类型
   *
   * @return
   */
  Class<? extends List> realListClass() default ArrayList.class;
}
