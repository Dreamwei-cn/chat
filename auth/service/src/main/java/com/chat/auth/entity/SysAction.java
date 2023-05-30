package com.chat.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_action
 */
@TableName(value ="sys_action")
@Data
public class SysAction implements Serializable {
    /**
     * 
     */
    @TableId(value = "actionId", type = IdType.AUTO)
    private Long actionid;

    /**
     * 
     */
    @TableField(value = "action")
    private String action;

    /**
     * 
     */
    @TableField(value = "describe")
    private String describe;

    /**
     * 
     */
    @TableField(value = "default_check")
    private Integer defaultCheck;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysAction other = (SysAction) that;
        return (this.getActionid() == null ? other.getActionid() == null : this.getActionid().equals(other.getActionid()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getDescribe() == null ? other.getDescribe() == null : this.getDescribe().equals(other.getDescribe()))
            && (this.getDefaultCheck() == null ? other.getDefaultCheck() == null : this.getDefaultCheck().equals(other.getDefaultCheck()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActionid() == null) ? 0 : getActionid().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getDescribe() == null) ? 0 : getDescribe().hashCode());
        result = prime * result + ((getDefaultCheck() == null) ? 0 : getDefaultCheck().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", actionid=").append(actionid);
        sb.append(", action=").append(action);
        sb.append(", describe=").append(describe);
        sb.append(", defaultCheck=").append(defaultCheck);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}