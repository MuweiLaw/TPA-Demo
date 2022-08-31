package com.indiev.sc.tpa.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Murray Law
 * @date 2022/8/31 16:36
 */
@Data
public class SongBasePojo implements Serializable {

    private static final long serialVersionUID = 7976469321244708595L;
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 歌曲ID
     */
    private String songId;

    /**
     * 歌曲名称
     */
    private String songName;

    /**
     * 歌曲海边路径
     */
    private String imageUrl;

    /**
     * 歌曲资源路径
     */
    private String songUrl;

    /**
     * 自增ID
     */
    private String fileType;

}
