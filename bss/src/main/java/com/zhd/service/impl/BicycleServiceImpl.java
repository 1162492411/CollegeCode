package com.zhd.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhd.enums.BicycleStatusEnum;
import com.zhd.exceptions.NoSuchBicycleException;
import com.zhd.exceptions.NotUseableBicycleException;
import com.zhd.mapper.BicycleMapper;
import com.zhd.pojo.Area;
import com.zhd.pojo.Bicycle;
import com.zhd.pojo.Journey;
import com.zhd.service.IAreaService;
import com.zhd.service.IBicycleService;
import com.zhd.service.IJourneyService;
import com.zhd.service.IUserService;
import com.zhd.util.Constants;
import com.zhd.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 车辆表 服务实现类
 * </p>
 *
 * @author zyg
 * @since 2018-02-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BicycleServiceImpl extends ServiceImpl<BicycleMapper, Bicycle> implements IBicycleService {

    @Autowired
    private BicycleMapper bicycleMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private IJourneyService journeyService;
    @Autowired
    private IAreaService areaService;

    @Override
    public Journey borrowBicycle(Bicycle bicycle, String userid) throws Exception {
        userService.checkDepositBalance(userid);
        userService.checkAccountBalance(userid);
        userService.checkCredit(userid);
        //check bicycle
        bicycle = this.selectById(bicycle.getId());
        if (bicycle == null) {
            throw new NoSuchBicycleException();
        }
        if (bicycle.getStatus() != BicycleStatusEnum.UNUSED.getCode()) {
            throw new NotUseableBicycleException();
        }
        //borrowBicycle
        bicycle.setStatus(BicycleStatusEnum.USING.getCode());
        Area area = areaService.findArea(bicycle.getLocationX(), bicycle.getLocationY());
//        Integer startCityCode = RegeoUtil.getCityByLocation(bicycle.getLocationX(),bicycle.getLocationY()).getJSONObject("addressComponent").getInteger("adcode");
//        Integer startCityId = cityService.selectOne(new EntityWrapper<City>().eq("code", startCityCode)).getId();
        Journey journey = Journey.builder().bicycleId(bicycle.getId()).userId(userid).startTime(DataUtil.generateRandomStartTimeString()).startLocationX(bicycle.getLocationX()).startLocationY(bicycle.getLocationY()).startArea(area.getId()).build();
        boolean result =  bicycleMapper.updateById(bicycle) > 0 && journeyService.insert(journey);
        if(result){
            return journey;
        }else{
            throw new Exception(Constants.TIP_BORROW_BICYCLE_ERROR);
        }
    }

    @Override
    public boolean returnBicycle(String userId, Journey journey) throws Exception {
        try{
//            JSONObject jsonObject = RegeoUtil.getCityByLocation(journey.getEndLocationX(), journey.getEndLocationY());
//            int adcode = Integer.valueOf(jsonObject.getJSONObject("addressComponent").getString("adcode"));
//            int cityId = cityService.selectOne(new EntityWrapper<City>().eq("code", adcode)).getId();
            journey.setDistanceRound(Math.round(journey.getDistance()));
            Bicycle bicycle = Bicycle.builder().id(journey.getBicycleId()).locationX(journey.getEndLocationX()).locationY(journey.getEndLocationY()).cityId(journey.getEndArea()).status(BicycleStatusEnum.UNUSED.getCode()).build();
            Bicycle bicycleInfo = Bicycle.builder().id(journey.getBicycleId()).serviceTime(journey.getRideTime()).mileage(journey.getDistance()).build();
            boolean result = bicycleMapper.updateById(bicycle) > 0 && bicycleMapper.updateInfo(bicycleInfo) && journeyService.updateById(journey) && userService.reduceAccount(userId, journey.getAmount());
            if(result){
                return true;
            }else{
                throw new Exception(Constants.TIP_RETURN_BICYCLE_ERROR);
            }
        }catch (Exception e){
            throw new Exception(Constants.TIP_RETURN_BICYCLE_ERROR);
        }
    }

    @Override
    public List<Bicycle> selectAllSimple() {
        return bicycleMapper.selectList(new EntityWrapper<Bicycle>().setSqlSelect("id,status,location_x,location_y"));
    }
}
