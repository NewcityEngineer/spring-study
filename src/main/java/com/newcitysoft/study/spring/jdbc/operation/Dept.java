package com.newcitysoft.study.spring.jdbc.operation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/11 10:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dept {
    private String code;
    private String name;
}
