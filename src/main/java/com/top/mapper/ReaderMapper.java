package com.top.mapper;

import com.top.pojo.Reader;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderMapper {

    /**
     * 插入读者信息
     * */
    int addAReader(@Param("addReader")Reader reader);

    /**g
     * 删除读者信息
     * */
    int deleteAReader(@Param("deleteReader")String rid);

    /**
     * 修改读者信息
     * */
    int modifyAReader(@Param("modifyReader")Reader reader);

    /**
     * 查询读者信息
     * */
    Reader getAReader(@Param("getReader")String rid);
}
