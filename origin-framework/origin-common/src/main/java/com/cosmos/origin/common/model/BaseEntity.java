package com.cosmos.origin.common.model;

import com.mybatisflex.annotation.Column;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体基类
 * 定义通用的实体字段，所有业务实体应继承此类
 *
 * @author 一陌千尘
 * @date 2025/02/05
 */
@Data
public abstract class BaseEntity {

    /**
     * 创建时间
     */
    @Column(onInsertValue = "CURRENT_TIMESTAMP")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Column(onInsertValue = "CURRENT_TIMESTAMP", onUpdateValue = "CURRENT_TIMESTAMP")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(onInsertValue = "false")
    private Boolean isDeleted;
}
