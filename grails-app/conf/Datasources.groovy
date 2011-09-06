datasources = {
    datasource(name: 'prestoReadOnly') {
        driverClassName('com.mysql.jdbc.Driver')
        readOnly(true)
        url('jdbc:mysql://localhost/presto3')
        pooled(true)
        username('root')
        password('admin')
        logSql(false)
        dialect(org.hibernate.dialect.MySQL5InnoDBDialect)

        hibernate {
            cache {
                use_query_cache(true)
                use_second_level_cache(true)
                provider_class('net.sf.ehcache.hibernate.EhCacheProvider')
            }
        }
    }
}