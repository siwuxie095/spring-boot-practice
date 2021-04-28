package com.siwuxie095.spring.boot.chapter7th.example5th;

/**
 * @author Jiajing Li
 * @date 2021-04-28 21:47:36
 */
@SuppressWarnings("all")
public class Main {

    /**
     * 定制 Actuator
     *
     * 虽然 Actuator 提供了很多运行中 Spring Boot 应用程序的内部工作细节，但难免和你的需求有所偏差。
     * 也许你并不需要它提供的所有功能，想要关闭一些也说不定。或者，你需要对 Actuator 稍作扩展，增加一
     * 些自定义的度量信息，以满足你对应用程序的需求。
     *
     * 实际上，Actuator 有多种定制方式，包括以下五项。
     * （1）重命名端点。
     * （2）启用和禁用端点。
     * （3）自定义度量信息。
     * （4）创建自定义仓库来存储跟踪数据。
     * （5）插入自定义的健康指示器。
     *
     * 接下来，会了解如何定制 Actuator，以满足需要。先来看一个最简单的定制：重命名 Actuator 端点。
     *
     *
     *
     * 1、修改端点 ID
     *
     * 每个 Actuator 端点都有一个 ID 用来决定端点的路径，比方说，/beans 端点的默认 ID 就是 beans。
     *
     * 如果端点的路径是由 ID 决定的，那么可以通过修改 ID 来改变端点的路径。你要做的就是设置一个属性，
     * 属性名是 endpoints.endpoint-id.id。
     *
     * 这里用 /shutdown 端点来做个演示，它会响应发往 /shutdown 的 POST 请求。假设你想让它处理发往
     * /kill 的 POST 请求，可以通过如下 YAML 为 /shutdown 赋予一个新的 ID，也就是新的路径：
     *
     * endpoints:
     *   shutdown:
     *     id: kill
     *
     * 重命名端点、修改其路径的理由很多。最明显的理由就是，端点的命名要和团队的术语保持一致。你也可能想
     * 重命名端点，让那些熟悉默认名称的人找不到它，借此增加一些安全感。
     *
     * 遗憾的是，重命名端点并不能真的起到保护作用，顶多是让黑客慢点找到它们。
     *
     * 后续会看到如何保护这些 Actuator 端点。现在先来看看如何禁用某个（或全部）不希望别人访问的端点。
     *
     *
     *
     * 2、启用和禁用端点
     *
     * 虽然 Actuator 的端点都很有用，但你不一定需要全部这些端点。默认情况下，所有端点（除了 /shutdown）
     * 都启用。已经看过如何设置 endpoints.shutdown.enabled 为 true，以此开启 /shutdown 端点。用同
     * 样的方式，你可以禁用其他的端点，将 endpoints.endpoint-id.enabled 设置为 false。
     *
     * 例如，要禁用 /metrics 端点，你要做的就是将 endpoints.metrics.enabled 属性设置为 false。在
     * application.yml 里做如下设置：
     *
     * endpoints:
     *   metrics:
     *     enabled: false
     *
     * 如果你只想打开一两个端点，那就先禁用全部端点，然后启用那几个你要的，这样更方便。例如，考虑如下
     * application.yml 片段：
     *
     * endpoints:
     *   enabled: false
     *   metrics:
     *     enabled: true
     *
     * 正如以上片段所示，endpoints.enabled 设置为 false 就能禁用 Actuator 的全部端点，然后将
     * endpoints.metrics.enabled 设置为 true 重新启用 /metrics 端点。
     *
     *
     *
     * 3、添加自定义度量信息
     *
     * 之前看到了如何从 /metrics 端点获得运行中应用程序的内部度量信息，包括内存、垃圾回收和线程信息。
     * 这些都是非常有用且信息量很大的度量值，但你可能还想定义自己的度量，用来捕获应用程序中的特定信息。
     *
     * 比方说，想要知道用户往阅读列表里保存了多少次图书，最简单的方法就是在每次调用 ReadingListController
     * 的 addToReadingList() 方法时增加计数器值。计数器很容易实现，但这个不断变化的总计值如何同 /metrics
     * 端点发布的度量信息一起发布出来呢？
     *
     * 再假设想要获得最后保存图书的时间戳。时间戳可以通过调用 System.currentTimeMillis() 来获取，
     * 但如何在 /metrics 端点里报告该时间戳呢？
     *
     * 实际上，自动配置允许 Actuator 创建 CounterService 的实例，并将其注册为 Spring 的应用程序
     * 上下文中的 Bean。CounterService 这个接口里定义了三个方法，分别用来增加、减少或重置特定名称
     * 的度量值，代码如下：
     *
     * package org.springframework.boot.actuate.metrics;
     *
     * public interface CounterService {
     *       void increment(String metricName);
     *       void decrement(String metricName);
     *       void reset(String metricName);
     * }
     *
     * Actuator 的自动配置还会配置一个 GaugeService 类型的 Bean。该接口与 CounterService 类似，
     * 能将某个值记录到特定名称的度量值里。GaugeService 看起来是这样的：
     *
     * package org.springframework.boot.actuate.metrics;
     *
     * public interface GaugeService {
     *       void submit(String metricName, double value);
     * }
     *
     * 你无需实现这些接口。Spring Boot 已经提供了两者的实现。这里所要做的就是把它们的实例注入所需的
     * Bean，在适当的时候调用其中的方法，更新想要的度量值。
     *
     * 针对上文提到的需求，需要把 CounterService 和 GaugeService Bean 注入 ReadingListController，
     * 然后在 addToReadingList() 方法里调用其中的方法。如下是 ReadingListController 里的相关变动：
     *
     * @Controller
     * @RequestMapping("/")
     * @ConfigurationProperties("amazon")
     * public class ReadingListController {
     *
     *     private ReadingListRepository readingListRepository;
     *     private AmazonProperties amazonConfig;
     *     private CounterService counterService;
     *     private GaugeService gaugeService;
     *
     *     @Autowired
     *     public ReadingListController(ReadingListRepository readingListRepository,
     *                                  AmazonProperties amazonConfig, CounterService counterService,
     *                                  GaugeService gaugeService) {
     *         this.readingListRepository = readingListRepository;
     *         this.amazonConfig = amazonConfig;
     *         this.counterService = counterService;
     *         this.gaugeService = gaugeService;
     *     }
     *
     *     ...
     *
     *     @RequestMapping(method= RequestMethod.POST)
     *     public String addToReadingList(Reader reader, Book book) {
     *         book.setReader(reader);
     *         readingListRepository.save(book);
     *         counterService.increment("books.saved");
     *         gaugeService.submit("books.save.time", System.currentTimeMillis());
     *         return "redirect:/";
     *     }
     *
     * }
     *
     * 修改后的 ReadingListController 使用了自动织入机制，通过控制器的构造方法注入 CounterService
     * 和 GaugeService，随后把它们保存在实例变量里。此后，addToReadingList() 方法每次处理请求时都会
     * 调用 counterService.increment("books.saved") 和 gaugeService.submit("books.last.saved")
     * 来调整度量值。
     *
     * 尽管 CounterService 和 GaugeService 用起来很简单，但还是有一些度量值很难通过增加计数器或记录
     * 指标值来捕获。对于那些情况，可以实现 PublicMetrics 接口，提供自己需要的度量信息。该接口定义了一
     * 个 metrics() 方法，返回一个 Metric 对象的集合：
     *
     * package org.springframework.boot.actuate.endpoint;
     *
     * public interface PublicMetrics {
     *   Collection<Metric<?>> metrics();
     * }
     *
     * 为了解 PublicMetrics 的使用方法，这里假设想报告一些源自 Spring 应用程序上下文的度量值 —— 应用
     * 程序上下文启动的时间、Bean 及 Bean 定义的数量，这些都包含进来会很有意思。顺便再报告一下添加了
     * @Controller 注解的 Bean 的数量。如下代码给出了相应 PublicMetrics 实现的代码。
     *
     * @Component
     * public class ApplicationContextMetrics implements PublicMetrics {
     *
     *     private ApplicationContext context;
     *
     *     @Autowired
     *     public ApplicationContextMetrics(ApplicationContext context) {
     *         this.context = context;
     *     }
     *
     *     @Override
     *     public Collection<Metric<?>> metrics() {
     *         List<Metric<?>> metrics = new ArrayList<Metric<?>>();
     *         metrics.add(new Metric<Long>("spring.context.startup-date",
     *                 context.getStartupDate()));
     *         metrics.add(new Metric<Integer>("spring.beans.definitions",
     *                 context.getBeanDefinitionCount()));
     *         metrics.add(new Metric<Integer>("spring.beans",
     *                 context.getBeanNamesForType(Object.class).length));
     *         metrics.add(new Metric<Integer>("spring.controllers",
     *                 context.getBeanNamesForAnnotation(Controller.class).length));
     *         return metrics;
     *     }
     *
     * }
     *
     * Actuator 会调用 metrics() 方法，收集 ApplicationContextMetrics 提供的度量信息。该方法调用
     * 了所注入的 ApplicationContext 上的方法，获取想要报告为度量的数量。每个度量值都会创建一个
     * Metrics 实例，指定度量的名称和值，将其加入要返回的列表。
     *
     * 创建 ApplicationContextMetrics，并在 ReadingListController 里使用 CounterService 和
     * GaugeService 之后，可以在 /metrics 端点的响应中找到如下条目：
     *
     * {
     *   ...
     *   spring.context.startup-date: 1429398980443,
     *   spring.beans.definitions: 261,
     *   spring.beans: 272,
     *   spring.controllers: 2,
     *   books.count: 1,
     *   gauge.books.save.time: 1429399793260,
     *   ...
     * }
     *
     * 当然，这些度量的实际值会根据添加了多少书、何时启动应用程序及何时保存最后一本书而发生变化。在这个
     * 例子里，你一定会好奇为什么 spring.controllers 是 2。因为这里算上了 ReadingListController
     * 以及 Spring Boot 提供的 BasicErrorController。
     *
     *
     *
     * 4、创建自定义跟踪仓库
     *
     * 默认情况下，/trace 端点报告的跟踪信息都存储在内存仓库里，100 个条目封顶。一旦仓库满了，就开始
     * 移除老的条目，给新的条目腾出空间。在开发阶段这没什么问题，但在生产环境中，大流量会造成跟踪信息
     * 还没来得及看就被丢弃。
     *
     * 为了避免这个问题，你可以声明自己的 InMemoryTraceRepository Bean，将它的容量调整至 100 以
     * 上。如下配置类可以将容量调整至 1000 个条目：
     *
     * @Configuration
     * public class ActuatorConfig {
     *
     *     @Bean
     *     public InMemoryTraceRepository traceRepository() {
     *         InMemoryTraceRepository traceRepo = new InMemoryTraceRepository();
     *         traceRepo.setCapacity(1000);
     *         return traceRepo;
     *     }
     *
     * }
     *
     * 仓库容量翻了 10 倍，跟踪信息的保存时间应该会更久。不过，繁忙到一定程度，应用程序还是可能在你查
     * 看这些信息前将其丢弃。这是一个内存存储的仓库，还要避免容量增长太多，影响应用程序的内存使用。
     *
     * 除了上述方法，还可以将那些跟踪条目存储在其他地方 —— 既不消耗内存，又能长久保存的地方。只需实现
     * Spring Boot 的 TraceRepository 接口即可：
     *
     * package org.springframework.boot.actuate.trace;
     * import java.util.List;
     * import java.util.Map;
     *
     * public interface TraceRepository {
     *   List<Trace> findAll();
     *   void add(Map<String, Object> traceInfo);
     * }
     *
     * 如你所见，TraceRepository 只要求实现两个方法：一个方法查找所有存储的 Trace 对象，另一个保存
     * 了一个 Trace，包含跟踪信息的 Map 对象。
     *
     * 作为演示，假设创建了一个使用 MongoDB 数据库存储跟踪信息的 TraceRepository 实例。如下代码演
     * 示了如何实现这个 TraceRepository。
     *
     * @Service
     * public class MongoTraceRepository implements TraceRepository {
     *
     *     private MongoOperations mongoOps;
     *
     *     @Autowired
     *     public MongoTraceRepository(MongoOperations mongoOps) {
     *         this.mongoOps = mongoOps;
     *     }
     *
     *     @Override
     *     public void add(Map<String, Object> traceInfo) {
     *         mongoOps.save(new Trace(new Date(), traceInfo));
     *     }
     *
     *     @Override
     *     public List<Trace> findAll() {
     *         return mongoOps.findAll(Trace.class);
     *     }
     *
     * }
     *
     * findAll() 方法很直白，用注入的 MongoOperations 来查找全部 Trace 对象。add() 方法稍微有趣
     * 一点，用当前时间和含有跟踪信息的 Map 创建了一个 Trace 对象，然后通过 MongoOperations.save()
     * 将其保存下来。唯一的问题是，MongoOperations 是哪里来的？
     *
     * 为了使用 MongoTraceRepository，需要保证 Spring 应用程序上下文里先有一个 MongoOperations
     * Bean。得益于 Spring Boot 的起步依赖和自动配置，做到这一点只需添加 MongoDB 起步依赖即可。
     *
     * 如果你用的是 Maven，则需要如下依赖：
     *
     * <dependency>
     *    <groupId>org.springframework.boot</groupId>
     *    <artifactId>spring-boot-starter-data-mongodb</artifactId>
     *  </dependency>
     *
     * 添加了这个起步依赖后，Spring Data MongoDB 和所依赖的库会添加到应用程序的 Classpath 里。
     * Spring Boot 会自动配置所需的 Bean，以便使用 MongoDB 数据库。这些 Bean 里就包括
     * MongoOperations。另外，你需要确保和 MongoOperations 通讯的 MongoDB 服务器正常运行。
     *
     *
     *
     * 5、插入自定义健康指示器
     *
     * Actuator 自带了很多健康指示器，能满足常见需求，比如报告应用程序使用的数据库和消息代理的健康情
     * 况。但如果你的应用程序需要和一些没有健康指示器的系统交互，那该怎么办呢？
     *
     * 这里的阅读列表里有指向 Amazon 的图书链接，可以报告一下 Amazon 是否可以访问。当然，Amazon
     * 不太可能宕机，但不怕一万就怕万一，所以为 Amazon 创建一个健康指示器吧。如下代码演示了相关
     * HealthIndicator 的实现。
     *
     * @Component
     * public class AmazonHealth implements HealthIndicator {
     *
     *     @Override
     *     public Health health() {
     *
     *         try {
     *             RestTemplate rest = new RestTemplate();
     *             rest.getForObject("http://www.amazon.com", String.class);
     *             return Health.up().build();
     *         } catch (Exception e) {
     *             return Health.down().build();
     *         }
     *     }
     *
     * }
     *
     * AmazonHealth 类并没有什么花哨的地方。health() 方法只是使用 Spring 的 RestTemplate 向
     * Amazon 首页发起了一个 GET 请求。如果请求成功，则返回一个表明 Amazon 状态为 UP 的 Health
     * 对象。如果请求发生异常，则 health() 返回一个标明 Amazon 状态为 DOWN 的 Health 对象。
     *
     * 下面是 /health 端点响应的一个片段。这里可以看出，如果 Amazon 不可访问，你会看到什么。
     *
     * {
     *      "amazonHealth": {
     *         "status": "DOWN"
     *      },
     *      ...
     * }
     *
     * 除了简单的状态之外，如果你还想向健康记录里添加其他附加信息，可以调用 Health 构造器的
     * withDetail() 方法。例如，要添加异常消息，将其作为健康记录的 reason 字段，可以让
     * catch 块返回这样一个 Health 对象。
     *
     * return Health.down().withDetail("reason", e.getMessage()).build();
     *
     * 修改后，当 Amazon 无法访问时，健康记录看起来是这样的：
     *
     * "amazonHealth": {
     *         "reason": "I/O error on GET request for
     *                    \"http://www.amazon.com\":www.amazon.com;
     *                    nested exception is java.net.UnknownHostException:
     *                    www.amazon.com",
     *         "status": "DOWN"
     * }
     *
     * 如果有很多附加信息，可以多次调用 withDetail() 方法，每次设置一个要放入健康记录的附加字段。
     */
    public static void main(String[] args) {

    }

}
