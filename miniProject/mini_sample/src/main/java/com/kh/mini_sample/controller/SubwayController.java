package com.kh.mini_sample.controller;


import com.kh.mini_sample.Dao.CalenderDao;
import com.kh.mini_sample.Dao.TimetalbeDao;
import com.kh.mini_sample.service.MinimumTimeService;
import com.kh.mini_sample.service.MinimumTransferService;
import com.kh.mini_sample.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SubwayController {
    @Autowired
    StationsVO stationsVO;
    @Autowired
    MinimumTimeService minimumTimeService;
    @Autowired
    MinimumTransferService minimumTransferService;

    @Autowired
    private CalenderDao calenderDao;
    @Autowired
    private TimetalbeDao timetalbeDao;

    @GetMapping("/path/")
    public List<PathVO> findPath(@RequestParam String start , String end) throws ParseException, SQLException {
        StationVO []stations = stationsVO.getStations();
        ArrayList<NodeDistVO> []graphDist = stationsVO.getGraphDist();
        ArrayList<NodeTransVO> []graphTrans = stationsVO.getGraphTrans();
        List<Boolean>whatDay = calenderDao.getTimetable();
        PathVO transfer = minimumTransferService.Dijkstra(start,end,stations,graphTrans,whatDay);
        PathVO miniTime = minimumTimeService.Dijkstra(start,end,stations,graphDist,whatDay);
        List<PathVO> list = new ArrayList<>();
        list.add(transfer);
        list.add(miniTime);
        return list;
    }

    @GetMapping("/timetable/")
    public String findTimetable(@RequestParam int id) throws SQLException {
        StationVO [] stations = stationsVO.getStations();
        List<Boolean>whatDay = calenderDao.getTimetable();
        String dayNm = whatDay.get(0) ? "휴일" : "평일";
        return timetalbeDao.getTimetable(id,dayNm);
    }
}
