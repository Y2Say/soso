package com.just.soso.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by user on 2017/3/20.
 */
@Entity
@Table(name = "function")
@NamedQuery(name = "Functions.findAll", query = "SELECT f FROM Functions f")
public class Functions implements Serializable{
    private static final long serialVersionUID = -1493337039445593406L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private Integer parentId;
    private Integer serialNum;
    private String accordion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public String getAccordion() {
        return accordion;
    }

    public void setAccordion(String accordion) {
        this.accordion = accordion;
    }
}
