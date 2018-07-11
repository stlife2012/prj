package com.cloud.web.common.data;

import com.cloud.web.common.data.mapper.LicenceMapper;
import com.cloud.web.common.data.pojo.LicenceObj;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LicenceHandlerService {
    @Resource
    private LicenceMapper licenceMapper;

    /**
     * 获取LC基本信息
     * @param uuid
     * @param isValid
     * @return
     */
    public LicenceObj loadLicence(String uuid,Boolean isValid){
        if(null == isValid || isValid.equals(false)){
            return licenceMapper.selectByPrimaryKey(uuid);
        }else{
            return licenceMapper.loadValidateLicence(uuid);
        }


    }

    public void accessLicence(LicenceObj obj){
        obj.setUsedNum(obj.getUsedNum() + 1);
        licenceMapper.updateByPrimaryKey(obj);
    }

}
