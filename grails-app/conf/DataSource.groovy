//dataSource {
//    pooled = true
//    driverClassName = "org.h2.Driver"
//    username = "sa"
//    password = ""
//}
//hibernate {
//    cache.use_second_level_cache = true
//    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
//}
//// environment specific settings
//environments {
//    development {
//        dataSource {
//            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
//            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//        }
//    }
//    test {
//        dataSource {
//            dbCreate = "update"
//            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//        }
//    }
//    production {
//        dataSource {
//            dbCreate = "update"
//            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
//            pooled = true
//            properties {
//               maxActive = -1
//               minEvictableIdleTimeMillis=1800000
//               timeBetweenEvictionRunsMillis=1800000
//               numTestsPerEvictionRun=3
//               testOnBorrow=true
//               testWhileIdle=true
//               testOnReturn=true
//               validationQuery="SELECT 1"
//            }
//        }
//    }
//}

dataSource {
    pooled = true
    dbCreate = "update"
    // Driver Oracle
    driverClassName = "oracle.jdbc.driver.OracleDriver"
    dialect = "org.hibernate.dialect.Oracle10gDialect"
}
hibernate {
    cache.use_second_level_cache=false
    cache.use_query_cache=false
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
environments {
    // Nombre de la base de datos con la que trabajaremos
    String baseDeDatos = "XE"  
    //Entorno de  Desarrollo
    development {
        dataSource {
            dbCreate = "update"
            // Usuario
            username = "desa"
            password = "123456"
            url = "jdbc:oracle:thin:@127.0.0.1:1521:XE"
        }
    }
    // Entorno Test
    test {
        dataSource {
            dbCreate = "update"
            // Usuario
            username = "desa"
            password = "123456"
            url = "jdbc:oracle:thin:@127.0.0.1:8080:XE"
        }
    }
    // Entorno de Produccion
    production {
        dataSource {
            dbCreate = "update"
            // Usuario
            username = "desa"
            password = "123456"
            url = "jdbc:oracle:thin:@127.0.0.1:8080:XE"
        }
    }
}
