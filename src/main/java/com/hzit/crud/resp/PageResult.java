package com.hzit.crud.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * data
 * @param <T>
 */
@Data
public class PageResult<T> implements Serializable {

    long count;

    String code;

    String msg;

    List<T> data;



}
