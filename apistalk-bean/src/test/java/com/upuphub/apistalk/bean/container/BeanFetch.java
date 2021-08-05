package com.upuphub.apistalk.bean.container;


import org.junit.jupiter.api.Test;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2021-08-05 21:35
 **/
public class BeanFetch {

    @Test
    public void fetch(){
        BeanContainer.getBeanInstanceByClazz(Bean.class);
    }

}
