package com.wh.file.utils;import tk.mybatis.mapper.common.BaseMapper;import tk.mybatis.mapper.common.ConditionMapper;import tk.mybatis.mapper.common.IdsMapper;import tk.mybatis.mapper.common.Mapper;import tk.mybatis.mapper.common.special.InsertListMapper;public interface MyBaseMapper<T> extends Mapper<T>, ConditionMapper<T>, IdsMapper<T>, InsertListMapper<T>, BaseMapper<T> {}