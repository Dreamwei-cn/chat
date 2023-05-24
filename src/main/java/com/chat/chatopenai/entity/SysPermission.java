package com.chat.chatopenai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_permission
 */
@TableName(value ="sys_permission")
@Data
public class SysPermission implements Serializable {
    /**
     * 
     */
    @TableId(value = "permissionId")
    private Long permissionid;

    /**
     * 
     */
    @TableField(value = "action")
    private String action;

    /**
     * 
     */
    @TableField(value = "action_name")
    private String actionName;

    /**
     * 
     */
    @TableField(value = "action_class")
    private String actionClass;

    /**
     * 
     */
    @TableField(value = "action_method")
    private String actionMethod;

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
        SysPermission other = (SysPermission) that;
        return (this.getPermissionid() == null ? other.getPermissionid() == null : this.getPermissionid().equals(other.getPermissionid()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getActionName() == null ? other.getActionName() == null : this.getActionName().equals(other.getActionName()))
            && (this.getActionClass() == null ? other.getActionClass() == null : this.getActionClass().equals(other.getActionClass()))
            && (this.getActionMethod() == null ? other.getActionMethod() == null : this.getActionMethod().equals(other.getActionMethod()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPermissionid() == null) ? 0 : getPermissionid().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getActionName() == null) ? 0 : getActionName().hashCode());
        result = prime * result + ((getActionClass() == null) ? 0 : getActionClass().hashCode());
        result = prime * result + ((getActionMethod() == null) ? 0 : getActionMethod().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", permissionid=").append(permissionid);
        sb.append(", action=").append(action);
        sb.append(", actionName=").append(actionName);
        sb.append(", actionClass=").append(actionClass);
        sb.append(", actionMethod=").append(actionMethod);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}