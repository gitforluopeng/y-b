package com.lp.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {

    private static final long serialVersionUID = -1307844635850275300L;

    private Long count;

    private List<T> list;

    private Integer pages;

    public Map<String, Object> toMap() {
        return cn.hutool.core.bean.BeanUtil.beanToMap(this);
    }

}
