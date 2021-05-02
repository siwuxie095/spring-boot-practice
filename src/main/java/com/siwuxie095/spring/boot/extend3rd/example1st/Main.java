package com.siwuxie095.spring.boot.extend3rd.example1st;

/**
 * @author Jiajing Li
 * @date 2021-05-01 22:25:04
 */
public class Main {

    /**
     * 配置属性
     *
     * 虽然 Spring Boot 在应用程序配置组件时处理了很多 "粗活"，但你可能还是想对其中某些组件进行微调。
     * 这时就该配置属性登场了。
     *
     * 之前介绍了 @ConfigurationProperties 注解，以及它如何暴露配置在代码外部的属性。你可以在自己
     * 创建的组件上使用 @ConfigurationProperties 注解，而 Spring Boot 自动配置的很多组件也添加
     * 了 @ConfigurationProperties 注解，可以通过 Spring Boot 支持的各种属性源对其进行配置。
     *
     * 例如，要指定内嵌的 Tomcat 或 Jetty 服务器应监听的端口，可以设置 server.port 属性。这个属性
     * 可以设置于 application.properties、application.yml、操作系统环境变量，或者是其他地方。
     *
     * 这里列出了 Spring Boot 组件提供的全部配置属性。请注意，这些属性是否生效取决于对应的组件是否声
     * 明为 Spring 应用程序上下文里的 Bean（基本是自动配置的），为一个不生效的组件设置属性是没有用的。
     * （1）flyway.baseline-description
     * 执行基线时标记已有 Schema 的描述。
     * （2）flyway.baseline-on-migrate
     * 在没有元数据表的情况下，针对非空 Schema 执行迁移时是否自动调用基线。（默认值：false）
     * （3）flyway.baseline-version
     * 执行基线时用来标记已有 Schema 的版本。（默认值：1）
     * （4）flyway.check-location
     * 检查迁移脚本所在的位置是否存在。（默认值：false）
     * （5）flyway.clean-on-validation-error
     * 在验证错误时，是否自动执行清理。（默认值：false）
     * （6）flyway.enabled
     * 开启 Flyway。（默认值：true）
     * （7）flyway.encoding
     * 设置 SQL 迁移文件的编码。（默认值:UTF-8）
     * （8）flyway.ignore-failed-future-migration
     * 在读元数据表时，是否忽略失败的后续迁移。（默认值：false）
     * （9）flyway.init-sqls
     * 获取连接后立即执行初始化的 SQL 语句。
     * （10）flyway.locations
     * 迁移脚本的位置。（默认值：db/migration）
     * （11）flyway.out-of-order
     * 是否允许乱序（out of order）迁移。（默认值：false）
     * （12）flyway.password
     * 待迁移数据库的登录密码。
     * （13）flyway.placeholder-prefix
     * 设置每个占位符的前缀。（默认值：${）
     * （14）flyway.placeholder-replacement
     * 是否要替换占位符。（默认值：true）
     * （15）flyway.placeholder-suffix
     * 设置占位符的后缀。（默认值：}）
     * （16）flyway.placeholders.[placeholder name]
     * 设置占位符的值。
     * （17）flyway.schemas
     * Flyway 管理的 Schema 列表，区分大小写。默认连接对应的默认 Schema。
     * （18）flyway.sql-migration-prefix
     * SQL 迁移的文件名前缀。（默认值：V）
     * （19）flyway.sql-migration-separator
     * SQL 迁移的文件名分隔符。（默认值：__）
     * （20）flyway.sql-migration-suffix
     * SQL 迁移的文件名后缀。（默认值：.sql）
     * （21）flyway.table
     * Flyway 使用的 Schema 元数据表名称。（默认值：schema_version）
     * （22）flyway.target
     * Flyway 要迁移到的目标版本号。（默认最新版本）
     * （23）flyway.url
     * 待迁移的数据库的 JDBC URL。如果没有设置，就使用配置的主数据源。
     * （24）flyway.user
     * 待迁移数据库的登录用户。
     * （25）flyway.validate-on-migrate
     * 在运行迁移时是否要自动验证。（默认值：true）
     * （26）liquibase.change-log
     * 变更日志配置路径。（默认值：classpath:/db/changelog/db.changelog-master.yaml）
     * （27）liquibase.check-change-log-location
     * 检查变更日志位置是否存在。（默认值：true）
     * （28）liquibase.contexts
     * 要使用的运行时上下文列表，用逗号分隔。
     * （29）liquibase.default-schema
     * 默认的数据库 Schema。
     * （30）liquibase.drop-first
     * 先删除数据库 Schema。（默认值：false）
     * （31）liquibase.enabled
     * 开启 Liquibase 支持。（默认值：true）
     * （32）liquibase.password
     * 待迁移数据库的登录密码。
     * （33）liquibase.url
     * 待迁移数据库的 JDBC URL。如果没有设置，就使用配置的主数据源。
     * （34）liquibase.user
     * 待迁移数据库的登录用户。
     * （35）multipart.enabled
     * 开启分段（multi-part）上传支持。（默认值：true）
     * （36）multipart.file-size-threshold
     * 大于该阈值的文件会写到磁盘上。这里的值可以使用 MB 或 KB 后缀来表明是兆字节还是千字节。（默认值：0）
     * （37）multipart.location
     * 上传文件的中间存放位置。
     * （38）multipart.max-file-size
     * 最大文件大小。这里的值可以使用 MB 或 KB 后缀来表明是兆字节还是千字节。（默认值：1 MB）
     * （39）multipart.max-request-size
     * 最大请求大小。这里的值可以使用 MB 或 KB 后缀来表明是兆字节还是千字节。（默认值：10 MB）
     * （40）security.basic.authorize-mode
     * 要运用的安全授权模式。
     * （41）security.basic.enabled
     * 开启基本身份验证。（默认值：true）
     * （42）security.basic.path
     * 要保护的路径，用逗号分隔。（默认值：[/**]）
     * （43）security.basic.realm
     * HTTP 基本领域（realm）用户名。（默认值：Spring）
     * （44）security.enable-csrf
     * 开启跨站请求伪造（cross-site request forgery）支持。（默认值：false）
     * （45）security.filter-order
     * 安全过滤器链顺序。（默认值：0）
     * （46）security.headers.cache
     * 开启缓存控制 HTTP 头。（默认值：false）
     * （47）security.headers.content-type
     * 开启 X-Content-Type-Options 头。（默认值：false）
     * （48）security.headers.frame
     * 开启 X-Frame-Options 头。（默认值：false）
     * （49）security.headers.hsts
     * HTTP Strict Transport Security（HSTS）模式（可设置为 none、domain、all）。
     * （50）security.headers.xss
     * 开启跨站脚本（cross-site scripting）保护。（默认值：false）
     * （51）security.ignored
     * 要从默认保护路径中排除掉的路径列表，用逗号分隔。
     * （52）security.oauth2.client.access-token-uri
     * 用于获取访问令牌的 URI。
     * （53）security.oauth2.client.access-token-validity-seconds
     * 在令牌过期前多长时间验证一次。
     * （54）security.oauth2.client.additional-information.[key]
     * 设置额外的信息，令牌授予者会将其添加到令牌里。
     * （55）security.oauth2.client.authentication-scheme
     * 传送持有人令牌（bearer token）的方法，包括 form、header、none、query，可选其一。（默认值：header）
     * （56）security.oauth2.client.authorities
     * 要赋予经授权客户端的权限。
     * （57）security.oauth2.client.authorized-grant-types
     * 客户端可用的授予类型。
     * （58）security.oauth2.client.auto-approve-scopes
     * 客户端自动通过的范围。
     * （59）security.oauth2.client.client-authentication-scheme
     * 在客户端身份认证时用于传输身份认证信息的方法，包括 form、header、none、query，可选其一。（默认值：header）
     * （60）security.oauth2.client.client-id
     * OAuth2 客户端 ID。
     * （61）security.oauth2.client.client-secret
     * OAuth2 客户端密钥。默认随机生成。
     * （62）security.oauth2.client.grant-type
     * 获得资源访问令牌的授予类型。
     * （63）security.oauth2.client.id
     * 应用程序的客户端 ID。
     * （64）security.oauth2.client.pre-established-redirect-uri
     * 与服务器预先建立好的重定向 URI。如果设置了该属性，用户授权请求中的重定向 URI 会被忽略，因为服务器不需要它。
     * （65）security.oauth2.client.refresh-token-validity-seconds
     * 刷新令牌在过期前的有效时间。
     * （66）security.oauth2.client.registered-redirect-uri
     * 客户端里注册的重定向 URI，用逗号分隔。
     * （67）security.oauth2.client.resource-ids
     * 与客户端关联的资源 ID，用逗号分隔。
     * （68）security.oauth2.client.scope
     * 客户端分配的域。
     * （69）security.oauth2.client.token-name
     * 令牌名称。
     * （70）security.oauth2.client.use-current-uri
     * 请求里的当前 URI（如果设置了的话）是否优先于预建立的重定向 URI。（默认值：true）
     * （71）security.oauth2.client.user-authorization-uri
     * 用户要重定向以便授访问令牌的 URI。
     * （72）security.oauth2.resource.id
     * 资源的标识符。
     * （73）security.oauth2.resource.jwt.key-uri
     * JWT 令牌的 URI。如果没有配置 key-value，使用的又是公钥，那么可以对这个属性进行设置。
     * （74）security.oauth2.resource.jwt.key-value
     * JWT 令牌的验证密钥，可以是对称密钥，也可以是 PEM 编码的 RSA 公钥。如果没有配置这个属性，那么可以用 key-uri 代替。
     * （75）security.oauth2.resource.prefer-token-info
     * 使用令牌的信息，设置为 false 则使用用户信息。（默认值：true）
     * （76）security.oauth2.resource.service-id
     * 服务 ID。（默认值：resource）
     * （77）security.oauth2.resource.token-info-uri
     * 令牌解码端点 URI。
     * （78）security.oauth2.resource.token-type
     * 在使用 userInfoUri 时发送的令牌类型。
     * （79）security.oauth2.resource.user-info-uri
     * 用户端点的 URI。
     * （80）security.oauth2.sso.filter-order
     * 在没有显式提供 WebSecurityConfigurerAdapter 时应用的过滤器顺序，在 WebSecurityConfigurerAdapter 里也可以指定顺序。
     * （81）security.oauth2.sso.login-path
     * 登录页的路径 —— 登录页是触发重定向到 OAuth2 授权服务器的页面。（默认值：/login）
     * （82）security.require-ssl
     * 对所有请求开启安全通道。（默认值：false）
     * （83）security.sessions
     * 创建会话使用的策略。（可选值包括：always、never、if_required、stateless）
     * （84）security.user.name
     * 默认的用户名。（默认值：user）
     * （85）security.user.password
     * 默认用户的密码。
     * （86）security.user.role
     * 赋予默认用户的角色。
     * （87）server.address
     * 服务器绑定的网络地址。
     * （88）server.compression.enabled
     * 是否要开启压缩。（默认值：false）
     * （89） server.compression.excluded-user-agents
     * 用逗号分割的列表，标明哪些用户代理不该开启压缩。（可选值包括：text/html、text/xml、text/plain、text/css）
     * （90）server.compression.mime-types
     * 要开启压缩的 MIME 类型列表，用逗号分割。
     * （91）server.compression.min-response-size
     * 要执行压缩的最小响应大小（单位为字节）。（默认值：2048）
     * （92）server.context-parameters.[param name]
     * 设置一个 Servlet 上下文参数。
     * （93）server.context-path
     * 应用程序的上下文路径。
     * （94）server.display-name
     * 应用程序的显示名称。（默认值：application）
     * （95）server.jsp-servlet.class-name
     * 针对 JSP 使用的 Servlet 类名。（默认值：org.apache.jasper.servlet.JspServlet）
     * （96）server.jsp-servlet.init-parameters.[param name]
     * 设置 JSP Servlet 初始化参数。
     * （97）server.jsp-servlet.registered
     * JSP Servlet 是否要注册到内嵌的 Servlet 容器里。（默认值：true）
     * （98）server.port
     * 服务器的 HTTP 端口。
     * （99）server.servlet-path
     * 主分发器 Servlet 的路径。（默认值：/）
     * （100）server.session.cookie.comment
     * 会话 Cookie 的注释。
     * （101）server.session.cookie.domain
     * 会话 Cookie 的域。
     * （102）server.session.cookie.http-only
     * 会话 Cookie 的 HttpOnly 标记。
     * （103）server.session.cookie.max-age
     * 会话 Cookie 的最大保存时间，单位为秒。
     * （104）server.session.cookie.name
     * 会话 Cookie 名称。
     * （105）server.session.cookie.path
     * 会话 Cookie 的路径。
     * （106）server.session.cookie.secure
     * 会话 Cookie 的 Secure 标记。
     * （107）server.session.persistent
     * 是否在两次重启间持久化会话数据。（默认值：false）
     * （108）server.session.timeout
     * 会话超时时间，单位为秒。
     * （109）server.session.tracking-modes
     * 会话跟踪模式（包括：cookie、url 和 ssl，可选其一或若干）。
     * （110）server.ssl.ciphers
     * 支持的 SSL 加密算法。
     * （111）server.ssl.client-auth
     * 客户端授权是主动想（want）还是被动需要（need）。要有一个 TrustStore。
     * （112）server.ssl.enabled
     * 是否开启 SSL。（默认值：true）
     * （113）server.ssl.key-alias
     * 在 KeyStore 里标识密钥的别名。
     * （114）server.ssl.key-password
     * 在 KeyStore 里用于访问密钥的密码。
     * （115）server.ssl.key-store
     * 持有 SSL 证书的 KeyStore 的路径（通常指向一个 .jks 文件）。
     * （116）server.ssl.key-store-password
     * 访问 KeyStore 时使用的密钥。
     * （117）server.ssl.key-store-provider
     * KeyStore 的提供者。
     * （118）server.ssl.key-store-type
     * KeyStore 的类型。
     * （119）server.ssl.protocol
     * 要使用的 SSL 协议。（默认值：TLS）
     * （120）server.ssl.trust-store
     * 持有 SSL 证书的 TrustStore。
     * （121）server.ssl.trust-store-password
     * 用于访问 TrustStore 的密码。
     * （122）server.ssl.trust-store-provider
     * TrustStore 的提供者。
     * （123）server.ssl.trust-store-type
     * TrustStore 的类型。
     * （124）server.tomcat.access-log-enabled
     * 是否开启访问日志。（默认值：false）
     * （125）server.tomcat.access-log-pattern
     * 访问日志的格式。（默认值：common）
     * （126）server.tomcat.accesslog.directory
     * 创建日志文件的目录。可以相对于 Tomcat 基础目录，也可以是绝对路径。（默认值：logs）
     * （127）server.tomcat.accesslog.enabled
     * 开启访问日志。（默认值：false）
     * （128）server.tomcat.accesslog.pattern
     * 访问日志的格式。（默认值：common）
     * （129）server.tomcat.accesslog.prefix
     * 日志文件名的前缀。（默认值：access_log）
     * （130）server.tomcat.accesslog.suffix
     * 日志文件名的后缀。（默认值：.log）
     * （131）server.tomcat.background-processor-delay
     * 两次调用 backgroundProcess 方法之间的延迟时间，单位为秒。（默认值：30）
     * （132）server.tomcat.basedir
     * Tomcat 的基础目录。如果没有指定则使用一个临时目录。
     * （133）server.tomcat.internal-proxies
     * 匹配可信任代理服务器的正则表达式。默认值："10\.\d{1,3}\.\d{1,3}\. \d{1,3}|192\.168\.\d {1,3}
     * \.\d{1,3}| 169\.254\.\d{1,3}\.\d{1,3}| 127\.\d{1,3}\.\d{1,3}\.\d{1,3}|172\.1[6-9]{1}
     * \.\d{1,3} \.\d{1,3}| 172\.2[0-9]{1}\.\d{1,3}\.\d{1,3}|172\.3[0-1]{1}\.\d{1,3}\.\d{1,3}"。
     * （134）server.tomcat.max-http-header-size
     * HTTP 消息头的最大字节数。（默认值：0）
     * （135）server.tomcat.max-threads
     * 最大工作线程数。（默认值：0）
     * （136）server.tomcat.port-header
     * 用来覆盖原始端口值的 HTTP 头的名字。
     * （137）server.tomcat.protocol-header
     * 持有流入协议的 HTTP 头，通常的名字是 X-Forwarded-Proto。仅当设置了 remoteIpHeader 的时候，它会被配置为 RemoteIpValve。
     * （138）server.tomcat.protocol-header-https-value
     * 协议头的值，表明流入请求使用了 SSL。（默认值：https）
     * （139）server.tomcat.remote-ip-header
     * 表明从哪个 HTTP 头里可以提取到远端 IP。仅当设置了 remoteIpHeader 的时候，它会被配置为 RemoteIpValve。
     * （140）server.tomcat.uri-encoding
     * 用来解码 URI 的字符编码。
     * （141）server.undertow.access-log-dir
     * Undertow 的访问日志目录。（默认值：logs）
     * （142）server.undertow.access-log-enabled
     * 是否开启访问日志。（默认值：false）
     * （143）server.undertow.access-log-pattern
     * 访问日志的格式。（默认值：common）
     * （144）server.undertow.accesslog.dir
     * Undertow 访问日志目录。
     * （145）server.undertow.accesslog.enabled
     * 开启访问日志。（默认值：false）
     * （146）server.undertow.accesslog.pattern
     * 访问日志的格式。（默认值：common）
     * （147）server.undertow.buffer-size
     * 每个缓冲的字节数。
     * （148）server.undertow.buffers-per-region
     * 每个区（region）的缓冲数。
     * （149）server.undertow.direct-buffers
     * 在 Java 堆外分配缓冲。
     * （150）server.undertow.io-threads
     * 要为工作线程创建的 I/O 线程数。
     * （151）server.undertow.worker-threads
     * 工作线程数。
     * （152）spring.activemq.broker-url
     * ActiveMQ 代理的 URL。默认自动生成。
     * （153）spring.activemq.in-memory
     * 标明默认代理 URL 是否应该在内存里。如果指定了一个显式的代理则忽略该属性。（默认值：true）
     * （154）spring.activemq.password
     * 代理的登录密码。
     * （155）spring.activemq.pooled
     * 标明是否要创建一个 PooledConnectionFactory 来代替普通的 ConnectionFactory。（默认值：false）
     * （156）spring.activemq.user
     * 代理的登录用户名。
     * （157）spring.aop.auto
     * 添加 @EnableAspectJAutoProxy。（默认值：true）
     * （158）spring.aop.proxy-target-class
     * 是否要创建基于子类（即 Code Generation Library，CGLIB）的代理来代替基于 Java 接口的代理，
     * 前者为 true，后者为 false。（默认值：false）
     * （159）spring.application.admin.enabled
     * 开启应用程序的管理功能。（默认值：false）
     * （160）spring.application.admin.jmx-name
     * 应用程序管理 MBean 的 JMX 名称。（默认值：org.springframework.boot:type=Admin,name=SpringApplication）
     * （161）spring.artemis.embedded.cluster-password
     * 集群密码。默认在启动时随机生成。
     * （162）spring.artemis.embedded.data-directory
     * Journal 文件目录。如果关闭了持久化则不需要该属性。
     * （163）spring.artemis.embedded.enabled
     * 如果有 Artemis 服务器 API 则开启嵌入模式。（默认值：true）
     * （164）spring.artemis.embedded.persistent
     * 开启持久化存储。（默认值：false）
     * （165）spring.artemis.embedded.queues
     * 要在启动时创建的队列列表，用逗号分隔。（默认值：[]）
     * （166）spring.artemis.embedded.server-id
     * 服务器 ID。默认情况下，使用一个自动递增的计数器。（默认值：0）
     * （167）spring.artemis.embedded.topics
     * 在启动时要创建的主题列表，用逗号分隔。（默认值：[]）
     * （168）spring.artemis.host
     * Artemis 代理主机。（默认值：localhost）
     * （169）spring.artemis.mode
     * Artemis 部署模式，默认自动检测。可以显式地设置为 native 或 embedded。
     * （170）spring.artemis.port
     * Artemis 代理端口。（默认值：61616）
     * （171）spring.autoconfigure.exclude
     * 要排除的自动配置类。
     * （172）spring.batch.initializer.enabled
     * 如果有必要的话，在启动时创建需要的批处理表。（默认值：true）
     * （173）spring.batch.job.enabled
     * 在启动时执行上下文里的所有 Spring Batch 任务。（默认值：true）
     * （174）spring.batch.job.names
     * 启动时要执行的任务名列表，用逗号分隔。默认在上下文里找到的所有任务都会执行。
     * （175）spring.batch.schema
     * 指向初始化数据库 Schema 用的 SQL 文件的路径。（默认值：classpath:org/ springframework/batch
     * /core/schema-@@platform@@.sql）
     * （176）spring.batch.table-prefix
     * 所有批处理元数据表的表前缀。
     * （177）spring.cache.cache-names
     * 如果底层缓存管理器支持缓存名的话，可以在这里指定要创建的缓存名列表，用逗号分隔。通常这会禁用运行时创建其他额外缓存的能力。
     * （178）spring.cache.ehcache.config
     * 用来初始化 EhCache 的配置文件的位置。
     * （179）spring.cache.guava.spec
     * 用来创建缓存的 Spec。要获得有关 Spec 格式的详细情况，可以查看 CacheBuilderSpec。
     * （180）spring.cache.hazelcast.config
     * 用来初始化 Hazelcast 的配置文件的位置。
     * （181）spring.cache.infinispan.config
     * 用来初始化 Infinispan 的配置文件的位置。
     * （182）spring.cache.jcache.config
     * 用来初始化缓存管理器的配置文件的位置。配置文件依赖于底层的缓存实现。
     * （183）spring.cache.jcache.provider
     * CachingProvider 实现的全限定类名，用来获取 JSR-107 兼容的缓存管理器，仅在 Classpath 里有不只一个
     * JSR-107 实现时才需要这个属性。
     * （184）spring.cache.type
     * 缓存类型，默认根据环境自动检测。
     * （185）spring.dao.exceptiontranslation.enabled
     * 打开 PersistenceExceptionTranslationPostProcessor。（默认值：true）
     * （186）spring.data.elasticsearch.cluster-name
     * Elasticsearch 集群名。（默认值：elasticsearch）
     * （187）spring.data.elasticsearch.cluster-nodes
     * 集群节点地址列表，用逗号分隔。如果没有指定，就启动一个客户端节点。
     * （188）spring.data.elasticsearch.properties
     * 用来配置客户端的额外属性。
     * （189）spring.data.elasticsearch.repositories.enabled
     * 开启 Elasticsearch 仓库。（默认值：true）
     * （190）spring.data.jpa.repositories.enabled
     * 开启 JPA 仓库。（默认值：true）
     * （191）spring.data.mongodb.authentication-database
     * 身份认证数据库名。
     * （192）spring.data.mongodb.database
     * 数据库名。
     * （193）spring.data.mongodb.field-naming-strategy
     * 要使用的 FieldNamingStrategy 的全限定名。
     * （194）spring.data.mongodb.grid-fs-database
     * GridFS 数据库名称。
     * （195）spring.data.mongodb.host
     * Mongo 服务器主机地址。
     * （196）spring.data.mongodb.password
     * Mongo 服务器的登录密码。
     * （197）spring.data.mongodb.port
     * Mongo 服务器端口号。
     * （198）spring.data.mongodb.repositories.enabled
     * 开启 Mongo 仓库。（默认值：true）
     * （199）spring.data.mongodb.uri
     * Mongo 数据库 URI。设置了该属性后就主机和端口号会被忽略。（默认值：mongodb://localhost/test）
     * （200）spring.data.mongodb.username
     * Mongo 服务器的登录用户名。
     * （201）spring.data.rest.base-path
     * 用于发布仓库资源的基本路径。
     * （202）spring.data.rest.default-page-size
     * 分页数据的默认页大小。（默认值：20）
     * （203）spring.data.rest.limit-param-name
     * 用于标识一次返回多少记录的 URL 查询字符串参数名。（默认值：size）
     * （204）spring.data.rest.max-page-size
     * 最大分页大小。（默认值：1000）
     * （205）spring.data.rest.page-param-name
     * URL 查询字符串参数的名称，用来标识返回哪一页。（默认值：page）
     * （206）spring.data.rest.return-body-on-create
     * 在创建实体后是否返回一个响应体。（默认值：false）
     * （207）spring.data.rest.return-body-on-update
     * 在更新实体后是否返回一个响应体。（默认值：false）
     * （208）spring.data.rest.sort-param-name
     * URL 查询字符串参数的名称，用来标识结果排序的方向。（默认值：sort）
     * （209）spring.data.solr.host
     * Solr 的主机地址。如果设置了 zk-host 则忽略该属性。（默认值：http://127.0.0.1: 8983/solr）
     * （210）spring.data.solr.repositories.enabled
     * 开启 Solr 仓库。（默认值：true）
     * （211）spring.data.solr.zk-host
     * ZooKeeper 主机地址，格式为 "主机:端口"。
     * （212）spring.datasource.abandon-when-percentage-full
     * 一个百分比形式的阈值，超过该阈值则关闭并报告被弃用（超时）的连接。
     * （213）spring.datasource.allow-pool-suspension
     * 是否允许池暂停（pool suspension）。在开启池暂停后会有性能会受到一定影响，除非你真的需要这个功能
     * （例如在冗余的系统下），否则不要开启它。该属性只在使用 Hikari 数据库连接池时有用。（默认值：false）
     * （214）spring.datasource.alternate-username-allowed
     * 是否允许使用其他用户名。
     * （215）spring.datasource.auto-commit
     * 更新操作是否自动提交。
     * （216）spring.datasource.catalog
     * 默认的 Catalog 名称。
     * （217）spring.datasource.commit-on-return
     * 在连接归还时，连接池是否要提交挂起的事务。
     * （218）spring.datasource.connection-init-sql
     * 在所有新连接创建时都会执行的 SQL 语句，该语句会在连接加入连接池前执行。
     * （219）spring.datasource.connection-init-sqls
     * 在物理连接第一次创建时执行的 SQL 语句列表。（用于 DBCP 连接池）
     * （220）spring.datasource.connection-properties.[key]
     * 设置创建连接时使用的属性。（用于 DBCP 连接池）
     * （221）spring.datasource.connection-test-query
     * 用于测试连接有效性的 SQL 查询。
     * （222）spring.datasource.connection-timeout
     * 连接超时（单位为毫秒）。
     * （223）spring.datasource.continue-on-error
     * 初始化数据库时发生错误不要终止。（默认值：false）
     * （224）spring.datasource.data
     * 指向数据（数据库操纵语言，Data Manipulation Language，DML）脚本资源的引用。
     * （225）spring.datasource.data-source-class-name
     * 用于获取连接的数据源的全限定类名。
     * （226）spring.datasource.data-source-jndi
     * 用于获取连接的数据源的 JNDI 位置。
     * （227）spring.datasource.data-source-properties.[key]
     * 设置创建数据源时使用的属性。（用于 Hikari 连接池）
     * （228）spring.datasource.db-properties
     * 设置创建数据源时使用的属性。（用于 Tomcat 连接池）
     * （229）spring.datasource.default-auto-commit
     * 连接上的操作是否自动提交。
     * （230）spring.datasource.default-catalog
     * 连接的默认 Catalog。
     * （231）spring.datasource.default-read-only
     * 连接的默认只读状态。
     * （232）spring.datasource.default-transaction-isolation
     * 连接的默认事务隔离级别。
     * （233）spring.datasource.driver-class-name
     * JDBC 驱动的全限定类名。默认根据 URL 自动检测。
     * （234）spring.datasource.fair-queue
     * 是否以 FIFO 方式返回连接。
     * （235）spring.datasource.health-check-properties.[key]
     * 设置要纳入健康检查的属性。（用于 Hikari 连接池）
     * （236）spring.datasource.idle-timeout
     * 连接池中的连接能保持闲置状态的最长时间，单位为毫秒。（默认值：10）
     * （237）spring.datasource.ignore-exception-on-pre-load
     * 初始化数据库连接池时是否要忽略连接。
     * （238）spring.datasource.init-sql
     * 在连接第一次创建时运行的自定义查询。
     * （239）spring.datasource.initial-size
     * 在连接池启动时要建立的连接数。
     * （240）spring.datasource.initialization-fail-fast
     * 在连接池创建时，如果达不到最小连接数是否要抛出异常。（默认值：true）
     * （241）spring.datasource.initialize
     * 使用 data.sql 初始化数据库。（默认值：true）
     * （242）spring.datasource.isolate-internal-queries
     * 是否要隔离内部请求。（默认值：false）
     * （243）spring.datasource.jdbc-interceptors
     * 一个分号分隔的类名列表，这些类都扩展了 JdbcInterceptor 类。这些拦截器会插入 java.sql.Connection
     * 对象的操作链里。（用于 Tomcat 连接池）
     * （244）spring.datasource.jdbc-url
     * 用来创建连接的 JDBC URL。
     * （245）spring.datasource.jmx-enabled
     * 开启 JMX 支持（如果底层连接池提供该功能的话）。（默认值：false）
     * （246）spring.datasource.jndi-name
     * 数据源的 JNDI 位置。设置了该属性则忽略类、URL、用户名和密码属性。
     * （247）spring.datasource.leak-detection-threshold
     * 用来检测 Hikari 连接池连接泄露的阈值，单位为毫秒。
     * （248）spring.datasource.log-abandoned
     * 是否针对弃用语句或连接的应用程序代码记录下跟踪栈。用于 DBCP 连接池。（默认值：false）
     * （249）spring.datasource.log-validation-errors
     * 在使用 Tomcat 连接池时是否要记录验证错误。
     * （250）spring.datasource.login-timeout
     * 连接数据库的超时时间（单位为秒）。
     * （251）spring.datasource.max-active
     * 连接池中的最大活跃连接数。
     * （252）spring.datasource.max-age
     * 连接池中连接的最长寿命。
     * （253）spring.datasource.max-idle
     * 连接池中的最大空闲连接数。
     * （254）spring.datasource.max-lifetime
     * 连接池中连接的最长寿命（单位为毫秒）。
     * （255）spring.datasource.max-open-prepared-statements
     * 开启状态的 PreparedStatement 的数量上限。
     * （256）spring.datasource.max-wait
     * 连接池在等待返回连接时，最长等待多少毫秒再抛出异常。
     * （257）spring.datasource.maximum-pool-size
     * 连接池能达到的最大规模，包含空闲连接的数量和使用中的连接数量。
     * （258）spring.datasource.min-evictable-idle-time-millis
     * 一个空闲连接被空闲连接释放器（如果存在的话）优雅地释放前，最短会在连接池里停留多少时间。
     * （259）spring.datasource.min-idle
     * 连接池里始终应该保持的最小连接数。（用于 DBCP 和 Tomcat 连接池）
     * （260）spring.datasource.minimum-idle
     * HikariCP 试图在连接池里维持的最小空闲连接数。
     * （261）spring.datasource.name
     * 数据源的名称。
     * （262）spring.datasource.num-tests-per-eviction-run
     * 空闲对象释放器线程（如果存在的话）每次运行时要检查的对象数。
     * （263）spring.datasource.password
     * 数据库的登录密码。
     * （264）spring.datasource.platform
     * 在 Schema 资源（schema-${platform}.sql）里要使用的平台。（默认值：all）
     * （265）spring.datasource.pool-name
     * 连接池名称。
     * （266）spring.datasource.pool-prepared-statements
     * 是否要将 Statement 放在池里。
     * （267）spring.datasource.propagate-interrupt-state
     * 对于等待连接的中断线程，是否要传播中断状态。
     * （268）spring.datasource.read-only
     * 在使用 Hikari 连接池时将数据源设置为只读。
     * （269）spring.datasource.register-mbeans
     * Hikari 连接池是否要注册 JMX MBean。
     * （270）spring.datasource.remove-abandoned
     * 被弃用的连接在到达弃用超时后是否应该被移除。
     * （271）spring.datasource.remove-abandoned-timeout
     * 连接在多少秒后应该考虑弃用。
     * （272）spring.datasource.rollback-on-return
     * 在连接归还连接池时，是否要回滚挂起的事务。
     * （273）spring.datasource.schema
     * Schema（数据定义语言，Data Definition Language，DDL）脚本资源的引用。
     * （274）spring.datasource.separator
     * SQL 初始化脚本里的语句分割符。（默认值：;）
     * （275）spring.datasource.sql-script-encoding
     * SQL 脚本的编码。
     * （276）spring.datasource.suspect-timeout
     * 在记录一个疑似弃用连接前要等待多少秒。
     * （277）spring.datasource.test-on-borrow
     * 从连接池中借用连接时是否要进行测试。
     * （278）spring.datasource.test-on-connect
     * 在建立连接时是否要进行测试。
     * （279）spring.datasource.test-on-return
     * 在将连接归还到连接池时是否要进行测试。
     * （280）spring.datasource.test-while-idle
     * 在连接空闲时是否要进行测试。
     * （281）spring.datasource.time-between-eviction-runs-millis
     * 在两次空闲连接验证、弃用连接清理和空闲池大小调整之间睡眠的毫秒数。
     * （282）spring.datasource.transaction-isolation
     * 在使用 Hikari 连接池时设置默认事务隔离级别。
     * （283）spring.datasource.url
     * 数据库的 JDBC URL。
     * （284）spring.datasource.use-disposable-connection-facade
     * 连接是否要用一个门面（facade）封装起来，在调用了 Connection.close() 后就不能再使用这个连接了。
     * （285）spring.datasource.use-equals
     * 在比较方法名时是否使用 String.equals() 来代替 ==。
     * （286）spring.datasource.use-lock
     * 在操作连接对象时是否要加锁。
     * （287）spring.datasource.username
     * 数据库的登录用户名。
     * （288）spring.datasource.validation-interval
     * 执行连接验证的间隔时间，单位为毫秒。
     * （289）spring.datasource.validation-query
     * 在连接池里的连接返回给调用者或连接池时，要执行的验证 SQL 查询。
     * （290）spring.datasource.validation-query-timeout
     * 在连接验证查询执行失败前等待的超时时间，单位为秒。
     * （291）spring.datasource.validation-timeout
     * 在连接验证失败前等待的超时时间，单位为秒。（用于 Hikari 连接池）
     * （292）spring.datasource.validator-class-name
     * 可选验证器类的全限定类名，用于执行测试查询。
     * （293）spring.datasource.xa.data-source-class-name
     * XA 数据源的全限定类名。
     * （294）spring.datasource.xa.properties
     * 要传递给 XA 数据源的属性。
     * （295）spring.freemarker.allow-request-override
     * HttpServletRequest 的属性是否允许覆盖（隐藏）控制器生成的同名模型属性。
     * （296）spring.freemarker.allow-session-override
     * HttpSession 的属性是否允许覆盖（隐藏）控制器生成的同名模型属性。
     * （297）spring.freemarker.cache
     * 开启模板缓存。
     * （298）spring.freemarker.charset
     * 模板编码。
     * （299）spring.freemarker.check-template-location
     * 检查模板位置是否存在。
     * （300）spring.freemarker.content-type
     * Content-Type 的值。
     * （301）spring.freemarker.enabled
     * 开启 FreeMarker 的 MVC 视图解析。
     * （302）spring.freemarker.expose-request-attributes
     * 在模型合并到模板前，是否要把所有的请求属性添加到模型里。
     * （303）spring.freemarker.expose-session-attributes
     * 在模型合并到模板前，是否要把所有的 HttpSession 属性添加到模型里。
     * （304）spring.freemarker.expose-spring-macro-helpers
     * 是否发布供 Spring 宏程序库使用的 RequestContext，并将命其名为 springMacroRequestContext。
     * （305）spring.freemarker.prefer-file-system-access
     * 加载模板时优先通过文件系统访问。文件系统访问能够实时检测到模板变更。（默认值：true）
     * （306）spring.freemarker.prefix
     * 在构建 URL 时添加到视图名称前的前缀。
     * （307）spring.freemarker.request-context-attribute
     * 在所有视图里使用的 RequestContext 属性的名称。
     * （308）spring.freemarker.settings
     * 要传递给 FreeMarker 配置的各种键。
     * （309）spring.freemarker.suffix
     * 在构建 URL 时添加到视图名称后的后缀。
     * （310）spring.freemarker.template-loader-path
     * 模板路径列表，用逗号分隔。（默认值：["classpath:/templates/"]）
     * （311）spring.freemarker.view-names
     * 可解析的视图名称的白名单。
     * （312）spring.groovy.template.allow-request-override
     * HttpServletRequest 的属性是否允许覆盖（隐藏）控制器生成的同名模型属性。
     * （313）spring.groovy.template.allow-session-override
     * HttpSession 的属性是否允许覆盖（隐藏）控制器生成的同名模型属性。
     * （314）spring.groovy.template.cache
     * 开启模板缓存。
     * （315）spring.groovy.template.charset
     * 模板编码。
     * （316）spring.groovy.template.check-template-location
     * 检查模板位置是否存在。
     * （317）spring.groovy.template.configuration.auto-escape
     * 模型变量在模板里呈现时是否要做转义。（默认值：false）
     * （318）spring.groovy.template.configuration.auto-indent
     * 模板是否要自动呈现缩进。（默认值：false）
     * （319）spring.groovy.template.configuration.auto-indent-string
     * 开启自动缩进时用于缩进的字符串，可以是 SPACES，也可以是 TAB。（默认值：SPACES）
     * （320）spring.groovy.template.configuration.auto-new-line
     * 模板里是否要呈现新的空行。（默认值：false）
     * （321）spring.groovy.template.configuration.base-template-class
     * 模板基类。
     * （322）spring.groovy.template.configuration.cache-templates
     * 模板是否应该缓存。（默认值：true）
     * （323）spring.groovy.template.configuration.declaration-encoding
     * 用来写声明头的编码。
     * （324）spring.groovy.template.configuration.expand-empty-elements
     * 没有正文的元素该用短形式（例如，<br/>）还是扩展形式（例如，<br></br>）来书写。（默认值：false）
     * （325）spring.groovy.template.configuration.locale
     * 设置模板地域。
     * （326）spring.groovy.template.configuration.new-line-string
     * 在自动空行开启后用来呈现空行的字符串。（默认为系统的 line.separator 属性值）
     * （327）spring.groovy.template.configuration.resource-loader-path
     * Groovy 模板的路径。（默认值：classpath:/templates/）
     * （328）spring.groovy.template.configuration.use-double-quotes
     * 属性是该用双引号还是单引号。（默认值：false）
     * （329）spring.groovy.template.content-type
     * Content-Type 的值。
     * （330）spring.groovy.template.enabled
     * 开启 Groovy 模板的 MVC 视图解析。
     * （331）spring.groovy.template.expose-request-attributes
     * 在模型合并到模板前，是否要把所有的请求属性添加到模型里。
     * （332）spring.groovy.template.expose-session-attributes
     * 在模型合并到模板前，是否要把所有的 HttpSession 属性添加到模型里。
     * （333）spring.groovy.template.expose-spring-macro-helpers
     * 是否发布供 Spring 宏程序库使用的 RequestContext，并将其命名为 springMacroRequestContext。
     * （334）spring.groovy.template.prefix
     * 在构建 URL 时，添加到视图名称前的前缀。
     * （335）spring.groovy.template.request-context-attribute
     * 所有视图里使用的 RequestContext 属性的名称。
     * （336）spring.groovy.template.resource-loader-path
     * 模板路径（默认值：classpath:/templates/）
     * （337）spring.groovy.template.suffix
     * 在构建 URL 时，添加到视图名称后的后缀。
     * （338）spring.groovy.template.view-names
     * 可解析的视图名称白名单。
     * （339）spring.h2.console.enabled
     * 开启控制台。（默认值：false）
     * （340）spring.h2.console.path
     * 可以找到控制台的路径。（默认值：/h2-console）
     * （341）spring.hateoas.apply-to-primary-object-mapper
     * 指定主 ObjectMapper 是否要应用 HATEOAS 支持。（默认值：true）
     * （342）spring.hornetq.embedded.cluster-password
     * 集群密码。默认在启动时随机生成。
     * （343）spring.hornetq.embedded.data-directory
     * 日志文件目录。如果关闭了持久化功能则不需要该属性。
     * （344）spring.hornetq.embedded.enabled
     * 如果有 HornetQ 服务器 API，则开启嵌入模式。（默认值：true）
     * （345）spring.hornetq.embedded.persistent
     * 开启持久化存储。（默认值：false）
     * （346）spring.hornetq.embedded.queues
     * 启动时要创建的队列列表，用逗号分隔。（默认值：[]）
     * （347）spring.hornetq.embedded.server-id
     * 服务器 ID。默认使用自增长计数器。（默认值：0）
     * （348）spring.hornetq.embedded.topics
     * 启动时要创建的主题列表，用逗号分隔。（默认值：[]）
     * （349）spring.hornetq.host
     * HornetQ 的主机。（默认值：localhost）
     * （350）spring.hornetq.mode
     * HornetQ 的部署模式，默认为自动检测。可以显式地设置为 native 或 embedded。
     * （351）spring.hornetq.port
     * HornetQ 的端口。（默认值：5445）
     * （352）spring.http.converters.preferred-json-mapper
     * HTTP 消息转换时优先使用 JSON 映射器。
     * （353）spring.http.encoding.charset
     * HTTP 请求和响应的字符集。如果没有显式地指定 Content-Type 头，则将该属性值作为这个头的值。（默认值：UTF-8）
     * （354）spring.http.encoding.enabled
     * 开启 HTTP 编码支持。（默认值：true）
     * （355）spring.http.encoding.force
     * 强制将 HTTP 请求和响应编码为所配置的字符集。（默认值：true）
     * （356）spring.jackson.date-format
     * 日期格式字符串（yyyy-MM-dd HH:mm:ss）或日期格式类的全限定类名。
     * （357）spring.jackson.deserialization
     * 影响 Java 对象反序列化的 Jackson on/off 特性。
     * （358）spring.jackson.generator
     * 用于生成器的 Jackson on/off 特性。
     * （359）spring.jackson.joda-date-time-format
     * Joda 日期时间格式字符串（yyyy-MM-dd HH:mm:ss）。如果没有配置，而 date-format 又配置了一个格式字符串的话，
     * 会将它作为降级配置。
     * （360）spring.jackson.locale
     * 用于格式化的地域值。
     * （361）spring.jackson.mapper
     * Jackson 的通用 on/off 特性。
     * （362）spring.jackson.parser
     * 用于解析器的 Jackson on/off 特性。
     * （363）spring.jackson.property-naming-strategy
     * Jackson 的 PropertyNamingStrategy 中的一个常量（CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES）。
     * 也可以设置 PropertyNamingStrategy 的子类的全限定类名。
     * （364）spring.jackson.serialization
     * 影响 Java 对象序列化的 Jackson on/off 特性。
     * （365）spring.jackson.serialization-inclusion
     * 控制序列化时要包含哪些属性。可选择 Jackson 的 JsonInclude.Include 枚举里的某个值。
     * （366）spring.jackson.time-zone
     * 格式化日期时使用的时区。可以配置各种可识别的时区标识符，比如 America/Los_Angeles 或者 GMT+10。
     * （367）spring.jersey.filter.order
     * Jersey 过滤器链的顺序。（默认值：0）
     * （368）spring.jersey.init
     * 通过 Servlet 或过滤器传递给 Jersey 的初始化参数。
     * （369）spring.jersey.type
     * Jersey 集成类型。可以是 servlet 或者 filter。
     * （370）spring.jms.jndi-name
     * 连接工厂的 JNDI 名字。设置了该属性，则优先于其他自动配置的连接工厂。
     * （371）spring.jms.listener.acknowledge-mode
     * 容器的应答模式（acknowledgment mode）。默认情况下，监听器使用自动应答。
     * （372）spring.jms.listener.auto-startup
     * 启动时自动启动容器。（默认值：true）
     * （373）spring.jms.listener.concurrency
     * 并发消费者的数量下限。
     * （374）spring.jms.listener.max-concurrency
     * 并发消费者的数量上限。
     * （375）spring.jms.pub-sub-domain
     * 如果是主题而非队列，指明默认的目的地类型是否支持 Pub/Sub。（默认值：false）
     * （376）spring.jmx.default-domain
     * JMX 域名。
     * （377）spring.jmx.enabled
     * 将管理 Bean 发布到 JMX 域里。（默认值：true）
     * （378）spring.jmx.server
     * MBeanServer 的 Bean 名称。（默认值：mbeanServer）
     * （379）spring.jooq.sql-dialect
     * 在与配置的数据源通信时，JOOQ 使用的 SQLDialect，比如 POSTGRES。
     * （380）spring.jpa.database
     * 要操作的目标数据库，默认自动检测。也可以通过 databasePlatform 属性进行设置。
     * （381）spring.jpa.database-platform
     * 要操作的目标数据库，默认自动检测。也可以通过 Database 枚举来设置。
     * （382）spring.jpa.generate-ddl
     * 启动时要初始化 Schema。（默认值：false）
     * （383）spring.jpa.hibernate.ddl-auto
     * DDL模式（none、validate、update、create 和 create-drop）。这是 hibernate.hbm2ddl.auto
     * 属性的一个快捷方式。在使用嵌入式数据库时，默认为 create-drop；其他情况下默认为 none。
     * （384）spring.jpa.hibernate.naming-strategy
     * Hibernate 命名策略的全限定类名。
     * （385）spring.jpa.open-in-view
     * 注册 OpenEntityManagerInViewInterceptor，在请求的整个处理过程中，将一个 JPA EntityManager
     * 绑定到线程上。（默认值：true）
     * （386）spring.jpa.properties
     * JPA 提供方要设置的额外原生属性。
     * （387）spring.jpa.show-sql
     * 在使用 Bitronix Transaction Manager 时打开 SQL 语句日志。（默认值：false）
     * （388）spring.jta.allow-multiple-lrc
     * 在使用 Bitronix Transaction Manager 时，事务管理器是否应该允许一个事务涉及多个 LRC 资源。（默认值：false）
     * （389）spring.jta.asynchronous2-pc
     * 在使用 Bitronix Transaction Manager 时，是否异步执行两阶段提交。（默认值：false）
     * （390）spring.jta.background-recovery-interval
     * 在使用 Bitronix Transaction Manager 时，多久运行一次恢复过程，单位为分钟。（默认值：1）
     * （391）spring.jta.background-recovery-interval-seconds
     * 在使用 Bitronix Transaction Manager 时，多久运行一次恢复过程，单位为秒。（默认值：60）
     * （392）spring.jta.current-node-only-recovery
     * 在使用 Bitronix Transaction Manager 时，恢复是否要滤除不包含本 JVM 唯一 ID 的 XID。（默认值：true）
     * （393）spring.jta.debug-zero-resource-transaction
     * 在使用 Bitronix Transaction Manager 时，对于没有涉及任何资源的事务，是否要跟踪并记录它们的创建和提交
     * 调用栈。（默认值：false）
     * （394）spring.jta.default-transaction-timeout
     * 在使用 Bitronix Transaction Manager 时，默认的事务超时时间，单位为秒。（默认值：60）
     * （395）spring.jta.disable-jmx
     * 在使用 Bitronix Transaction Manager 时，是否要禁止注册 JMX MBean。（默认值：false）
     * （396）spring.jta.enabled
     * 开启 JTA 支持。（默认值：true）
     * （397）spring.jta.exception-analyzer
     * 在使用 Bitronix Transaction Manager 时用到的异常分析器。设置为 null 时使用默认异常分析器，也可以设置
     * 自定义异常分析器的全限定类名。
     * （398）spring.jta.filter-log-status
     * 在使用 Bitronix Transaction Manager 时，是否只记录必要的日志。开启该参数时能减少分段（fragment）空间
     * 用量，但调试更复杂了。（默认值：false）
     * （399）spring.jta.force-batching-enabled
     * 在使用 Bitronix Transaction Manager 时，是否批量输出至磁盘。禁用批处理会严重降低事务管理器的吞吐量。（默认值：true）
     * （400）spring.jta.forced-write-enabled
     * 在使用 Bitronix Transaction Manager 时，日志是否强制写到磁盘上。在生产环境里不要设置为 false，因为
     * 不强制写到磁盘上无法保证完整性。（默认值：true）
     * （401）spring.jta.graceful-shutdown-interval
     * 在使用 Bitronix Transaction Manager 时，要关闭的话，事务管理器在放弃事务前最多等它多少秒。（默认值：60）
     * （402）spring.jta.jndi-transaction-synchronization-registry-name
     * 在使用 Bitronix Transaction Manager 时，事务同步注册表应该绑定到哪个 JNDI 下。
     * （默认值：java:comp/TransactionSynchronizationRegistry）
     * （403）spring.jta.jndi-user-transaction-name
     * 在使用 Bitronix Transaction Manager 时，用户事务应该绑定到哪个 JNDI 下。（默认值：java:comp/UserTransaction）
     * （404）spring.jta.journal
     * 在使用 Bitronix Transaction Manager 时，要用的日志名。可以是 disk、null 或者全限定类名。（默认值：disk）
     * （405）spring.jta.log-dir
     * 事务日志目录。
     * （406）spring.jta.log-part1-filename
     * 日志分段文件 1 的名称。（默认值：btm1.tlog）
     * （407）spring.jta.log-part2-filename
     * 日志分段文件2的名称。（默认值：btm2.tlog）
     * （408）spring.jta.max-log-size-in-mb
     * 在使用 Bitronix Transaction Manager 时，日志分段文件的最大兆数。日志越大，事务就被允许在未终结状态停留
     * 越长时间。但是，如果文件大小限制得太小，事务管理器在分段满了的时候就会暂停更长时间。（默认值：2）
     * （409）spring.jta.resource-configuration-filename
     * Bitronix Transaction Manager 的配置文件名。
     * （410）spring.jta.server-id
     * 唯一标识 Bitronix Transaction Manager 实例的 ID。
     * （411）spring.jta.skip-corrupted-logs
     * 是否跳过损坏的日志文件。（默认值：false）
     * （412）spring.jta.transaction-manager-id
     * 事务管理器的唯一标识符。
     * （413）spring.jta.warn-about-zero-resource-transaction
     * 在使用 Bitronix Transaction Manager 时，是否要对执行时没有涉及任何资源的事务作出告警。（默认值：true）
     * （414）spring.mail.default-encoding
     * 默认的 MimeMessage 编码。（默认值：UTF-8）
     * （415）spring.mail.host
     * SMTP 服务器主机地址。
     * （416）spring.mail.jndi-name
     * 会话的 JNDI 名称。设置之后，该属性的优先级要高于其他邮件设置。
     * （417）spring.mail.password
     * SMTP 服务器的登录密码。
     * （418）spring.mail.port
     * SMTP 服务器的端口号。
     * （419）spring.mail.properties
     * 附加的 JavaMail 会话属性。
     * （420）spring.mail.protocol
     * SMTP 服务器用到的协议。（默认值：smtp）
     * （421）spring.mail.test-connection
     * 在启动时测试邮件服务器是否可用。（默认值：false）
     * （422）spring.mail.username
     * SMTP 服务器的登录用户名。
     * （423）spring.messages.basename
     * 逗号分隔的基本名称列表，都遵循 ResourceBundle 的惯例。本质上这就是一个全限定的 Classpath 位置，
     * 如果不包含包限定符（比如 org.mypackage），就会从 Classpath 的根部开始解析。（默认值:messages）
     * （424）spring.messages.cache-seconds
     * 加载的资源包文件的缓存失效时间，单位为秒。在设置为 -1 时，包会永远缓存。（默认值：-1）
     * （425）spring.messages.encoding
     * 消息包的编码。（默认值：UTF-8）
     * （426）spring.mobile.devicedelegatingviewresolver.enable-fallback
     * 开启降级解析支持。（默认值：false）
     * （427）spring.mobile.devicedelegatingviewresolver.enabled
     * 开启设备视图解析器。（默认值：false）
     * （428）spring.mobile.devicedelegatingviewresolver.mobile-prefix
     * 添加到移动设备视图名前的前缀。（默认值：mobile/）
     * （429）spring.mobile.devicedelegatingviewresolver.mobile-suffix
     * 添加到移动设备视图名后的后缀。
     * （430）spring.mobile.devicedelegatingviewresolver.normal-prefix
     * 添加到普通设备视图名前的前缀。
     * （431）spring.mobile.devicedelegatingviewresolver.normal-suffix
     * 添加到普通设备视图名后的后缀。
     * （432）spring.mobile.devicedelegatingviewresolver.tablet-prefix
     * 添加到平板设备视图名前的前缀。（默认值：tablet/）
     * （433）spring.mobile.devicedelegatingviewresolver.tablet-suffix
     * 添加到平板设备视图名后的后缀。
     * （434）spring.mobile.sitepreference.enabled
     * 开启 SitePreferenceHandler。（默认值：true）
     * （435）spring.mongodb.embedded.features
     * 要开启的特性列表，用逗号分隔。
     * （436）spring.mongodb.embedded.version
     * 要使用的 Mongo 版本。（默认值：2.6.10）
     * （437）spring.mustache.cache
     * 开启模板缓存。
     * （438）spring.mustache.charset
     * 模板编码。
     * （439）spring.mustache.check-template-location
     * 检查模板位置是否存在。
     * （440）spring.mustache.content-type
     * Content-Type 的值。
     * （441）spring.mustache.enabled
     * 开启 Mustache 的 MVC 视图解析。
     * （442）spring.mustache.prefix
     * 添加到模板名前的前缀。（默认值：classpath:/templates/）
     * （443）spring.mustache.suffix
     * 添加到模板名后的后缀。（默认值：.html）
     * （444）spring.mustache.view-names
     * 可解析的视图名称的白名单。
     * （445）spring.mvc.async.request-timeout
     * 异步请求处理超时前的等待时间（单位为毫秒）。如果没有设置该属性，则使用底层实现的默认超时时间，比如，
     * Tomcat 上使用 Servlet 3 时超时时间为 10 秒。
     * （446）spring.mvc.date-format
     * 要使用的日期格式（比如 dd/MM/yyyy）。
     * （447）spring.mvc.favicon.enabled
     * 开启 favicon.ico 的解析。（默认值：true）
     * （448）spring.mvc.ignore-default-model-on-redirect
     * 在重定向的场景下，是否要忽略 "默认" 模型对象的内容。（默认值：true）
     * （449）spring.mvc.locale
     * 要使用的地域配置。
     * （450）spring.mvc.message-codes-resolver-format
     * 消息代码格式（PREFIX_ERROR_CODE、POSTFIX_ERROR_CODE）。
     * （451）spring.mvc.view.prefix
     * Spring MVC 视图前缀。
     * （452）spring.mvc.view.suffix
     * Spring MVC 视图后缀。
     * （453）spring.rabbitmq.addresses
     * 客户端应该连接的地址列表，用逗号分隔。
     * （454）spring.rabbitmq.dynamic
     * 创建一个 AmqpAdmin Bean。（默认值：true）
     * （455）spring.rabbitmq.host
     * RabbitMQ 主机地址。（默认值：localhost）
     * （456）spring.rabbitmq.listener.acknowledge-mode
     * 容器的应答模式。
     * （458）spring.rabbitmq.listener.auto-startup
     * 启动时自动开启容器。（默认值：true）
     * （459）spring.rabbitmq.listener.concurrency
     * 消费者的数量下限。
     * （460）spring.rabbitmq.listener.max-concurrency
     * 消费者的数量上限。
     * （461）spring.rabbitmq.listener.prefetch
     * 单个请求里要处理的消息数。该数值不应小于事务数（如果用到的话）。
     * （462）spring.rabbitmq.listener.transaction-size
     * 一个事务里要处理的消息数。为了保证效果，应该不大于预先获取的数量。
     * （463）spring.rabbitmq.password
     * 进行身份验证的密码。
     * （464）spring.rabbitmq.port
     * RabbitMQ 端口。（默认值：5672）
     * （465）spring.rabbitmq.requested-heartbeat
     * 请求心跳超时，单位为秒；0 表示不启用心跳。
     * （466）spring.rabbitmq.ssl.enabled
     * 开启 SSL 支持。（默认值：false）
     * （467）spring.rabbitmq.ssl.key-store
     * 持有 SSL 证书的 KeyStore 路径。
     * （468）spring.rabbitmq.ssl.key-store-password
     * 访问 KeyStore 的密码。
     * （469）spring.rabbitmq.ssl.trust-store
     * 持有 SSL 证书的 TrustStore。
     * （470）spring.rabbitmq.ssl.trust-store-password
     * 访问 TrustStore 的密码。
     * （471）spring.rabbitmq.username
     * 进行身份验证的用户名。
     * （472）spring.rabbitmq.virtual-host
     * 在连接 RabbitMQ 时的虚拟主机。
     * （473）spring.redis.database
     * 连接工厂使用的数据库索引。（默认值：0）
     * （474）spring.redis.host
     * Redis 服务器主机地址。（默认值：localhost）
     * （475）spring.redis.password
     * Redis 服务器的登录密码。
     * （476）spring.redis.pool.max-active
     * 连接池在指定时间里能分配的最大连接数。负数表示无限制。（默认值：8）
     * （477）spring.redis.pool.max-idle
     * 连接池里的最大空闲连接数。负数表示空闲连接数可以是无限大。（默认值：8）
     * （478）spring.redis.pool.max-wait
     * 当连接池被耗尽时，分配连接的请求应该在抛出异常前被阻塞多长时间（单位为秒）。负数表示一直阻塞。（默认值：-1）
     * （479）spring.redis.pool.min-idle
     * 连接池里要维持的最小空闲连接数。该属性只有在设置为正数时才有效。（默认值：0）
     * （480）spring.redis.port
     * Redis 服务器端口。（默认值：6379）
     * （481）spring.redis.sentinel.master
     * Redis 服务器的名字。
     * （482）spring.redis.sentinel.nodes
     * 形如 "主机:端口" 配对的列表，用逗号分隔。
     * （483）spring.redis.timeout
     * 连接超时时间，单位为秒。（默认值：0）
     * （484）spring.resources.add-mappings
     * 开启默认资源处理。（默认值：true）
     * （485）spring.resources.cache-period
     * 资源处理器对资源的缓存周期，单位为秒。
     * （486）spring.resources.chain.cache
     * 对资源链开启缓存。（默认值：true）
     * （487）spring.resources.chain.enabled
     * 开启 Spring 资源处理链。（默认关闭的，除非至少开启了一个策略）
     * （488）spring.resources.chain.html-application-cache
     * 开启 HTML5 应用程序缓存证明重写。（默认值：false）
     * （489）spring.resources.chain.strategy.content.enabled
     * 开启内容版本策略。（默认值：false）
     * （490）spring.resources.chain.strategy.content.paths
     * 要运用于版本策略的模式列表，用逗号分隔。（默认值：[/**]）
     * （491）spring.resources.chain.strategy.fixed.enabled
     * 开启固定版本策略。（默认值：false）
     * （492）spring.resources.chain.strategy.fixed.paths
     * 要运用于固定版本策略的模式列表，用逗号分隔。
     * （493）spring.resources.chain.strategy.fixed.version
     * 用于固定版本策略的版本字符串。
     * （494）spring.resources.static-locations
     * 静态资源位置。默认为 classpath:[/META-INF/resources/, /resources/, /static/, /public/]
     * 加上 context:/（Servlet 上下文的根目录）。
     * （495）spring.sendgrid.password
     * SendGrid 密码。
     * （496）spring.sendgrid.proxy.host
     * SendGrid 代理主机地址。
     * （497）spring.sendgrid.proxy.port
     * SendGrid 代理端口。
     * （498）spring.sendgrid.username
     * SendGrid 用户名。
     * （499）spring.social.auto-connection-views
     * 针对所支持的提供方开启连接状态视图。（默认值：false）
     * （500）spring.social.facebook.app-id
     * 应用程序 ID。
     * （501）spring.social.facebook.app-secret
     * 应用程序的密钥。
     * （502）spring.social.linkedin.app-id
     * 应用程序 ID。
     * （503）spring.social.linkedin.app-secret
     * 应用程序的密钥。
     * （504）spring.social.twitter.app-id
     * 应用程序 ID。
     * （505）spring.social.twitter.app-secret
     * 应用程序的密钥。
     * （506）spring.thymeleaf.cache
     * 开启模板缓存。（默认值：true）
     * （507）spring.thymeleaf.check-template-location
     * 检查模板位置是否存在。（默认值：true）
     * （508）spring.thymeleaf.content-type
     * Content-Type 的值。（默认值：text/html）
     * （509）spring.thymeleaf.enabled
     * 开启 MVC Thymeleaf 视图解析。（默认值：true）
     * （510）spring.thymeleaf.encoding
     * 模板编码。（默认值：UTF-8）
     * （511）spring.thymeleaf.excluded-view-names
     * 要被排除在解析之外的视图名称列表，用逗号分隔。
     * （512）spring.thymeleaf.mode
     * 要运用于模板之上的模板模式。另见 StandardTemplateModeHandlers。（默认值：HTML5）
     * （513）spring.thymeleaf.prefix
     * 在构建 URL 时添加到视图名称前的前缀。（默认值：classpath:/templates/）
     * （514）spring.thymeleaf.suffix
     * 在构建 URL 时添加到视图名称后的后缀。（默认值：.html）
     * （515）spring.thymeleaf.template-resolver-order
     * Thymeleaf 模板解析器在解析器链中的顺序。默认情况下，它排在第一位。顺序从 1 开始，
     * 只有在定义了额外的 TemplateResolver Bean 时才需要设置这个属性。
     * （516）spring.thymeleaf.view-names
     * 可解析的视图名称列表，用逗号分隔。
     * （517）spring.velocity.allow-request-override
     * HttpServletRequest 的属性是否允许覆盖（隐藏）控制器生成的同名模型属性。
     * （518）spring.velocity.allow-session-override
     * HttpSession 的属性是否允许覆盖（隐藏）控制器生成的同名模型属性。
     * （519）spring.velocity.cache
     * 开启模板缓存。
     * （520）spring.velocity.charset
     * 模板编码。
     * （521）spring.velocity.check-template-location
     * 检查模板位置是否存在。
     * （522）spring.velocity.content-type
     * Content-Type 的值。
     * （523）spring.velocity.date-tool-attribute
     * DateTool 辅助对象在视图的 Velocity 上下文里呈现的名字。
     * （524）spring.velocity.enabled
     * 开启 Velocity 的 MVC 视图解析。
     * （525）spring.velocity.expose-request-attributes
     * 在模型合并到模板前，是否要把所有的请求属性添加到模型里。
     * （526）spring.velocity.expose-session-attributes
     * 在模型合并到模板前，是否要把所有的 HttpSession 属性添加到模型里。
     * （527）spring.velocity.expose-spring-macro-helpers
     * 是否发布供 Spring 宏程序库使用的 RequestContext，并将其名命为 springMacroRequestContext。
     * （528）spring.velocity.number-tool-attribute
     * NumberTool 辅助对象在视图的 Velocity 上下文里呈现的名字。
     * （529）spring.velocity.prefer-file-system-access
     * 加载模板时优先通过文件系统访问。文件系统访问能够实时检测到模板变更。（默认值：true）
     * （530）spring.velocity.prefix
     * 在构建 URL 时添加到视图名称前的前缀。
     * （531）spring.velocity.properties
     * 额外的 Velocity 属性。
     * （532）spring.velocity.request-context-attribute
     * 所有视图里使用的 RequestContext 属性的名称。
     * （533）spring.velocity.resource-loader-path
     * 模板路径。（默认值：classpath:/templates/）
     * （534）spring.velocity.suffix
     * 在构建 URL 时添加到视图名称后的后缀。
     * （535）spring.velocity.toolbox-config-location
     * Velocity Toolbox 的配置位置，比如 /WEB-INF/toolbox.xml。自动加载 Velocity Tools 工具定义文件，
     * 将所定义的全部工具发布到指定的作用域内。
     * （536）spring.velocity.view-names
     * 可解析的视图名称白名单。
     * （537）spring.view.prefix
     * Spring MVC 视图前缀。
     * （538）spring.view.suffix
     * Spring MVC 视图后缀。
     */
    public static void main(String[] args) {

    }

}
