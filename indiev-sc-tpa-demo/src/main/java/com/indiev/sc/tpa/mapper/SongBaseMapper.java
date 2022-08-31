package com.indiev.sc.tpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.indiev.sc.tpa.domain.SongBasePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Murray Law
 */
@Mapper
public interface SongBaseMapper extends BaseMapper<SongBasePojo> {
    /**
     * 分页获取歌曲基础信息
     *
     * @param page 分页参数
     * @return com.baomidou.mybatisplus.core.metadata.IPage
     * @date 2022/8/31 17:56
     */
    IPage<SongBasePojo> getPageSongBase(IPage<SongBasePojo> page);

    /**
     * 根据歌曲ID查询
     *
     * @param songId 歌曲ID
     * @return SongBasePojo
     * @date 2022/8/31 16:51
     */
    @Select("SELECT `song_id`, `song_name`, `song_id`, `image_url`, `song_url`, `file_type` from song_base WHERE song_id = #{songId}")
    SongBasePojo getBySongId(@Param("songId") String songId);

}