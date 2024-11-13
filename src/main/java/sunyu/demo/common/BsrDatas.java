package sunyu.demo.common;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Component;
import sunyu.demo.entity.TwNrvRedundant;
import sunyu.demo.service.TwNrvRedundantService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BsrDatas {
    Log log = LogFactory.get();
    long tenantId = 115;//钵施然

    @Resource
    TwNrvRedundantService twNrvRedundantService;

    Map<String, TwNrvRedundant> redundantMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        ThreadUtil.execute(() -> {
            do {
                List<TwNrvRedundant> twNrvRedundants = twNrvRedundantService.list(
                        Wrappers.lambdaQuery(TwNrvRedundant.class)
                                .select(TwNrvRedundant::getVin, TwNrvRedundant::getDid, TwNrvRedundant::getVehicleModel, TwNrvRedundant::getImei, TwNrvRedundant::getOrgNamePath)
                                .eq(TwNrvRedundant::getTenantId, tenantId)
                                .isNotNull(TwNrvRedundant::getDid)
                );
                log.info("钵施然共有 {} 车辆信息", twNrvRedundants.size());
                for (TwNrvRedundant twNrvRedundant : twNrvRedundants) {
                    redundantMap.put(twNrvRedundant.getDid(), twNrvRedundant);
                }
                ThreadUtil.sleep(1000 * 60 * 60);
            } while (true);
        });
    }

    public Map<String, TwNrvRedundant> getRedundantMap() {
        return redundantMap;
    }


}
