package com.example.autoservice.initdefaultdata;

import com.example.autoservice.customer.CustomerMapper;
import com.example.autoservice.customer.CustomerRepository;
import com.example.autoservice.servicetype.ServiceTypeInitData;
import com.example.autoservice.servicetype.ServiceTypeRepository;
import com.example.autoservice.vehicle.VehicleMapper;
import com.example.autoservice.vehicle.VehicleRepository;
import java.lang.Class;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link InitData}.
 */
public class InitData__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'initData'.
   */
  private static BeanInstanceSupplier<InitData> getInitDataInstanceSupplier() {
    return BeanInstanceSupplier.<InitData>forConstructor(CustomerRepository.class, CustomerMapper.class, VehicleRepository.class, ServiceTypeRepository.class, VehicleMapper.class, ServiceTypeInitData.class)
            .withGenerator((registeredBean, args) -> new InitData(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'initData'.
   */
  public static BeanDefinition getInitDataBeanDefinition() {
    Class<?> beanType = InitData.class;
    RootBeanDefinition beanDefinition = new RootBeanDefinition(beanType);
    beanDefinition.setInitMethodNames("initDefaultData");
    beanDefinition.setInstanceSupplier(getInitDataInstanceSupplier());
    return beanDefinition;
  }
}
