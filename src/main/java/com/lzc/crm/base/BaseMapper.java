package com.lzc.crm.base;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * BaseMapper  基本方法定义
 *
 * 乐字节：专注线上IT培训
 * 答疑老师微信：lezijie
 */
public interface BaseMapper<T,ID> {
    /**
     * 添加记录返回行数
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param entity
     * @return
     */
    public Integer insertSelective(T entity) throws DataAccessException;

    /**
     * 添加记录返回主键
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param entity
     * @return
     */
    public Integer insertHasKey(T entity) throws DataAccessException;

    /**
     * 批量添加
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param entities
     * @return
     */
    public Integer insertBatch(List<T> entities) throws DataAccessException;


    /**
     * 根据id 查询详情
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param id
     * @return
     */
    public T selectByPrimaryKey(ID id) throws DataAccessException;


    /**
     * 多条件查询
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param baseQuery
     * @return
     */
    public List<T> selectByParams(BaseQuery baseQuery) throws DataAccessException;


    /**
     * 更新单条记录
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param entity
     * @return
     */
    public Integer updateByPrimaryKeySelective(T entity) throws DataAccessException;


    /**
     * 批量更新
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param entities
     * @return
     */
    public Integer updateBatch(List<T> entities) throws DataAccessException;

    /**
     * 删除单条记录
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param id
     * @return
     */
    public Integer deleteByPrimaryKey(ID id) throws DataAccessException;

    /**
     * 批量删除
     *
     * 乐字节：专注线上IT培训
     * 答疑老师微信：lezijie
     * @param ids
     * @return
     */
    public Integer deleteBatch(ID[] ids) throws DataAccessException;


}
