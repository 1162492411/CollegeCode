package com.zhd.controller;

import com.zhd.convert.JourneyReportConvert;
import com.zhd.convert.TaskReportConvert;
import com.zhd.pojo.JSONResponse;
import com.zhd.service.ICityService;
import com.zhd.service.IJourneyReportService;
import com.zhd.service.ITaskReportService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 报表控制器
 *
 * @Author zyg
 */
@RestController
@RequestMapping("report")
public class ReportController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private IJourneyReportService journeyReportService;
    @Autowired
    private ITaskReportService taskReportService;
    @Autowired
    private ICityService cityService;

    @RequestMapping(value = "overview", method = RequestMethod.POST)
    public JSONResponse overview(@RequestBody Map<String, Object> params) {
        try {
            String chartType = String.valueOf(params.get("chartType"));
            if (StringUtils.isBlank(chartType)) {
                chartType = "line";
            }
            Integer statisticalType = (Integer) params.get("statisticalType");
            Integer groupType = (Integer) params.get("groupType");
            Object cityIdValue = params.get("cityId");
            Integer cityId = 0;
            String cityName = "";
            if (cityIdValue != null) {
                cityId = (Integer) cityIdValue;
                cityName = cityService.selectById(cityId).getName();
            }
            String startDate = String.valueOf(params.get("startDate"));
            String endDate = String.valueOf(params.get("endDate"));
            if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
                LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
                if (end.isAfter(start)) {
                    switch (statisticalType) {
                        case 0:
                            return renderSuccess(JourneyReportConvert.convertOverview(journeyReportService.countUseCount(startDate, endDate, cityId, groupType), cityName, start, end, chartType, groupType));
                        case 1:
                            return renderSuccess(JourneyReportConvert.convertRideTime(journeyReportService.countRideTime(startDate, endDate, cityId), cityName, chartType));
                        case 2:
                            return renderSuccess(JourneyReportConvert.convertRideDistance(journeyReportService.countRideDistance(startDate, endDate, cityId), cityName, chartType));
                        case 3:
                            return renderSuccess(JourneyReportConvert.convertFlow(journeyReportService.countFlow(startDate, endDate, cityId, groupType), cityName, groupType));
                        default:
                            return renderError();
                    }
                } else {
                    return renderError();
                }
            } else {
                return renderError();
            }
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
    }

    @RequestMapping(value = "task", method = RequestMethod.POST)
    public JSONResponse task(@RequestBody Map<String, Object> params) {
        logger.debug("enter taskReport ,params is{}", params);
        try {
            String chartType = String.valueOf(params.get("chartType"));
            if (StringUtils.isBlank(chartType)) {
                chartType = "line";
            }
            Integer statisticalType = (Integer) params.get("statisticalType");
            Integer groupType = (Integer) params.get("groupType");
            Integer taskType = (Integer) params.get("taskType");
            Object cityIdValue = params.get("cityId");
            Integer cityId = 0;
            String cityName = "";
            if (cityIdValue != null) {
                cityId = (Integer) cityIdValue;
                cityName = cityService.selectById(cityId).getName();
            }
            String startDate = String.valueOf(params.get("startDate"));
            String endDate = String.valueOf(params.get("endDate"));
            if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {
                LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
                if (end.isAfter(start)) {
                    switch (statisticalType) {
                        case 1:
                            return renderSuccess(TaskReportConvert.convertTaskCount(taskReportService.countCount(startDate, endDate, cityId, taskType,groupType),cityName, chartType, groupType));
                        case 2:
                            return renderSuccess(TaskReportConvert.convertTaskTime(taskReportService.countByTime(startDate, endDate, cityId,taskType),cityName, chartType));
                        default:
                            return renderError();
                    }
                } else {
                    return renderError();
                }
            } else {
                return renderError();
            }
        } catch (Exception e) {
            return renderError(e.getMessage());
        }
    }

}
