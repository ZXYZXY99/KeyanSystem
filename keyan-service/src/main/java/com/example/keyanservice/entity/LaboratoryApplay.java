package com.example.keyanservice.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LaboratoryApplay implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String applayLaboratoryNum;

    private String applayLaboratoryUser;

    private String ispass;

    private String reason;


}
