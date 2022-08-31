package com.indiev.sc.tpa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.indiev.sc.common.result.Result;
import com.indiev.sc.tpa.domain.SongBasePojo;
import com.indiev.sc.tpa.mapper.SongBaseMapper;
import org.springframework.stereotype.Service;

/**
 * @author Murray Law
 * @date 2022/8/31 14:55
 */
@Service
public class MusicBaseService extends ServiceImpl<SongBaseMapper, SongBasePojo> {

    public IPage<SongBasePojo> getPageSongBase(IPage<SongBasePojo> queryMap) {
        return baseMapper.getPageSongBase(queryMap);
    }

    public Result<SongBasePojo> getBySongId(String songId) {
        return Result.data(baseMapper.getBySongId(songId));
    }

}
