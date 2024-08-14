package com.myTemplateProject.models.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserVo {

//    @JSONField(serializeUsing = ToStringSerializer.class)
    Long userId;

    String userName;

    Boolean isOnline;

    Date lastOnLineTime;

}
