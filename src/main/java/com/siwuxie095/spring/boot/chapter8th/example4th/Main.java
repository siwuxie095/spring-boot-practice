package com.siwuxie095.spring.boot.chapter8th.example4th;

/**
 * @author Jiajing Li
 * @date 2021-04-30 22:56:58
 */
public class Main {

    /**
     * 推上云端
     *
     * 服务器硬件的购买和维护成本很高。大流量很难通过适当扩展服务器去处理，这种做法在某些组织中甚至是禁忌。现如今，
     * 相比在自己的数据中心运行应用程序，把它们部署到云上是更引人注目，而且划算的做法。
     *
     * 目前有多个云平台可供选择，而那些提供 Platform as a Service(PaaS)能力的平台无疑是最有吸引力的。PaaS 提
     * 供了现成的应用程序部署平台，带有附加服务（比如数据库和消息代理），可以绑定到应用程序上。除此之外，当你的应
     * 用程序要求提供更大的马力时，云平台能轻松实现应用程序在运行时向上（或向下）伸缩，只需添加或删除实例即可。
     *
     * 之前已经把阅读列表应用程序部署到了传统的应用服务器上，现在再试试将其部署到云上。这里将把应用程序部署到
     * Cloud Foundry 和 Heroku 这两个著名的 PaaS 平台上。
     *
     *
     *
     * 1、部署到 Cloud Foundry
     *
     * Cloud Foundry 是 Pivotal 的 PaaS 平台。这家公司也赞助了 Spring Framework 和 Spring 平台里的其他库。
     * Cloud Foundry 里最吸引人的特点之一就是它既有开源版本，也有多个商业版本。你可以选择在何处运行 Cloud
     * Foundry。它甚至还可以在公司数据中心的防火墙后运行，提供私有云。
     *
     * 这里打算将阅读列表应用程序部署到 Pivotal Web Services（PWS）上。这是一个由 Pivotal 托管的公共 Cloud
     * Foundry，地址是 http://run.pivotal.io。如果想使用 PWS，你可以注册一个账号。PWS 提供为期 60 天的免费
     * 试用，在试用期间无需提交任何信用卡信息。
     *
     * 在注册了 PWS 后，可以从 https://console.run.pivotal.io/tools 下载并安装 cf 命令行工具。你可以通过这
     * 个工具将应用程序推上 Cloud Foundry。但你要先用这个工具登录自己的 PWS 账号。
     *
     * cf login -a https://api.run.pivotal.io
     *
     * 现在已经可以把阅读列表应用程序传到云上了。实际上，这里的项目已经做好了部署到 Cloud Foundry 的准备，只需
     * 使用 cf push 命令把它推上去就好。
     *
     * cf push readinglist -p build/libs/readinglist.war
     *
     * cf push 命令的第一个参数指定了应用程序在 Cloud Foundry 里的名称。这个名称将被用作托管应用程序的子域名。
     * 本例中，应用程序的完整域名将是 http://readinglist.cfapps.io。因此，应用程序的命名很重要。名字必须独
     * 一无二，这样才不会和 Cloud Foundry 里部署的其他应用程序（包括其他用户部署的应用程序）发生冲突。
     *
     * 因为空想一个独一无二的名称有点困难，所以 cf push 命令提供了一个 --random-route 选项，可以为你随机产生
     * 一个子域名。下面的例子演示了如何上传阅读列表应用程序，生成一个随机的子域名。
     *
     * cf push readinglist -p build/libs/readinglist.war --random-route
     *
     * 在使用了 --random-route 后，还是要设定应用程序名称。会有两个随机选择的单词添加到后面，组成子域名。这里
     * 尝试的时候，生成的子域名是 readinglist-gastroenterological-stethoscope。
     *
     * PS：不仅仅是 WAR 文件：虽然部署的应用程序是一个 WAR 文件，但 Cloud Foundry 也可以部署其他格式的
     * Spring Boot 应用程序，包括可执行的 JAR 文件，甚至 Spring Boot CLI 开发的未经编译的 Groovy 脚本。
     *
     * 如果一切顺利，这里部署的应用程序应该可以处理请求了。假设子域名是 readinglist，你可以用浏览器访问
     * http://readinglist.cfapps.io，看看效果。你应该会被引导到登录页。回想一下，数据库迁移脚本中插入
     * 了一个名为 craig 的用户，密码是 password，可以以此登录应用程序。
     *
     * 你可以在应用程序里随便点点，加几本书。所有的东西都可以运行，但还是有点不对劲。如果重启应用程序（通过 cf
     * restart 命令），重新登录，你会发现阅读列表清空了。你在重启前添加的书都不见了。
     *
     * 应用程序重启后数据消失，原因在于还在使用内嵌的 H2 数据库。可以通过 Actuator 的 /health 端点验证推测。
     * 它返回的信息大约是这样的：
     *
     * {
     *   "status": "UP",
     *   "diskSpace": {
     *     "status": "UP",
     *     "free": 834236510208,
     *     "threshold": 10485760
     *   },
     *   "db": {
     *     "status": "UP",
     *     "database": "H2",
     *     "hello": 1
     *   }
     * }
     *
     * 请注意 db.database 属性的值。它证实了之前的怀疑 —— 果然用的是内嵌的 H2 数据库。需要修复这个问题。
     *
     * 实际上，Cloud Foundry 以市集服务（marketplace services）的形式提供了一些数据库以供选择，包括 MySQL
     * 和 PostgreSQL。因为已经在项目里放了 PostgreSQL 的 JDBC 驱动，所以就使用市集里的 PostgreSQL 服务，
     * 名字是 elephantsql。
     *
     * elephantsql 服务也有不少计划可选，小到开发用的小型数据库，大到工业级生产数据库。elephantsql 的完整计
     * 划列表可以通过 cf marketplace 命令获得。
     *
     * cf marketplace -s elephantsql
     *
     * 比较严谨的生产级数据库计划都是要付费的。你可以选择你所期望的计划。假设你会选择免费的 turtle。
     *
     * 创建数据库服务的实例，需要使用 cf create-service 命令，指定服务名、计划名和实例名。
     *
     * cf create-service elephantsql turtle readinglistdb
     *
     * 服务创建后，需要通过 cf bind-service 命令将它绑定到应用程序上。
     *
     * cf bind-service readinglist readinglistdb
     *
     * 将一个服务绑定到应用程序上不过就是为应用程序提供了连接服务的细节，这里用的是名为 VCAP_SERVICES 的环境变
     * 量。它不会通过修改应用程序来使用服务。
     *
     * 可以改写阅读列表应用程序，读取 VCAP_SERVICES，使用其中提供的信息来连接数据库服务。但其实完全不用这么做。
     * 实际上，只需用 cf restage 命令重启应用程序就可以了：
     *
     * cf restage readinglist
     *
     * cf restage 命令会让 Cloud Foundry 重新部署应用程序，并重新计算 VCAP_SERVICES 的值。如此一来，应用程
     * 序会在 Spring 应用程序上下文里声明一个引用了绑定数据库服务的 DataSource Bean，用它来替换原来的
     * DataSource Bean。这样就能抛开内嵌的 H2 数据库，使用 elephantsql 提供的 PostgreSQL 服务了。
     *
     * 现在来试一下。登录应用程序，添加几本书，然后重启。重启之后你所添加的书应该还在列表里，因为它们已经被持久化
     * 在绑定的数据库服务里，而非内嵌的 H2 数据库里。再访问一下 Actuator 的 /health 端点，返回的内容能证明在
     * 使用 PostgreSQL：
     *
     * {
     *   "status": "UP",
     *   "diskSpace": {
     *     "status": "UP",
     *     "free": 834331525120,
     *     "threshold": 10485760
     *   },
     *   "db": {
     *     "status": "UP",
     *     "database": "PostgreSQL",
     *     "hello": 1
     *   }
     * }
     *
     * Cloud Foundry 对 Spring Boot 应用程序部署而言是极佳的 PaaS，Cloud Foundry 与 Spring 项目搭配可谓
     * 如虎添翼。但 Cloud Foundry 并非 Spring Boot 应用程序在 PaaS 方面的唯一选择。下面来看看如何将阅读列表
     * 应用程序部署到另一个流行的 PaaS 平台：Heroku。
     *
     *
     *
     * 2、部署到 Heroku
     *
     * Heroku 在应用程序部署上有一套独特的方法，不用部署完整的部署产物。Heroku 为你的应用程序安排了一个 Git 仓
     * 库。每当你向仓库里提交代码时，它都会自动为你构建并部署应用程序。如果还是解决不了问题，则需要先将项目目录初
     * 始化为 Git 仓库。
     *
     * git init
     *
     * 这样 Heroku 的命令行工具就能自动把远程 Heroku Git 仓库添加到项目里。
     *
     * 现在可以通过 Heroku 的命令行工具在 Heroku 中设置应用程序了。这里使用 apps:create 命令。
     *
     * heroku apps:create readinglist
     *
     * 这里要求 Heroku 将应用程序命名为 readinglist。这将成为 Git 仓库的名字，同时也是应用程序在 herokuapps
     * .com 的子域名。你需要确定这个名字唯一，因为不能有同名应用程序。此外，也可以让 Heroku 替你生成一个独特的
     * 名字（比如 fierce-river-8120 或 serene-anchorage-6223）。
     *
     * apps:create 命令会在 https://git.heroku.com/readinglist.git 创建一个远程 Git 仓库，并在本地项目
     * 的 Git 配置里添加一个名为 heroku 的远程仓库引用。这样就能通过 git 命令将项目推送到 Heroku 了。
     *
     * Heroku 里的项目已经设置完毕，但现在还不能进行推送。Heroku 需要你提供一个名为 Procfile 的文件，告诉
     * Heroku 应用程序构建后该如何运行。对于阅读列表应用程序而言，需要告诉 Heroku，构建生成的 WAR 文件要当
     * 作可执行 JAR 文件来运行，这里使用 java 命令。假设应用程序是用 Gradle 来构建的，只需要如下一行内容的
     * Procfile：
     *
     * web: java -Dserver.port=$PORT -jar build/libs/readinglist.war
     *
     * PS：当前使用的项目会实际生成一个可执行的 WAR 文件。但对 Heroku 来说，它和可执行的 JAR 文件没什么区别。
     *
     * 另一方面，如果你使用 Maven 来构建项目，JAR 文件的路径就会有所不同。Heroku 需要到 target 目录，而不是
     * build/libs 目录里寻找可执行 WAR 文件。具体如下：
     *
     * web: java -Dserver.port=$PORT -jar target/readinglist.war
     *
     * 不管何种情况，你都需要像例子中那样设置 server.port 属性。这样内嵌的 Tomcat 服务器才能在 Heroku 分配的
     * 端口上（通过 $PORT 变量指定）启动。
     *
     * 现在差不多可以把应用程序推上 Heroku 了，但 Gradle 构建说明还要稍作调整。Heroku 构建应用程序时，会执行一
     * 个名为 stage 的任务，因此需要在 build.gradle 里添加这个 stage 任务。
     *
     * task stage(dependsOn: ['build']) {
     *     }
     *
     * 如你所见，这个 stage 任务什么也没做，但依赖了 build 任务。于是，在 Heroku 使用 stage 任务构建应用程序
     * 会触发 build 任务，生成的 JAR 文件会放在 build/libs 目录里。
     *
     * 你还需要告诉 Heroku 用什么 Java 版本来构建并运行应用程序。这样 Heroku 才能用合适的版本来运行它。最简单
     * 的方法是在项目根目录里创一个名为 system.properties 的文件，在其中设置 java.runtime.version 属性：
     *
     * java.runtime.version=1.7
     *
     * 现在就可以将项目推上 Heroku 了。和前面说一样，只需将代码推到远程 Git 仓库，Heroku 会搞定其他事情。
     *
     * git commit -am "Initial commit"
     * git push heroku master
     *
     * 然后，Heroku 会根据找到的构建说明文件，使用 Maven 或 Gradle 进行构建，再用 Procfile 里的指令来运行应
     * 用程序。就绪后，你可以用浏览器打开 http://{app name}.herokuapp.com，这里的 {app name} 就是你在
     * apps:create 里给应用程序起的名字。例如，这里在部署时将应用程序命名为 readinglist，所以它的 URL 就是
     * http://readinglist.herokuapps.com。
     *
     * 你可以在应用程序里随便点点，但要访问一下 /health 端点。db.database 属性会告诉你应用程序正在使用内嵌的
     * H2 数据库。应该把它换成 PostgreSQL 服务。
     *
     * 可以通过 Heroku 命令行工具的 addons:add 命令创建并绑定一个 PostgreSQL 服务。
     *
     * heroku addons:add heroku-postgresql:hobby-dev
     *
     * 这里要使用名为 heroku-postgresql 的附加服务。这是 Heroku 提供的 PostgreSQL 服务。这里还要求使用该服
     * 务的 hobby-dev 计划，这是免费的。
     *
     * 在 PostgreSQL 服务创建并绑定到应用程序后，Heroku 会自动重启应用程序以保证绑定生效。但即便如此，在访问
     * /health 端点时仍然会看到应用程序还在使用内嵌的 H2 数据库。那是因为 H2 的自动配置仍然有效，谁也没告诉
     * Spring Boot 要用 PostgreSQL 代替 H2。
     *
     * 一个办法是设置 spring.datasource.* 属性，做法和将应用程序部署到应用服务器上时一样。所需要的信息能在数据
     * 库服务的仪表板上找到，可以用 addons:open 命令打开仪表板。
     *
     * heroku addons:open waking-carefully-3728
     *
     * 在这个例子里，数据库实例的名字是 waking-carefully-3728。该命令会在 Web 浏览器里打开仪表板页面，其中包含
     * 了你所需要的全部连接信息，包括主机名、数据库名和账户信息。总之，设置 spring.datasource.* 属性所需的一切
     * 信息都在这里了。
     *
     * 还有一个更简单的办法，与其自己查找那些信息，再设置到属性里，为什么不让 Spring 来查找信息呢？实际上，这就是
     * Spring Cloud Connectors 的用途。它可以用在 Cloud Foundry 和 Heroku 上，查找绑定到应用程序上的所有服
     * 务，并自动配置应用程序，以便使用那些服务。
     *
     * 只需在项目中加入 Spring Cloud Connectors 依赖即可。
     *
     * 如果你用的是 Maven，则添加如下 Spring Cloud Connectors <dependency>：
     *
     * <dependency>
     *      <groupId>org.springframework.boot</groupId>
     *      <artifactId>spring-boot-starter-cloud-connectors</artifactId>
     * </dependency>
     *
     * 只有激活 cloud Profile，Spring Cloud Connectors 才会工作。要在 Heroku 里激活 cloud Profile，
     * 可以使用 config:set 命令：
     *
     * heroku config:set SPRING_PROFILES_ACTIVE="cloud"
     *
     * 现在项目里有了 Spring Cloud Connectors 依赖，cloud Profile 也激活了。可以再推一次应用程序。
     *
     * git commit -am "Add cloud connector"
     * git push heroku master
     *
     * 应用程序启动后，登入应用程序，查看 /health 端点。它应该显示应用程序已经连接到了 PostgreSQL 数据库：
     *
     * "db": {
     *   "status": "UP",
     *   "database": "PostgreSQL",
     *   "hello": 1
     * }
     *
     * 现在应用程序已经部署到云上，可以接受世界各地的请求了！
     */
    public static void main(String[] args) {

    }

}
