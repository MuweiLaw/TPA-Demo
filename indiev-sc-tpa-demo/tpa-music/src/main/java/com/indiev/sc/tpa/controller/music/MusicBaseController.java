package com.indiev.sc.tpa.controller.music;

import com.indiev.sc.common.result.Result;
import com.indiev.sc.tpa.business.service.MusicBaseService;
import com.indiev.sc.tpa.controller.BaseController;
import com.indiev.sc.tpa.domain.pojo.SongBasePojo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Murray Law
 * @date 2022/8/31 17:42
 */
@RestController
@RequestMapping("/music")
public class MusicBaseController extends BaseController {
    private final MusicBaseService musicBaseService;

    public MusicBaseController(MusicBaseService musicBaseService) {
        this.musicBaseService = musicBaseService;
    }

    @GetMapping("/{songId}")
    public Result<SongBasePojo> getBySongId(@PathVariable("songId")String songId) {
        return musicBaseService.getBySongId(songId);
    }
}
