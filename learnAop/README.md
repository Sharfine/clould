# aop
DynamicDataSourceAspect切面类   拦截注解

    @component
    @Aspect
    @Order(1)
    
    @Pointcut(value = "@annotation(com.sharfine.validate.annotation.DataSource)")
    方法
    @Before(value = "pointcut()")
    方法
     @After(value = "pointcut()")
    方法
    

DynamicDataSource数据源路由

    public class DynamicDataSource extends AbstractRoutingDataSource {
        
        @Override
        protected Object determineCurrentLookupKey() {
            String dataSource = DataSourceContextHolder.getDataSource();
    
            if (dataSource == null){
                log.info("当前数据源为[primary]");
            } else {
                log.info("当前数据源为{}", dataSource);
            }
    
            return dataSource;
        }





DataSourceContextHolder类，获取注解中的值，往其中注入数据源的名字，它会根据名字去选择对应数据源（提前往spring容器中注入多个数据源）

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    
    public static void setDataSource(String dbType){
        log.info("切换到[{}]数据源", dbType);
    
        contextHolder.set(dbType);
    }
    
    public static String getDataSource(){
        return  contextHolder.get();
    }
    
    public static void clearDataSource(){
        contextHolder.remove();
    }

MultipleDataSourceConfig注入多数据源
MultipleDataSourceConfig注入多数据源

    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }
    
    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix = "slave.datasource")
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }
    
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
        //配置多数据源
        HashMap<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DatabaseType.DATA_SOURCE_MASTER, primaryDataSource());
        dataSourceMap.put(DatabaseType.DATA_SOURCE_SLAVE, slaveDataSource());
         // 该方法是AbstractRoutingDataSource的方法
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    
    }
    
    
## 9.2
 添加excel转化成java对象以供数据库操作
