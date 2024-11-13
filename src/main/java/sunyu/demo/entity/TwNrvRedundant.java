package sunyu.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 非道路车辆冗余表
 * </p>
 *
 * @author SunYu
 * @since 2024-11-13
 */
@TableName("tw_nrv_redundant")
public class TwNrvRedundant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    @TableId("vehicle_id")
    private Long vehicleId;

    /**
     * 租户ID
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * VIN码
     */
    @TableField("vin")
    private String vin;

    /**
     * 车牌号
     */
    @TableField("lpn")
    private String lpn;

    /**
     * 机械环保代码类型 0.不支持广播 1.支持广播
     */
    @TableField("mein_type")
    private Byte meinType;

    /**
     * 机械环保代码
     */
    @TableField("mein")
    private String mein;

    /**
     * 机械环保代码动态获取
     */
    @TableField("mein_dynamic")
    private String meinDynamic;

    /**
     * 发动机品牌ID
     */
    @TableField("engine_brand_id")
    private Long engineBrandId;

    /**
     * 发动机品牌名称
     */
    @TableField("engine_brand_name")
    private String engineBrandName;

    /**
     * 发动机型号ID
     */
    @TableField("engine_model_id")
    private Long engineModelId;

    /**
     * 发动机型号名称
     */
    @TableField("engine_model_name")
    private String engineModelName;

    /**
     * 发动机号
     */
    @TableField("engine_serial_number")
    private String engineSerialNumber;

    /**
     * 通信协议（CAN协议号）ID
     */
    @TableField("comm_protocol_id")
    private Long commProtocolId;

    /**
     * 通信协议（CAN协议号）编码 根据车厂不同而不同，由字典维护
     */
    @TableField("comm_protocol_code")
    private String commProtocolCode;

    /**
     * 通信协议（CAN协议号）名称 根据车厂不同而不同，由字典维护
     */
    @TableField("comm_protocol_name")
    private String commProtocolName;

    /**
     * 反应剂存储罐容积（L）
     */
    @TableField("rs_tank_capacity")
    private Double rsTankCapacity;

    /**
     * 油箱容积（L）
     */
    @TableField("fuel_tank_capacity")
    private Double fuelTankCapacity;

    /**
     * 发动机最大基准扭矩（Nm）
     */
    @TableField("engine_torque")
    private Double engineTorque;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 车型ID
     */
    @TableField("vehicle_model_id")
    private Long vehicleModelId;

    /**
     * 机械类型 1.农业机械 2.工程机械
     */
    @TableField("machine_type")
    private Byte machineType;

    /**
     * 产品大类代码
     */
    @TableField("category_a_code")
    private String categoryACode;

    /**
     * 产品大类名称
     */
    @TableField("category_a_name")
    private String categoryAName;

    /**
     * 产品小类代码
     */
    @TableField("category_b_code")
    private String categoryBCode;

    /**
     * 产品小类名称
     */
    @TableField("category_b_name")
    private String categoryBName;

    /**
     * 品目代码
     */
    @TableField("category_c_code")
    private String categoryCCode;

    /**
     * 品目名称
     */
    @TableField("category_c_name")
    private String categoryCName;

    /**
     * 车辆名称ID
     */
    @TableField("vehicle_name_id")
    private Long vehicleNameId;

    /**
     * 车辆名称
     */
    @TableField("vehicle_name")
    private String vehicleName;

    /**
     * 车辆型号
     */
    @TableField("vehicle_model")
    private String vehicleModel;

    /**
     * 类目ID路径
     */
    @TableField("category_id_path")
    private String categoryIdPath;

    /**
     * 类目名称路径
     */
    @TableField("category_name_path")
    private String categoryNamePath;

    /**
     * 能源类型 1.柴油 2.汽油 3.纯电动 4.柴电混动
     */
    @TableField("energy_type")
    private Byte energyType;

    /**
     * 排放标准 3.国三 4.国四 5.国五 6.国六
     */
    @TableField("emission_standard")
    private Byte emissionStandard;

    /**
     * 车型图标
     */
    @TableField("vehicle_model_icon")
    private String vehicleModelIcon;

    /**
     * 机构ID
     */
    @TableField("org_id")
    private Long orgId;

    /**
     * 机构名称
     */
    @TableField("org_name")
    private String orgName;

    /**
     * 机构类型 1.厂商
     */
    @TableField("org_type")
    private Byte orgType;

    /**
     * 机构ID路径
     */
    @TableField("org_id_path")
    private String orgIdPath;

    /**
     * 机构名称路径
     */
    @TableField("org_name_path")
    private String orgNamePath;

    /**
     * 终端ID
     */
    @TableField("terminal_id")
    private Long terminalId;

    /**
     * 适配车型 1.工程机械 2.非工程机械 3.重型车 4.新能源
     */
    @TableField("adapt_vehicle_model")
    private Byte adaptVehicleModel;

    /**
     * 终端编号
     */
    @TableField("did")
    private String did;

    /**
     * SIM卡号
     */
    @TableField("sim_number")
    private String simNumber;

    /**
     * IMEI号
     */
    @TableField("imei")
    private String imei;

    /**
     * 安全芯片ID
     */
    @TableField("security_chip_id")
    private String securityChipId;

    /**
     * 集成电路卡识别码
     */
    @TableField("iccid")
    private String iccid;

    /**
     * 终端生产企业
     */
    @TableField("terminal_manufacturer")
    private String terminalManufacturer;

    /**
     * 终端生产日期
     */
    @TableField("terminal_mfd")
    private LocalDate terminalMfd;

    /**
     * 终端型号ID
     */
    @TableField("terminal_model_id")
    private Long terminalModelId;

    /**
     * 终端类型 1.TBOX 2.一体机 3.自动驾驶
     */
    @TableField("terminal_type")
    private Byte terminalType;

    /**
     * 终端型号
     */
    @TableField("terminal_model")
    private String terminalModel;

    /**
     * 安全芯片型号
     */
    @TableField("security_chip_model")
    private String securityChipModel;

    /**
     * 锁车使能 0.关闭 1.开启
     */
    @TableField("lock_enable_flag")
    private Byte lockEnableFlag;

    /**
     * 三合一使能 1.关闭 2.开启 99.未获取
     */
    @TableField("trinity_enable_flag")
    private Integer trinityEnableFlag;

    /**
     * 三合一连接状态 0.三合一断开 1.已连接token服务器 2.已连接IP服务器 3.已连接数据服务器 4.轨迹同步成功 99.未获取
     */
    @TableField("trinity_link_status")
    private Byte trinityLinkStatus;

    /**
     * 三合一反馈时间
     */
    @TableField("trinity_feedback_time")
    private LocalDateTime trinityFeedbackTime;

    /**
     * 三合一转发数量
     */
    @TableField("trinity_forwarding_num")
    private Integer trinityForwardingNum;

    /**
     * 在线状态 0.离线 2.休眠 9.在线
     */
    @TableField("online_status")
    private Byte onlineStatus;

    /**
     * 锁车状态 0.解锁 1.锁车
     */
    @TableField("lock_status")
    private Byte lockStatus;

    /**
     * 报警状态 0.未报警 1.报警
     */
    @TableField("alarm_status")
    private Byte alarmStatus;

    /**
     * 终端程序版本
     */
    @TableField("terminal_software_version")
    private String terminalSoftwareVersion;

    /**
     * 车辆新增时间
     */
    @TableField("vehicle_create_time")
    private LocalDateTime vehicleCreateTime;

    /**
     * 最新经度
     */
    @TableField("lon")
    private Double lon;

    /**
     * 最新纬度
     */
    @TableField("lat")
    private Double lat;

    /**
     * 最后绑定时间
     */
    @TableField("last_bind_time")
    private LocalDateTime lastBindTime;

    /**
     * 接入时间
     */
    @TableField("access_time")
    private LocalDateTime accessTime;

    /**
     * 最近接收时间
     */
    @TableField("last_received_time")
    private LocalDateTime lastReceivedTime;

    /**
     * 最近转发时间
     */
    @TableField("last_forwarding_time")
    private LocalDateTime lastForwardingTime;

    /**
     * 转发实时状态(1.正常 2.异常)
     */
    @TableField("forwarding_realtime_status")
    private Byte forwardingRealtimeStatus;

    /**
     * 最近告警发生时间
     */
    @TableField("last_alarm_happen_time")
    private LocalDateTime lastAlarmHappenTime;

    /**
     * 最近告警定位状态 0.未定位 1.已定位
     */
    @TableField("last_alarm_status")
    private Byte lastAlarmStatus;

    /**
     * 最近告警经度
     */
    @TableField("last_alarm_lon")
    private Double lastAlarmLon;

    /**
     * 最近告警纬度
     */
    @TableField("last_alarm_lat")
    private Double lastAlarmLat;

    /**
     * 省编码
     */
    @TableField("province_code")
    private String provinceCode;

    /**
     * 省名称
     */
    @TableField("province_name")
    private String provinceName;

    /**
     * 市编码
     */
    @TableField("city_code")
    private String cityCode;

    /**
     * 市名称
     */
    @TableField("city_name")
    private String cityName;

    /**
     * 区编码
     */
    @TableField("district_code")
    private String districtCode;

    /**
     * 区名称
     */
    @TableField("district_name")
    private String districtName;

    /**
     * 定位时间
     */
    @TableField("location_time")
    private LocalDateTime locationTime;

    /**
     * 休眠天数
     */
    @TableField("sleep_days")
    private Integer sleepDays;

    /**
     * 休眠阈值 单位:小时
     */
    @TableField("sleep_tv")
    private Integer sleepTv;

    /**
     * 休眠时间数 单位:分钟
     */
    @TableField("sleep_times")
    private Integer sleepTimes;

    /**
     * 离线天数
     */
    @TableField("offline_days")
    private Integer offlineDays;

    /**
     * 库存状态 参考字典表
     */
    @TableField("inventory_status")
    private Integer inventoryStatus;

    /**
     * 库存修改时间
     */
    @TableField("inventory_update_time")
    private LocalDateTime inventoryUpdateTime;

    /**
     * 库存修改人名称
     */
    @TableField("inventory_update_uname")
    private String inventoryUpdateUname;

    /**
     * 库存修改时长（天）
     */
    @TableField("inventory_update_duration")
    private Integer inventoryUpdateDuration;

    /**
     * 入商品库时间
     */
    @TableField("in_warehouse_time")
    private LocalDateTime inWarehouseTime;

    /**
     * 出厂时间
     */
    @TableField("ex_factory_time")
    private LocalDateTime exFactoryTime;

    /**
     * 销售时间
     */
    @TableField("sales_time")
    private LocalDateTime salesTime;

    /**
     * 作业标识 0.非作业 1.作业中
     */
    @TableField("job_flag")
    private Byte jobFlag;

    /**
     * 最近作业时间
     */
    @TableField("last_job_time")
    private LocalDateTime lastJobTime;

    /**
     * ACC状态 0.ACC关 1.ACC开 99.未上报
     */
    @TableField("acc_status")
    private Byte accStatus;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLpn() {
        return lpn;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }

    public Byte getMeinType() {
        return meinType;
    }

    public void setMeinType(Byte meinType) {
        this.meinType = meinType;
    }

    public String getMein() {
        return mein;
    }

    public void setMein(String mein) {
        this.mein = mein;
    }

    public String getMeinDynamic() {
        return meinDynamic;
    }

    public void setMeinDynamic(String meinDynamic) {
        this.meinDynamic = meinDynamic;
    }

    public Long getEngineBrandId() {
        return engineBrandId;
    }

    public void setEngineBrandId(Long engineBrandId) {
        this.engineBrandId = engineBrandId;
    }

    public String getEngineBrandName() {
        return engineBrandName;
    }

    public void setEngineBrandName(String engineBrandName) {
        this.engineBrandName = engineBrandName;
    }

    public Long getEngineModelId() {
        return engineModelId;
    }

    public void setEngineModelId(Long engineModelId) {
        this.engineModelId = engineModelId;
    }

    public String getEngineModelName() {
        return engineModelName;
    }

    public void setEngineModelName(String engineModelName) {
        this.engineModelName = engineModelName;
    }

    public String getEngineSerialNumber() {
        return engineSerialNumber;
    }

    public void setEngineSerialNumber(String engineSerialNumber) {
        this.engineSerialNumber = engineSerialNumber;
    }

    public Long getCommProtocolId() {
        return commProtocolId;
    }

    public void setCommProtocolId(Long commProtocolId) {
        this.commProtocolId = commProtocolId;
    }

    public String getCommProtocolCode() {
        return commProtocolCode;
    }

    public void setCommProtocolCode(String commProtocolCode) {
        this.commProtocolCode = commProtocolCode;
    }

    public String getCommProtocolName() {
        return commProtocolName;
    }

    public void setCommProtocolName(String commProtocolName) {
        this.commProtocolName = commProtocolName;
    }

    public Double getRsTankCapacity() {
        return rsTankCapacity;
    }

    public void setRsTankCapacity(Double rsTankCapacity) {
        this.rsTankCapacity = rsTankCapacity;
    }

    public Double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(Double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public Double getEngineTorque() {
        return engineTorque;
    }

    public void setEngineTorque(Double engineTorque) {
        this.engineTorque = engineTorque;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public Byte getMachineType() {
        return machineType;
    }

    public void setMachineType(Byte machineType) {
        this.machineType = machineType;
    }

    public String getCategoryACode() {
        return categoryACode;
    }

    public void setCategoryACode(String categoryACode) {
        this.categoryACode = categoryACode;
    }

    public String getCategoryAName() {
        return categoryAName;
    }

    public void setCategoryAName(String categoryAName) {
        this.categoryAName = categoryAName;
    }

    public String getCategoryBCode() {
        return categoryBCode;
    }

    public void setCategoryBCode(String categoryBCode) {
        this.categoryBCode = categoryBCode;
    }

    public String getCategoryBName() {
        return categoryBName;
    }

    public void setCategoryBName(String categoryBName) {
        this.categoryBName = categoryBName;
    }

    public String getCategoryCCode() {
        return categoryCCode;
    }

    public void setCategoryCCode(String categoryCCode) {
        this.categoryCCode = categoryCCode;
    }

    public String getCategoryCName() {
        return categoryCName;
    }

    public void setCategoryCName(String categoryCName) {
        this.categoryCName = categoryCName;
    }

    public Long getVehicleNameId() {
        return vehicleNameId;
    }

    public void setVehicleNameId(Long vehicleNameId) {
        this.vehicleNameId = vehicleNameId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getCategoryIdPath() {
        return categoryIdPath;
    }

    public void setCategoryIdPath(String categoryIdPath) {
        this.categoryIdPath = categoryIdPath;
    }

    public String getCategoryNamePath() {
        return categoryNamePath;
    }

    public void setCategoryNamePath(String categoryNamePath) {
        this.categoryNamePath = categoryNamePath;
    }

    public Byte getEnergyType() {
        return energyType;
    }

    public void setEnergyType(Byte energyType) {
        this.energyType = energyType;
    }

    public Byte getEmissionStandard() {
        return emissionStandard;
    }

    public void setEmissionStandard(Byte emissionStandard) {
        this.emissionStandard = emissionStandard;
    }

    public String getVehicleModelIcon() {
        return vehicleModelIcon;
    }

    public void setVehicleModelIcon(String vehicleModelIcon) {
        this.vehicleModelIcon = vehicleModelIcon;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Byte getOrgType() {
        return orgType;
    }

    public void setOrgType(Byte orgType) {
        this.orgType = orgType;
    }

    public String getOrgIdPath() {
        return orgIdPath;
    }

    public void setOrgIdPath(String orgIdPath) {
        this.orgIdPath = orgIdPath;
    }

    public String getOrgNamePath() {
        return orgNamePath;
    }

    public void setOrgNamePath(String orgNamePath) {
        this.orgNamePath = orgNamePath;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public Byte getAdaptVehicleModel() {
        return adaptVehicleModel;
    }

    public void setAdaptVehicleModel(Byte adaptVehicleModel) {
        this.adaptVehicleModel = adaptVehicleModel;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSecurityChipId() {
        return securityChipId;
    }

    public void setSecurityChipId(String securityChipId) {
        this.securityChipId = securityChipId;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getTerminalManufacturer() {
        return terminalManufacturer;
    }

    public void setTerminalManufacturer(String terminalManufacturer) {
        this.terminalManufacturer = terminalManufacturer;
    }

    public LocalDate getTerminalMfd() {
        return terminalMfd;
    }

    public void setTerminalMfd(LocalDate terminalMfd) {
        this.terminalMfd = terminalMfd;
    }

    public Long getTerminalModelId() {
        return terminalModelId;
    }

    public void setTerminalModelId(Long terminalModelId) {
        this.terminalModelId = terminalModelId;
    }

    public Byte getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Byte terminalType) {
        this.terminalType = terminalType;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public void setTerminalModel(String terminalModel) {
        this.terminalModel = terminalModel;
    }

    public String getSecurityChipModel() {
        return securityChipModel;
    }

    public void setSecurityChipModel(String securityChipModel) {
        this.securityChipModel = securityChipModel;
    }

    public Byte getLockEnableFlag() {
        return lockEnableFlag;
    }

    public void setLockEnableFlag(Byte lockEnableFlag) {
        this.lockEnableFlag = lockEnableFlag;
    }

    public Integer getTrinityEnableFlag() {
        return trinityEnableFlag;
    }

    public void setTrinityEnableFlag(Integer trinityEnableFlag) {
        this.trinityEnableFlag = trinityEnableFlag;
    }

    public Byte getTrinityLinkStatus() {
        return trinityLinkStatus;
    }

    public void setTrinityLinkStatus(Byte trinityLinkStatus) {
        this.trinityLinkStatus = trinityLinkStatus;
    }

    public LocalDateTime getTrinityFeedbackTime() {
        return trinityFeedbackTime;
    }

    public void setTrinityFeedbackTime(LocalDateTime trinityFeedbackTime) {
        this.trinityFeedbackTime = trinityFeedbackTime;
    }

    public Integer getTrinityForwardingNum() {
        return trinityForwardingNum;
    }

    public void setTrinityForwardingNum(Integer trinityForwardingNum) {
        this.trinityForwardingNum = trinityForwardingNum;
    }

    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Byte getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Byte lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Byte getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Byte alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getTerminalSoftwareVersion() {
        return terminalSoftwareVersion;
    }

    public void setTerminalSoftwareVersion(String terminalSoftwareVersion) {
        this.terminalSoftwareVersion = terminalSoftwareVersion;
    }

    public LocalDateTime getVehicleCreateTime() {
        return vehicleCreateTime;
    }

    public void setVehicleCreateTime(LocalDateTime vehicleCreateTime) {
        this.vehicleCreateTime = vehicleCreateTime;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public LocalDateTime getLastBindTime() {
        return lastBindTime;
    }

    public void setLastBindTime(LocalDateTime lastBindTime) {
        this.lastBindTime = lastBindTime;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public LocalDateTime getLastReceivedTime() {
        return lastReceivedTime;
    }

    public void setLastReceivedTime(LocalDateTime lastReceivedTime) {
        this.lastReceivedTime = lastReceivedTime;
    }

    public LocalDateTime getLastForwardingTime() {
        return lastForwardingTime;
    }

    public void setLastForwardingTime(LocalDateTime lastForwardingTime) {
        this.lastForwardingTime = lastForwardingTime;
    }

    public Byte getForwardingRealtimeStatus() {
        return forwardingRealtimeStatus;
    }

    public void setForwardingRealtimeStatus(Byte forwardingRealtimeStatus) {
        this.forwardingRealtimeStatus = forwardingRealtimeStatus;
    }

    public LocalDateTime getLastAlarmHappenTime() {
        return lastAlarmHappenTime;
    }

    public void setLastAlarmHappenTime(LocalDateTime lastAlarmHappenTime) {
        this.lastAlarmHappenTime = lastAlarmHappenTime;
    }

    public Byte getLastAlarmStatus() {
        return lastAlarmStatus;
    }

    public void setLastAlarmStatus(Byte lastAlarmStatus) {
        this.lastAlarmStatus = lastAlarmStatus;
    }

    public Double getLastAlarmLon() {
        return lastAlarmLon;
    }

    public void setLastAlarmLon(Double lastAlarmLon) {
        this.lastAlarmLon = lastAlarmLon;
    }

    public Double getLastAlarmLat() {
        return lastAlarmLat;
    }

    public void setLastAlarmLat(Double lastAlarmLat) {
        this.lastAlarmLat = lastAlarmLat;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public LocalDateTime getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(LocalDateTime locationTime) {
        this.locationTime = locationTime;
    }

    public Integer getSleepDays() {
        return sleepDays;
    }

    public void setSleepDays(Integer sleepDays) {
        this.sleepDays = sleepDays;
    }

    public Integer getSleepTv() {
        return sleepTv;
    }

    public void setSleepTv(Integer sleepTv) {
        this.sleepTv = sleepTv;
    }

    public Integer getSleepTimes() {
        return sleepTimes;
    }

    public void setSleepTimes(Integer sleepTimes) {
        this.sleepTimes = sleepTimes;
    }

    public Integer getOfflineDays() {
        return offlineDays;
    }

    public void setOfflineDays(Integer offlineDays) {
        this.offlineDays = offlineDays;
    }

    public Integer getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Integer inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public LocalDateTime getInventoryUpdateTime() {
        return inventoryUpdateTime;
    }

    public void setInventoryUpdateTime(LocalDateTime inventoryUpdateTime) {
        this.inventoryUpdateTime = inventoryUpdateTime;
    }

    public String getInventoryUpdateUname() {
        return inventoryUpdateUname;
    }

    public void setInventoryUpdateUname(String inventoryUpdateUname) {
        this.inventoryUpdateUname = inventoryUpdateUname;
    }

    public Integer getInventoryUpdateDuration() {
        return inventoryUpdateDuration;
    }

    public void setInventoryUpdateDuration(Integer inventoryUpdateDuration) {
        this.inventoryUpdateDuration = inventoryUpdateDuration;
    }

    public LocalDateTime getInWarehouseTime() {
        return inWarehouseTime;
    }

    public void setInWarehouseTime(LocalDateTime inWarehouseTime) {
        this.inWarehouseTime = inWarehouseTime;
    }

    public LocalDateTime getExFactoryTime() {
        return exFactoryTime;
    }

    public void setExFactoryTime(LocalDateTime exFactoryTime) {
        this.exFactoryTime = exFactoryTime;
    }

    public LocalDateTime getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(LocalDateTime salesTime) {
        this.salesTime = salesTime;
    }

    public Byte getJobFlag() {
        return jobFlag;
    }

    public void setJobFlag(Byte jobFlag) {
        this.jobFlag = jobFlag;
    }

    public LocalDateTime getLastJobTime() {
        return lastJobTime;
    }

    public void setLastJobTime(LocalDateTime lastJobTime) {
        this.lastJobTime = lastJobTime;
    }

    public Byte getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(Byte accStatus) {
        this.accStatus = accStatus;
    }

    @Override
    public String toString() {
        return "TwNrvRedundant{" +
            "vehicleId = " + vehicleId +
            ", tenantId = " + tenantId +
            ", vin = " + vin +
            ", lpn = " + lpn +
            ", meinType = " + meinType +
            ", mein = " + mein +
            ", meinDynamic = " + meinDynamic +
            ", engineBrandId = " + engineBrandId +
            ", engineBrandName = " + engineBrandName +
            ", engineModelId = " + engineModelId +
            ", engineModelName = " + engineModelName +
            ", engineSerialNumber = " + engineSerialNumber +
            ", commProtocolId = " + commProtocolId +
            ", commProtocolCode = " + commProtocolCode +
            ", commProtocolName = " + commProtocolName +
            ", rsTankCapacity = " + rsTankCapacity +
            ", fuelTankCapacity = " + fuelTankCapacity +
            ", engineTorque = " + engineTorque +
            ", remark = " + remark +
            ", vehicleModelId = " + vehicleModelId +
            ", machineType = " + machineType +
            ", categoryACode = " + categoryACode +
            ", categoryAName = " + categoryAName +
            ", categoryBCode = " + categoryBCode +
            ", categoryBName = " + categoryBName +
            ", categoryCCode = " + categoryCCode +
            ", categoryCName = " + categoryCName +
            ", vehicleNameId = " + vehicleNameId +
            ", vehicleName = " + vehicleName +
            ", vehicleModel = " + vehicleModel +
            ", categoryIdPath = " + categoryIdPath +
            ", categoryNamePath = " + categoryNamePath +
            ", energyType = " + energyType +
            ", emissionStandard = " + emissionStandard +
            ", vehicleModelIcon = " + vehicleModelIcon +
            ", orgId = " + orgId +
            ", orgName = " + orgName +
            ", orgType = " + orgType +
            ", orgIdPath = " + orgIdPath +
            ", orgNamePath = " + orgNamePath +
            ", terminalId = " + terminalId +
            ", adaptVehicleModel = " + adaptVehicleModel +
            ", did = " + did +
            ", simNumber = " + simNumber +
            ", imei = " + imei +
            ", securityChipId = " + securityChipId +
            ", iccid = " + iccid +
            ", terminalManufacturer = " + terminalManufacturer +
            ", terminalMfd = " + terminalMfd +
            ", terminalModelId = " + terminalModelId +
            ", terminalType = " + terminalType +
            ", terminalModel = " + terminalModel +
            ", securityChipModel = " + securityChipModel +
            ", lockEnableFlag = " + lockEnableFlag +
            ", trinityEnableFlag = " + trinityEnableFlag +
            ", trinityLinkStatus = " + trinityLinkStatus +
            ", trinityFeedbackTime = " + trinityFeedbackTime +
            ", trinityForwardingNum = " + trinityForwardingNum +
            ", onlineStatus = " + onlineStatus +
            ", lockStatus = " + lockStatus +
            ", alarmStatus = " + alarmStatus +
            ", terminalSoftwareVersion = " + terminalSoftwareVersion +
            ", vehicleCreateTime = " + vehicleCreateTime +
            ", lon = " + lon +
            ", lat = " + lat +
            ", lastBindTime = " + lastBindTime +
            ", accessTime = " + accessTime +
            ", lastReceivedTime = " + lastReceivedTime +
            ", lastForwardingTime = " + lastForwardingTime +
            ", forwardingRealtimeStatus = " + forwardingRealtimeStatus +
            ", lastAlarmHappenTime = " + lastAlarmHappenTime +
            ", lastAlarmStatus = " + lastAlarmStatus +
            ", lastAlarmLon = " + lastAlarmLon +
            ", lastAlarmLat = " + lastAlarmLat +
            ", provinceCode = " + provinceCode +
            ", provinceName = " + provinceName +
            ", cityCode = " + cityCode +
            ", cityName = " + cityName +
            ", districtCode = " + districtCode +
            ", districtName = " + districtName +
            ", locationTime = " + locationTime +
            ", sleepDays = " + sleepDays +
            ", sleepTv = " + sleepTv +
            ", sleepTimes = " + sleepTimes +
            ", offlineDays = " + offlineDays +
            ", inventoryStatus = " + inventoryStatus +
            ", inventoryUpdateTime = " + inventoryUpdateTime +
            ", inventoryUpdateUname = " + inventoryUpdateUname +
            ", inventoryUpdateDuration = " + inventoryUpdateDuration +
            ", inWarehouseTime = " + inWarehouseTime +
            ", exFactoryTime = " + exFactoryTime +
            ", salesTime = " + salesTime +
            ", jobFlag = " + jobFlag +
            ", lastJobTime = " + lastJobTime +
            ", accStatus = " + accStatus +
        "}";
    }
}
