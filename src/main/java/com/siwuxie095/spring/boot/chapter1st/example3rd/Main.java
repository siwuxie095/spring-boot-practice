package com.siwuxie095.spring.boot.chapter1st.example3rd;

/**
 * @author Jiajing Li
 * @date 2021-04-06 07:52:35
 */
public class Main {

    /**
     * Spring Boot 入门
     *
     * 从根本上来说，Spring Boot 的项目只是普通的 Spring 项目，只是它们正好用到了 Spring Boot 的起步依赖和自动配置
     * 而已。因此，那些你早已熟悉的从头创建 Spring 项目的技术或工具，都能用于 Spring Boot 项目。然而，还是有一些简便
     * 的途径可以用来开启一个新的 Spring Boot 项目。
     *
     * 最快的方法就是安装 Spring Boot CLI，安装后就可以开始写代码，接着通过 CLI 来运行就好。
     *
     *
     *
     * 1、安装 Spring Boot CLI
     *
     * Spring Boot CLI 提供了一种有趣的、不同寻常的 Spring 应用程序开发方式。后续会详细解释 CLI 提供的功能。这里先
     * 来看看如何安装 Spring Boot CLI，这样才能运行代码。
     *
     * Spring Boot CLI 有好几种安装方式。
     * （1）用下载的分发包进行安装。
     * （2）用 Groovy Environment Manager 进行安装。
     * （3）通过 OS X Homebrew 进行安装。
     * （4）使用 MacPorts 进行安装。
     *
     * 下面分别看一下这几种方式。除此之外，还要了解如何安装 Spring Boot CLI 的命令行补全支持，如果你在 BASH 或 zsh
     * shell 里使用 CLI，这会非常有用（抱歉了，各位 Windows 用户）。先来看看如何用分发包手工安装 Spring Boot CLI
     * 吧。
     *
     *
     * 1.1、手工安装 Spring Boot CLI
     *
     * 安装 Spring Boot CLI 最直接的方法大约是下载、解压，随后将它的 bin 目录添加到系统路径里。你可以从以下两个地址
     * 下载分发包：
     * （1）https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/1.4.3.RELEASE/
     * spring-boot-cli-1.4.3.RELEASE-bin.zip
     * （2）https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/1.4.3.RELEASE/
     * spring-boot-cli-1.4.3.RELEASE-bin.tar.gz
     *
     * 下载完成之后，把它解压到文件系统的任意目录里。在解压后的目录里，你会找到一个 bin 目录，其中包含了一个 spring.bat
     * 脚本（用于 Windows 环境）和一个 spring 脚本（用于 Unix 环境）。把这个 bin 目录添加到系统路径里，然后就能使用
     * Spring Boot CLI 了。
     *
     * PS：为 Spring Boot 建立符号链接：如果是在安装了 Unix 的机器上使用 Spring Boot CLI，最好建立一个指向解压目录
     * 的符号链接，然后把这个符号链接添加到系统路径，而不是实际的目录。这样后续升级 Spring Boot 新版本，或是转换版本，
     * 都会很方便，只要重建一下符号链接，指向新版本就好了。
     *
     * 你可以先浅尝辄止，看看你所安装的 CLI 版本号：
     *
     * spring --version
     *
     * 如果一切正常，你会看到安装好的 Spring Boot CLI 的版本号。
     *
     * 虽然这是手工安装，但一切都很容易，而且不要求你安装任何附加的东西。如果你是 Windows 用户，也别无选择，这是唯一的
     * 安装方式。但如果你使用的是 Unix 机器，而且想要稍微自动化一点的方式，那么可以试试 Software Development Kit
     * Manager。
     *
     *
     * 1.2、使用 Software Development Kit Manager 进行安装
     *
     * 软件开发工具管理包（Software Development Kit Manager，SDKMAN，曾用简称 GVM）也能用来安装和管理多版本 Spring
     * Boot CLI。使用前，你需要先从 http://sdkman.io 获取并安装 SDKMAN。最简单的安装方式是使用命令行：
     *
     * curl -s get.sdkman.io | bash
     *
     * 跟随输出的指示就能完成 SDKMAN 的安装。然后在命令行里执行了如下命令：
     *
     *  source "/Users/siwuxie095/.sdkman/bin/sdkman-init.sh"
     *
     * 注意，用户不同，这条命令也会有所不同。这里的用户目录是 /Users/siwuxie095，因此这也是 shell 脚本的根路径。你需
     * 要根据实际情况稍作调整。一旦安装好了 SDKMAN，就可以用下面的方式来安装 Spring Boot CLI 了：
     *
     * sdk install springboot
     * spring --version
     *
     * 假设一切正常，你将看到 Spring Boot 的当前版本号。
     *
     * 如果想升级新版本的 Spring Boot CLI，只需安装并使用即可。使用 SDKMAN 的 list 命令可以找到可用的版本：
     *
     * sdk list springboot
     *
     * list 命令列出了所有可用版本，包括已经安装的和正在使用的。从中选择一个进行安装，然后就可以正常使用。举例来说，要安
     * 装 Spring Boot CLI 1.4.3.RELEASE，直接使用 install 命令，指定版本号：
     *
     * sdk install springboot 1.4.3.RELEASE
     *
     * 这样就会安装一个新版本，随后你会被询问是否将其设置为默认版本。要是你不想把它作为默认版本，或者想要切换到另一个版本，
     * 可以用 use 命令：
     *
     * sdk use springboot 1.4.3.RELEASE
     *
     * 如果你希望把那个版本作为所有 shell 的默认版本，可以使用 default 命令：
     *
     * sdk default springboot 1.4.3.RELEASE
     *
     * 使用 SDKMAN 来管理 Spring Boot CLI 有一个好处，你可以便捷地在 Spring Boot 的不同版本之间切换。这样你可以在
     * 正式发布前试用快照版本（snapshot）、里程碑版本（milestone）和尚未正式发布的候选版本（release candidate），
     * 试用后再切回稳定版本进行其他工作。
     *
     *
     * 1.3、使用 Homebrew 进行安装
     *
     * 如果要在 OS X 的机器上进行开发，你还可以用 Homebrew 来安装 Spring Boot CLI。Homebrew 是 OS X 的包管理器，
     * 用于安装多种不同应用程序和工具。要安装 Homebrew，最简单的方法就是运行安装用的 Ruby 脚本：
     *
     * ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/ master/install)"
     *
     * 你可以在 http://brew.sh 看到更多关于 Homebrew 的内容（还有安装方法）。
     *
     * 要用 Homebrew 来安装 Spring Boot CLI，你需要引入 Pivotal 的 tap：
     *
     * brew tap pivotal/tap
     *
     * PS：tap 是向 Homebrew 添加额外仓库的一种途径。Pivotal 是 Spring 及 Spring Boot 背后的公司，通过它的 tap
     * 可以安装 Spring Boot。
     *
     * 在有了 Pivotal 的 tap 后，就可以像下面这样安装 Spring Boot CLI 了：
     *
     * brew install springboot
     *
     * Homebrew 会把 Spring Boot CLI 安装到 /usr/local/bin，之后可以直接使用。可以通过检查版本号来验证安装是否成功：
     *
     * spring --version
     *
     * 这条命令应该会返回刚才安装的 Spring Boot 版本号。你也可以运行代码看看。
     *
     *
     * 1.4、使用 MacPorts 进行安装
     *
     * OS X 用户还有另一种安装 Spring Boot CLI 的方法，即使用 MacPorts，这是 Mac OS X 上另一个流行的安装工具。要使
     * 用 MacPorts 来安装 Spring Boot CLI，必须先安装 MacPorts，而 MacPorts 还要求安装 Xcode。此外，使用不同版本
     * 的 OS X 时，MacPorts 的安装步骤也会有所不同。因此这里建议你根据 https://www.macports.org/install.php 的安
     * 装指南来安装 MacPorts。
     *
     * 一旦安装好了 MacPorts，就可以用以下命令来安装 Spring Boot CLI 了：
     *
     *  sudo port install spring-boot-cli
     *
     * MacPorts 会把 Spring Boot CLI 安装到 /opt/local/share/java/spring-boot-cli，并在 /opt/local/bin 里放
     * 一个指向其可执行文件的符号链接。在安装 MacPorts 后，/opt/local/bin 这个目录应该就在系统路径里了。你可以检查版
     * 本号来验证安装是否成功：
     *
     * spring --version
     *
     * 这条命令应该会返回刚才安装的 Spring Boot 的版本号。你也可以运行代码，看看效果如何。
     *
     *
     * 1.5、开启命令行补全
     *
     * Spring Boot CLI 为基于 CLI 的应用程序的运行、打包和测试提供了一套好用的命令。而且，每个命令都有好多选项。要记住
     * 这些东西实属不易，命令行补全能帮助记忆怎么使用 Spring Boot CLI。
     *
     * 如果用 Homebrew 安装 Spring Boot CLI，那么命令行补全已经安装完毕。但如果是手工安装或者用 SDKMAN 安装的，那就
     * 需要执行脚本或者手工安装。（如果是通过 MacPorts 安装的 Spring Boot CLI，那么你也不必考虑命令行补全）
     *
     * 你可以在 Spring Boot CLI 安装目录的 shell-completion 子目录里找到补全脚本。有两个不同的脚本，一个是针对 BASH
     * 的，另一个是针对 zsh 的。要使用 BASH 的补全脚本，可以在命令行里键入以下命令（假设安装时用的是 SDKMAN）：
     *
     * . ~/.sdkman/springboot/current/shell-completion/bash/spring
     *
     * 这样，在当前的 shell 里就可以使用 Spring Boot CLI 的补全功能了，但每次开启一个新的 shell 都要重新执行一次上面
     * 的命令才行。你也可以把这个脚本复制到你的个人或系统脚本目录里，这个目录的位置在不同的 Unix 里也会有所不同，可以参考
     * 系统文档（或 Google）了解细节。
     *
     * 开启了命令行补全之后，在命令行里键入 spring 命令，然后按 Tab 键就能看到下一步该输什么的提示。选中一个命令后，键入
     * --（两个连字符）后再按 Tab，就会显示出该命令的选项列表。
     *
     * 如果你在 Windows 上进行开发，或者没有用 BASH 或 zsh，那就无缘使用这些命令行补全脚本了。尽管如此，如果你用的是
     * Spring Boot CLI 的 shell，那一样也有命令补全：
     *
     * spring shell
     *
     * 和 BASH、zsh 的命令补全脚本（在 BASH/zsh shell 里执行的）不同，Spring Boot CLI shell 会新开一个特别针对
     * Spring Boot 的 shell，在里面可以执行各种 CLI 命令，Tab 键也能有命令补全。
     *
     * Spring Boot CLI 为 Spring Boot 提供了快速上手和构建简单原型应用程序的途径。后续会看到，在正确的生产运行时环境
     * 下，它也能用于开发生产应用程序。
     *
     * 尽管如此，与大部分 Java 项目的开发相比，Spring Boot CLI 的流程还是不太符合常规。通常情况下，Java 项目用 Gradle
     * 或 Maven 这样的工具构建出 WAR 文件，再把这些文件部署到应用服务器里。即便 CLI 模型让你感到不太舒服，你仍然可以在
     * 传统方式下充分利用大部分 Spring Boot 特性。Spring Initializr 可以成为你万里长征的第一步（只是要放弃那些用到
     * Groovy 语言灵活性的特性，比如自动依赖和 import 解析）。
     *
     *
     *
     * 2、使用 Spring Initializr 初始化 Spring Boot 项目
     *
     * 万事开头难，你需要设置一个目录结构存放各种项目内容，创建构建文件，并在其中加入各种依赖。Spring Boot CLI 消除了不
     * 少设置工作，但如果你更倾向于传统 Java 项目结构，那你应该看看 Spring Initializr。
     *
     * Spring Initializr 从本质上来说就是一个 Web 应用程序，它能为你生成 Spring Boot 项目结构。虽然不能生成应用程序
     * 代码，但它能为你提供一个基本的项目结构，以及一个用于构建代码的 Maven 或 Gradle 构建说明文件。你只需要写应用程序
     * 的代码就好了。
     *
     * Spring Initializr 有几种用法。
     * （1）通过 Web 界面使用。
     * （2）通过 Spring Tool Suite 使用。
     * （3）通过 IntelliJ IDEA 使用。
     * （4）使用 Spring Boot CLI 使用。
     *
     * 下面分别看看这几种用法，先从 Web 界面开始。
     *
     *
     * 2.1、使用 Spring Initializr 的 Web 界面
     *
     * 要使用 Spring Initializr，最直接的办法就是用浏览器打开 https://start.spring.io/，你应该能看到一个表单。
     *
     * 表单的头两个问题是，你想用 Maven 还是 Gradle 来构建项目，以及使用 Spring Boot 的哪个版本。程序默认生成 Maven
     * 项目，并使用 Spring Boot 的最新版本（非里程碑和快照版本），但你也可以自由选择其他选项。
     *
     * 然后表单要你指定项目的一些基本信息。最起码你要提供项目的 Group 和 Artifact，以及指定额外的信息，比如版本号和基础
     * 包名。这些信息是用来生成 Maven 的 pom.xml 文件（或者 Gradle 的 build.gradle 文件）的。
     *
     * PS：Spring Initializr 是生成空 Spring 项目的 Web 应用程序，可以视为开发过程的第一步。
     *
     * 然后表单要你指定项目依赖，最简单的方法就是在文本框里键入依赖的名称。随着你的输入会出现匹配依赖的列表，选中一个（或
     * 多个）依赖，选中的依赖就会加入项目。
     *
     * 其实这里的依赖和 Spring Boot 起步依赖是对应的。实际上，在这里选中依赖，就相当于告诉 Initializr 把对应的起步依
     * 赖加到项目的构建文件里。
     *
     * 填完表单，选好依赖，点击 "Generate Project" 按钮，Spring Initializr 就会为你生成一个项目。浏览器将会以 ZIP
     * 文件的形式（文件名取决于 Artifact 字段的内容）把这个项目下载下来。根据你的选择，ZIP 文件的内容也会略有不同。不管
     * 怎样，ZIP 文件都会包含一个极其基础的项目，让你能着手使用 Spring Boot 开发应用程序。
     *
     * 举例来说，假设你在 Spring Initializr 里指定了如下信息。
     * （1） Artifact：myapp
     * （2）包名：myapp
     * （3）类型：Gradle 项目
     * （4）依赖：Web 和 JPA
     *
     * 点击 "Generate Project"，就能获得一个名为 myapp.zip 的 ZIP 文件。
     *
     * PS：Initializr 创建的项目，提供了构建 Spring Boot 应用程序所需的基本内容。
     *
     * 项目里基本没有代码，除了几个空目录外，还包含了如下几样东西。
     * （1）build.gradle：Gradle 构建说明文件。如果选择 Maven 项目，这就会换成 pom.xml。
     * （2）Application.java：一个带有 main() 方法的类，用于引导启动应用程序。
     * （3）ApplicationTests.java：一个空的 JUnit 测试类，它加载了一个使用 Spring Boot 自动配置功能的 Spring 应用
     * 程序上下文。
     * （4）application.properties：一个空的 properties 文件，你可以根据需要添加配置属性。
     *
     * 在 Spring Boot 应用程序中，就连空目录都有自己的意义。static 目录放置的是 Web 应用程序的静态内容（JavaScript、
     * 样式表、图片等等）。还有，后续你将看到，用于呈现模型数据的模板会放在 templates 目录里。
     *
     * 你很可能会把 Initializr 生成的项目导入 IDE。如果你用的 IDE 是Spring Tool Suite，则可以直接在 IDE 里创建项目。
     * 下面来看看 Spring Tool Suite 是怎么创建 Spring Boot 项目的。
     *
     *
     * 2.2、在 Spring Tool Suite 里创建 Spring Boot 项目
     *
     * 长久以来，Spring Tool Suite 一直都是开发 Spring 应用程序的不二之选。从 3.4.0 版本开始，它就集成了 Spring
     * Initializr，这让它成为开始上手 Spring Boot 的好方法。
     *
     * PS：Spring Tool Suite 是 Eclipse IDE 的一个发行版，增加了诸多能辅助 Spring 开发的特性。你可以从
     * https://github.com/spring-projects/toolsuite-distribution/wiki/Spring-Tool-Suite-3 下载
     * Spring Tool Suite。
     *
     * 要在 Spring Tool Suite 里创建新的 Spring Boot 应用程序，在 File 菜单里选中 New > Spring Starter Project
     * 菜单项，随后 Spring Tool Suite 会显示一个与之前相仿的对话框。
     *
     * 这个对话框要求填写的信息和 Spring Initializr 的 Web 界面里是一样的。事实上，你在这里提供的数据会被发送给 Spring
     * Initializr，用于创建项目 ZIP 文件，这和使用 Web 表单是一样的。
     *
     * PS：Spring Tool Suite 集成了 Spring Initializr，可以在 IDE 里创建并直接导入 Spring Boot 项目。
     *
     * 你必须认识到一点，Spring Tool Suite 的 Spring Starter Project 对话框，其实是把项目生成的工作委托给
     * https://start.spring.io/ 上的 Spring Initializr 来做的，因此必须联网才能使用这一功能。
     *
     * 在开发的过程中，你会发现 Spring Tool Suite 针对 Spring Boot 还有一些锦上添花的功能。比如，可以在 Run 菜单里选
     * 中 Run As > Spring Boot Application，在嵌入式服务器里运行你的应用程序。
     *
     * 注意，Spring Tool Suite 是通过 REST API 与 Initializr 交互的，因此只有连上 Initializr 它才能正常工作。如果
     * 你的开发机离线，或者 Initializr 被防火墙阻断了，那么 Spring Tool Suite 的 Spring Starter Project 向导是无
     * 法使用的。
     *
     *
     * 2.3、在 IntelliJ IDEA 里创建 Spring Boot 项目
     *
     * IntelliJ IDEA 是非常流行的 IDE，IntelliJ IDEA 14.1 已经支持 Spring Boot 了！
     *
     * 要在 IntelliJ IDEA 里创建新的 Spring Boot 应用程序，在 File 菜单里选择 New > Project。你会看到几屏内容，问
     * 的问题和 Initializr 的 Web 应用程序以及 Spring Tool Suite 类似。
     *
     *
     * 2.4、在 Spring Boot CLI 里使用 Initializr
     *
     * 如前面所述，如果你想仅仅写代码就完成 Spring 应用程序的开发，那么 Spring Boot CLI 是个不错的选择。然而，Spring
     * Boot CLI 的功能还不限于此，它有一些命令可以帮你使用 Initializr，通过它上手开发更传统的 Java 项目。
     *
     * Spring Boot CLI 包含了一个 init 命令，可以作为 Initializr 的客户端界面。init 命令最简单的用法就是创建 Spring
     * Boot 项目的基线：
     *
     * spring init
     *
     * 在和 Initializr 的 Web 应用程序通信后，init 命令会下载一个 demo.zip 文件。解压后你会看到一个典型的项目结构，
     * 包含一个 Maven 的 pom.xml 构建描述文件。Maven 的构建说明只包含最基本的内容，即只有 Spring Boot 基线和测试
     * 起步依赖。你可能会想要更多的东西。
     *
     * 假设你想要构建一个 Web 应用程序，其中使用 JPA 实现数据持久化，使用 Spring Security 进行安全加固，可以用
     * --dependencies 或 -d 来指定那些初始依赖：
     *
     * spring init -dweb,jpa,security
     *
     * 这条命令会下载一个 demo.zip 文件，包含与之前一样的项目结构，但在 pom.xml 里增加了 Spring Boot 的 web、jpa
     * 和 security 起步依赖。请注意，在 -d 和依赖之间不能加空格，否则就变成了下载一个 ZIP 文件，文件名是 "web,jpa,
     * security"。
     *
     * 现在，假设你想用 Gradle 来构建项目。没问题，用 --build 参数将 Gradle 指定为构建类型：
     *
     * spring init -dweb,jpa,security --build gradle
     *
     * 默认情况下，无论是 Maven 还是 Gradle 的构建说明都会产生一个可执行 JAR 文件。但如果你想要一个 WAR 文件，那么可以
     * 通过 --packaging 或者 -p 参数进行说明：
     *
     * spring init -dweb,jpa,security --build gradle -p war
     *
     * 到目前为止，init 命令只用来下载 ZIP 文件。如果你想让 CLI 帮你解压那个 ZIP 文件，可以指定一个用于解压的目录：
     *
     * spring init -dweb,jpa,security --build gradle -p war myapp
     *
     * 此处的最后一个参数说明你希望把项目解压到 myapp 目录里去。
     *
     * 此外，如果你希望 CLI 把生成的项目解压到当前目录，可以使用 --extract 或者 -x 参数：
     *
     * spring init -dweb,jpa,security --build gradle -p jar -x
     *
     * init 命令还有不少其他参数，包括基于 Groovy 构建项目的参数、指定用 Java 版本编译的参数，还有选择构建依赖的
     * Spring Boot 版本的参数。可以通过 help 命令了解所有参数的情况：
     *
     * spring help init
     *
     * 你也可以查看那些参数都有哪些可选项，为 init 命令带上 --list 或 -l 参数就可以了：
     *
     * spring init -l
     *
     * 你一定注意到了，尽管 spring init –l 列出了一些 Initializr 支持的参数，但并非所有参数都能直接为 Spring Boot
     * CLI 的 init 命令所支持。举例来说，用 CLI 初始化项目时，你不能指定根包的名字，它默认为 demo。spring help init
     * 会告诉你 CLI 的 init 命令都支持哪些参数。
     *
     *
     * 无论你是用 Initializr 的 Web 界面，在 Spring Tool Suite 里创建项目，还是用 Spring Boot CLI 来初始化项目，
     * Spring Boot Initializr 创建出来的项目都有相似的项目布局，和你之前开发过的 Java 项目没什么不同。
     */
    public static void main(String[] args) {

    }

}
