package com.mouse.mapper;

import com.mouse.pojo.Moment;

import java.util.List;

public interface MomentMapper {

    /**
     * 分享歌曲到社区
     * @param moment
     * @return
     */
    int addMoment(Moment moment);

    List<Moment> searchAllMoment();
}
