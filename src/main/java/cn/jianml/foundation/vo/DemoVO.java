package cn.jianml.foundation.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Demo对象
 *
 * @author wujian
 * @date 2022年01月21日
 */
@Data
public class DemoVO {
    @NotNull
    private Integer age;

    @Length(max = 10)
    @NotNull
    private String name;
}
