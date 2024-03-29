package zhd.controllerTests;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.TypeUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zhd.BssTestEnvironment;
import com.zhd.enums.DepositTypeEnum;
import com.zhd.enums.RechargeTypeEnum;
import com.zhd.pojo.*;
import com.zhd.service.IJourneyService;
import com.zhd.util.Constants;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.*;
import static com.alibaba.fastjson.JSON.toJSONString;
import static org.springframework.http.MediaType.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * mockMVc测试类，用于测试各种接口
 */
public class MockTest extends BssTestEnvironment {

    @Autowired
    private IJourneyService journeyService;

    @Test
    public void testLogin() throws Exception{
        User adminUser = User.builder().id("23456789011").password("789011").build();
        mockMvc.perform(post("/login").contentType(APPLICATION_JSON).content(toJSONString(adminUser))).andDo(print());
    }

    @Test
    public void testLogout() throws Exception{
        mockMvc.perform(get("/logout")).andDo(print()).andReturn();
    }

    @Test
    public void testRecharge() throws Exception{
        Recharge recharge = Recharge.builder().userId("23456789011").type(RechargeTypeEnum.ALIPAY.getCode()).amount(BigDecimal.valueOf(50)).build();
        mockMvc.perform(post("/recharge").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(recharge))).andDo(print()).andReturn();
    }

    @Test
    public void testRecharges() throws Exception{
        mockMvc.perform(get("/recharge/list/1").sessionAttr("userid","23456789011")).andDo(print()).andReturn();
    }

    @Test
    public void testInDeposit() throws Exception{
        Deposit deposit = Deposit.builder().type(DepositTypeEnum.IN.getCode()).amount(Constants.STANDARD_DEPOSIT).build();
        mockMvc.perform(post("/deposit").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(deposit)).sessionAttr("userid","23456789011")).andDo(print()).andReturn();
    }

    @Test
    public void testOutDeposit() throws Exception{
        Deposit deposit = Deposit.builder().type(DepositTypeEnum.OUT.getCode()).amount(Constants.STANDARD_DEPOSIT).build();
        String content = JSON.toJSONString(deposit);
        mockMvc.perform(post("/deposit").contentType(MediaType.APPLICATION_JSON).content(content).sessionAttr("userid","23456789011")).andDo(print()).andReturn();
    }

    @Test
    public void testBorrowBicycle() throws Exception{
        Bicycle bicycle = Bicycle.builder().build();
        String content = JSON.toJSONString(bicycle);
        mockMvc.perform(get("/bicycles/borrow/394").contentType(MediaType.APPLICATION_JSON).content(content).sessionAttr("userid","11223344571")).andDo(print()).andReturn();
    }

    @Test
    public void testReturnBicycle() throws Exception {
        Bicycle bicycle = bicycleService.selectById("466");
        City currentCity = cityService.selectById(bicycle.getCityId());
        City parentCity = cityService.selectById(currentCity.getParentId());
        List<City> cities = cityService.selectList(new EntityWrapper<City>().eq("parent_id", parentCity.getParentId()));
        List<Integer> cityIds = new ArrayList<>();
        for (City city : cities) {
            cityIds.add(city.getId());
        }
        List<Area> areas = areaService.selectList(new EntityWrapper<Area>().in("city_id", cityIds));
        System.out.println("areas-->" + areas);
        Area area = areas.get(RandomUtils.nextInt(0, areas.size()));
        System.out.println("selectedArea-->" + area);
        BigDecimal endLocationX = BigDecimal.valueOf(RandomUtils.nextDouble(area.getWestPoint().doubleValue(), area.getEastPoint().doubleValue()));
        BigDecimal endLocationY = BigDecimal.valueOf(RandomUtils.nextDouble(area.getSouthPoint().doubleValue(), area.getNorthPoint().doubleValue()));

//        LocalDateTime startTimeValue = LocalDateTime.now();
        Journey formerJourney = journeyService.getContinuedJourneys("11223344843").get(0);
        LocalDateTime startTimeValue =  LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(formerJourney.getStartTime())), ZoneId.of("Asia/Shanghai"));
        long rideTimeValue = RandomUtils.nextLong(0, 3600);
        LocalDateTime endTimeValue = startTimeValue.plusSeconds(rideTimeValue);
        String endTime = TypeUtils.castToString(endTimeValue.toEpochSecond(ZoneOffset.ofHours(8)));
        Journey journey = Journey.builder().endLocationX(endLocationX).endLocationY(endLocationY).endTime(endTime).build();
        String content = JSON.toJSONString(journey);
        mockMvc.perform(post("/bicycles/return/" + bicycle.getId()).contentType(MediaType.APPLICATION_JSON).content(content).sessionAttr("userid","11223344843")).andDo(print()).andReturn();
    }




    @Test
    @Rollback(false)
    public void test() throws Exception{
        long start = System.currentTimeMillis();
        List<Journey>  journeyList = journeyService.selectList(new EntityWrapper<Journey>());
        for (int i = 0; i < journeyList.size() ; i++) {
            Journey journey = journeyList.get(i);
//            Integer startCityCode = RegeoUtil.getCityByLocation(journey.getStartLocationX(),journey.getStartLocationY()).getJSONObject("addressComponent").getInteger("adcode");
//            Integer startCityId = cityService.selectOne(new EntityWrapper<City>().eq("code", startCityCode)).getId();
//            Integer endCityCode = RegeoUtil.getCityByLocation(journey.getEndLocationX(),journey.getEndLocationY()).getJSONObject("addressComponent").getInteger("adcode");
//            Integer endCityId = cityService.selectOne(new EntityWrapper<City>().eq("code", endCityCode)).getId();
//            Journey newJourney = Journey.builder().id(journey.getId()).endCity(endCityId).build();
//            journeyService.updateById(newJourney);
//            System.out.println(newJourney.getId() +  "---" + newJourney.getEndCity());
            Journey newJourney = Journey.builder().id(journey.getId()).distanceRound((int)Math.floor(journey.getDistance()/ 1000) + 1).build();
            journeyService.updateById(newJourney);
//            Thread.sleep(10);
        }
        long end = System.currentTimeMillis();
        System.out.println("->" + (end - start));

    }











}
