package zhd.controllerTests;

import com.alibaba.fastjson.JSON;
import com.zhd.BssTestEnvironment;
import org.junit.Test;

public class ServiceTest extends BssTestEnvironment {


    @Test
    public void test(){
//        System.out.println(JourneyReportConvert.convertOverviewByHour(journeyReportMapper.countByHour("2017-06-01","2017-06-15",0)));
//        System.out.println(journeyReportService.countByHour("2017-01-01","2018-01-01",0));

//        System.out.println(JourneyReportConvert.convertRideTime(journeyReportService.countRideTime("2017-01-01","2018-01-01",1049), Arrays.asList("测试城市")));
//        LocalDate start = LocalDate.parse("2017-02-26", DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalDate end = LocalDate.parse("2017-02-27", DateTimeFormatter.ISO_LOCAL_DATE);


//        System.out.println(JourneyReportConvert.convertRideDistance(journeyReportService.countRideDistance("2017-06-01","2017-06-15",0), Arrays.asList("ceshi")));

//        System.out.println(JourneyReportConvert.convertFlow(journeyReportService.countFlowByDay("2017-06-01","2017-06-30",1049),Arrays.asList("ceshi"),"line"));

//        System.out.println(cityService.getAllChildren(907));

//        System.out.println(JSON.toJSONString(cityService.getNextLevelChildren(907)));
        System.out.println(JSON.toJSONString(journeyReportService.countUseCountByCity("2017-06-01","2018-07-01",1)));


    }



}
