<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2017/2/28
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Zhangys</title>
    <link href="${pageContext.request.contextPath}/scripts/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="${pageContext.request.contextPath}/styles/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!-- Plugins -->
    <link href="${pageContext.request.contextPath}/styles/css/animations.css" rel="stylesheet">
    <!-- Worthy core CSS file -->
    <link href="${pageContext.request.contextPath}/styles/css/style.css" rel="stylesheet">
    <!-- Custom css -->
    <link href="${pageContext.request.contextPath}/styles/css/custom.css" rel="stylesheet">
</head>
<body class="no-trans">
<%--start--%>
<header class="header fixed clearfix navbar navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="header-left clearfix">
                    <!-- logo -->
                    <div class="logo smooth-scroll">
                        <a href="#banner"><img id="logo" src="${pageContext.request.contextPath}/files/images/logo.png" alt="Worthy"></a>
                    </div>
                    <div class="site-name-and-slogan smooth-scroll">
                        <div class="site-name"><a href="#banner">Zhangys</a></div>
                        <div class="site-slogan">www.zhangys.com</div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="header-right clearfix">
                    <div class="main-navigation animated">
                        <nav class="navbar navbar-default" role="navigation">
                            <div class="container-fluid">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                </div>
                                <div class="collapse navbar-collapse scrollspy smooth-scroll" id="navbar-collapse-1">
                                    <ul class="nav navbar-nav navbar-right">
                                        <li class="active"><a href="#banner">Home</a></li>
                                        <li><a href="#about">About</a></li>
                                        <li><a href="#services">Skill</a></li>
                                        <li><a href="#portfolio">Photo</a></li>
                                        <%--<li><a href="#clients">Clients</a></li>--%>
                                        <li><a href="#contact">Contact</a></li>
                                    </ul>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- header end -->

<!-- banner start -->
<!-- ================ -->
<div id="banner" class="banner" style="background: #123;">
    <div class="banner-image" style="">
        <img src="${pageContext.request.contextPath}/files/images/banner.jpg" alt="" width="100%">
    </div>
    <div class="banner-caption">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2 object-non-visible" data-animation-effect="fadeIn">
                    <h1 class="text-center"><span>张英松</span></h1>
                    <p class="lead text-center">千古江山，英雄无觅，孙仲谋处。舞榭歌台，风流总被，雨打风吹去。斜阳草树，寻常巷陌，人道寄奴曾住。想当年、金戈铁马，气吞万里如虎。元嘉草草，封狼居胥，赢得仓皇北顾。
                        四十三年，望中犹记，烽火扬州路。可堪回首，佛狸祠下，一片神鸦社鼓！凭谁问、廉颇老矣，尚能饭否？</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- banner end -->

<!-- section start -->
<!-- ================ -->
<div class="section clearfix object-non-visible" data-animation-effect="fadeIn">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h1 id="about" class="title text-center">About <span>Zhangys</span></h1>
                <p class="lead text-center">大连理工大学.Dalian University of Technology.</p>
                <div class="space"></div>
                <div class="row">
                    <div class="col-md-6">
                        <img src="${pageContext.request.contextPath}/files/images/section-image-1.jpg" alt="">
                        <div class="space"></div>
                    </div>
                    <%--<div class="col-md-6" style="background: #9acfea">--%>
                    <div class="col-md-6">
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;寄醉里挑灯看剑，梦回吹角连营。八百里分麾下灸，五十弦翻塞外声。沙场秋点兵。马作的卢飞快，弓如霹雳弦惊。了却君王天下事，嬴得生前身后名。可怜白发生！ </p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;东风夜放花千树，更吹落、星如雨。宝马雕车香满路。凤箫声动，玉壶光转，一夜鱼龙舞。蛾儿雪柳黄金缕，笑语盈盈暗香去。众里寻他千百度。蓦然回首，那人却在，灯火阑珊处。</p>
                        <ul class="list-unstyled">
                            <li><i class="fa fa-caret-right pr-10 text-colored"></i> 1996.11 辽宁省锦州市</li>
                            <li><i class="fa fa-caret-right pr-10 text-colored"></i> 2001.9 ~ 2007.6 小学</li>
                            <li><i class="fa fa-caret-right pr-10 text-colored"></i> 2007.9 ~ 2010.6 中学</li>
                            <li><i class="fa fa-caret-right pr-10 text-colored"></i> 2010.9 ~ 2013.6 辽西育明高级中学</li>
                            <li><i class="fa fa-caret-right pr-10 text-colored"></i> 2013.9 ~ now 大连理工大学软件学院</li>
                        </ul>
                    </div>
                </div>
                <div class="space"></div>
                <h2>岳阳楼记</h2>
                <div class="row">
                    <%--<div class="col-md-6" style="background: #5cb85c">--%>
                        <div class="col-md-6">
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;庆历四年春， 滕子京谪守巴陵郡。 越明年， 政通人和， 百废具兴。 乃重修岳阳楼， 增其旧制， 刻唐贤今人诗赋于其上。 属予作文以记之。</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;予观夫巴陵胜状， 在洞庭一湖。 衔远山， 吞长江， 浩浩汤汤， 横无际涯； 朝晖夕阴， 气象万千。 此则岳阳楼之大观也， 前人之述备矣。 然则北通巫峡， 南极潇湘， 迁客骚人， 多会于此， 览物之情， 得无异乎？
                            若夫淫雨霏霏， 连月不开， 阴风怒号， 浊浪排空； 日星隐曜， 山岳潜形； 商旅不行， 樯倾楫摧； 薄暮冥冥， 虎啸猿啼。 登斯楼也， 则有去国怀乡， 忧谗畏讥， 满目萧然， 感极而悲者矣。</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;至若春和景明， 波澜不惊， 上下天光， 一碧万顷； 沙鸥翔集， 锦鳞游泳； 岸芷汀兰， 郁郁青青。 而或长烟一空， 皓月千里， 浮光跃金， 静影沉璧， 渔歌互答， 此乐何极！ 登斯楼也， 则有心旷神怡， 宠辱偕忘， 把酒临风， 其喜洋洋者矣。
                            嗟夫！ 予尝求古仁人之心， 或异二者之为， 何哉？ 不以物喜， 不以己悲； 居庙堂之高则忧其民； 处江湖之远则忧其君。 是进亦忧， 退亦忧。 然则何时而乐耶？ 其必曰 “先天下之忧而忧， 后天下之乐而乐” 乎？ 噫！ 微斯人， 吾谁与归？</p>
                    </div>
                    <div class="col-md-6">
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingOne">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                            兴趣爱好
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="panel-body">
                                        上不知天文下不知地理 , 最大爱好葛优瘫.
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingTwo">
                                    <h4 class="panel-title">
                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                            社团经历
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                                    <div class="panel-body">
                                        大学呆过也带过几个社团 , 聊胜于无.
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="headingThree">
                                    <h4 class="panel-title">
                                        <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                            我的心得
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                                    <div class="panel-body">
                                        没啥心得 , 吃饭睡觉打豆豆.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- section end -->

<!-- services start -->
<!-- ================ -->
<div class="section translucent-bg bg-image-1 blue">
    <div class="container object-non-visible" data-animation-effect="fadeIn">
        <h1 id="services"  class="text-center title">专业技能树</h1>
        <div class="space"></div>
        <div class="row">
            <div class="col-sm-6">
                <div class="media">
                    <div class="media-body text-right">
                        <div>
                            <h4 class="media-heading">Java</h4>
                        </div>
                        <%--进度条--%>
                        <div class="progress" style="transform:rotate(-180deg);" >
                            <div class="progress-bar" role="progressbar" aria-valuenow="90"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                    <div class="media-right">
                        <i class="fa fa-cog"></i>
                    </div>
                </div>
                <div class="media">
                    <div class="media-body text-right">
                        <h4 class="media-heading">Linux</h4>
                        <%--进度条--%>
                        <div class="progress" style="transform:rotate(-180deg);">
                            <div class="progress-bar" role="progressbar" aria-valuenow="85"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 85%;">
                            </div>
                        </div>
                        <p>能熟练运用Linux系统,熟悉Shell脚本,对Linux内核有一定的了解,包括Linux进程,中断,文件系统,网络等各方面知识点,实习期间基于Linux系统做过云计算相关开发.</p>
                    </div>
                    <div class="media-right" >
                            <i class="fa fa-desktop"></i>
                    </div>
                </div>
                <div class="media">
                    <div class="media-body text-right">
                        <h4 class="media-heading">docker</h4>
                        <%--进度条--%>
                        <div class="progress" style="transform:rotate(-180deg);">
                            <div class="progress-bar" role="progressbar" aria-valuenow="80"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 80%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                    <div class="media-right">
                        <i class="fa fa-cloud"></i>
                    </div>
                </div>
                <div class="media">
                    <div class="media-body text-right">
                        <h4 class="media-heading">数据结构与算法</h4>
                        <%--进度条--%>
                        <div class="progress" style="transform:rotate(-180deg);">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                    <div class="media-right">
                        <i class="fa fa-book"></i>
                    </div>
                </div>
            </div>
            <div class="space visible-xs"></div>
            <div class="col-sm-6">
                <div class="media">
                    <div class="media-left">
                        <%--<i class="fa fa-leaf"></i>--%>
                        <i class="fa fa-codepen"></i>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">SSH框架</h4>
                        <%--进度条--%>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                </div>
                <div class="media">
                    <div class="media-left">
                        <i class="fa fa-anchor"></i>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">JavaScript</h4>
                        <%--进度条--%>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="70"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                </div>
                <div class="media">
                    <div class="media-left">
                        <%--<i class="fa fa-child"></i>--%>
                        <i class="fa fa-database"></i>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">数据库</h4>
                        <%--进度条--%>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                </div>
                <div class="media">
                    <div class="media-left">
                        <i class="fa fa-leaf"></i>
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">微服务</h4>
                        <%--进度条--%>
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="30"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 30%;">
                            </div>
                        </div>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iure aperiam consequatur quo quis exercitationem reprehenderit dolor vel ducimus, voluptate eaque suscipit iste placeat.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- services end -->

<!-- section start -->
<!-- ================ -->
<div class="default-bg space blue">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h1 class="text-center">醉里挑灯看剑，梦回吹角连营。</h1>
            </div>
        </div>
    </div>
</div>
<!-- section end -->
<%--<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >企业网站模板</a></div>--%>

<!-- photo start -->
<!-- ================ -->
<div class="section">
    <div class="container">
        <h1 class="text-center title" id="portfolio">《苏幕遮》</h1>
        <div class="separator"></div>
        <p class="lead text-center">碧云天,黄叶地,秋色连波,波上寒烟翠.山映斜阳天接水,芳草无情,更在斜阳外.
            <br> 黯乡魂,追旅思.夜夜除非,好梦留人睡.明月楼高休独倚,酒入愁肠,化作相思泪.</p>
        <br>
        <div class="row object-non-visible" data-animation-effect="fadeIn">
            <div class="col-md-12">
                <!-- isotope filters start -->
                <div class="filters text-center">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#" data-filter="*">All</a></li>
                        <li><a href="#" data-filter=".web-design">暖风</a></li>
                        <li><a href="#" data-filter=".app-development">杨柳</a></li>
                        <li><a href="#" data-filter=".site-building">屠苏</a></li>
                    </ul>
                </div>
                <!-- isotope filters end -->

                <!-- portfolio items start -->
                <div class="isotope-container row grid-space-20">
                    <%--photo.jsp在这里插入--%>
                    <div class="col-sm-6 col-md-3 isotope-item web-design">
                        <div class="image-box">
                            <div class="overlay-container">
                                <img src="${pageContext.request.contextPath}/files/images/portfolio-9.jpg" alt="">
                                <a class="overlay" data-toggle="modal" data-target="#project-9">
                                    <i class="fa fa-search-plus"></i>
                                    <span>暖风</span>
                                </a>
                            </div>
                            <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-9">水墨</a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="project-9" tabindex="-1" role="dialog" aria-labelledby="project-9-label" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="project-9-label">水墨</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h3>Project Description</h3>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <p>寒蝉凄切，对长亭晚，骤雨初歇。都门帐饮无绪，留恋处，兰舟催发。执手相看泪眼，竟无语凝噎。念去去，千里烟波，暮霭沉沉楚天阔。</p>
                                                <p>多情自古伤离别，更那堪，冷落清秋节！今宵酒醒何处？杨柳岸，晓风残月。此去经年，应是良辰好景虚设。便纵有千种风情，更与何人说？</p>
                                            </div>
                                            <div class="col-md-6">
                                                <img src="${pageContext.request.contextPath}/files/images/portfolio-9.jpg" alt="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal end -->
                    </div>
                    <div class="col-sm-6 col-md-3 isotope-item site-building">
                        <div class="image-box">
                            <div class="overlay-container">
                                <img src="${pageContext.request.contextPath}/files/images/portfolio-10.jpg" alt="">
                                <a class="overlay" data-toggle="modal" data-target="#project-10">
                                    <i class="fa fa-search-plus"></i>
                                    <span>屠苏</span>
                                </a>
                            </div>
                            <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-10">水墨</a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="project-10" tabindex="-1" role="dialog" aria-labelledby="project-10-label" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="project-10-label">水墨</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h3>Project Description</h3>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <p>黄金榜上，偶失龙头望。明代暂遗贤，如何向。未遂风云便，争不恣游狂荡。何须论得丧？才子词人，自是白衣卿相。</p>
                                                <p>烟花巷陌，依约丹青屏障。幸有意中人，堪寻访。且恁偎红倚翠，风流事，平生畅。青春都一饷。忍把浮名，换了浅斟低唱！</p>
                                            </div>
                                            <div class="col-md-6">
                                                <img src="${pageContext.request.contextPath}/files/images/portfolio-10.jpg" alt="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal end -->
                    </div>

                    <div class="col-sm-6 col-md-3 isotope-item web-design">
                        <div class="image-box">
                            <div class="overlay-container">
                                <img src="${pageContext.request.contextPath}/files/images/portfolio-11.jpg" alt="">
                                <a class="overlay" data-toggle="modal" data-target="#project-11">
                                    <i class="fa fa-search-plus"></i>
                                    <span>暖风</span>
                                </a>
                            </div>
                            <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-11">水墨</a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="project-11" tabindex="-1" role="dialog" aria-labelledby="project-11-label" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="project-11-label">水墨</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h3>Project Description</h3>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <p>长安古道马迟迟，高柳乱蝉嘶。夕阳岛外，秋风原上，目断四天垂。</p>
                                                <p>归云一去无踪迹，何处是前期？狎兴生疏，酒徒萧索，不似少年时。</p>
                                            </div>
                                            <div class="col-md-6">
                                                <img src="${pageContext.request.contextPath}/files/images/portfolio-11.jpg" alt="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal end -->
                    </div>

                    <div class="col-sm-6 col-md-3 isotope-item app-development">
                        <div class="image-box">
                            <div class="overlay-container">
                                <img src="${pageContext.request.contextPath}/files/images/portfolio-12.jpg" alt="">
                                <a class="overlay" data-toggle="modal" data-target="#project-12">
                                    <i class="fa fa-search-plus"></i>
                                    <span>杨柳</span>
                                </a>
                            </div>
                            <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-12">水墨</a>
                        </div>
                        <!-- Modal -->
                        <div class="modal fade" id="project-12" tabindex="-1" role="dialog" aria-labelledby="project-12-label" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                        <h4 class="modal-title" id="project-12-label">水墨</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h3>Project Description</h3>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <p>自春来、惨绿愁红，芳心是事可可。日上花梢，莺穿柳带，犹压香衾卧。暖酥消、腻云亸，终日厌厌倦梳裹。无那。恨薄情一去，音书无个。</p>
                                                <p>早知恁么，悔当初、不把雕鞍锁。向鸡窗，只与蛮笺象管，拘束教吟课。镇相随、莫抛躲，针线闲拈伴伊坐。和我，免使年少，光阴虚过。</p>
                                            </div>
                                            <div class="col-md-6">
                                                <img src="${pageContext.request.contextPath}/files/images/portfolio-12.jpg" alt="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal end -->
                    </div>
                </div>
                <!-- portfolio items end -->
            </div>
        </div>
    </div>
</div>
<!-- photo end -->

<!-- section start -->
<!-- ================ -->
<div class="default-bg space">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <h1 class="text-center">金戈铁马 气吞万里如虎</h1>
            </div>
        </div>
    </div>
</div>
<!-- section end -->

<!-- footer start -->
<!-- ================ -->
<footer id="footer">

    <!-- .footer start -->
    <!-- ================ -->
    <div class="footer section">
        <div class="container">
            <h1 class="title text-center" id="contact">Contact Me</h1>
            <div class="space"></div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="footer-content">
                        <p class="large">东南形胜,三吴都会,钱塘自古繁华.烟柳画桥,风帘翠幕,参差十万人家.云树绕堤沙.怒涛卷霜雪,天堑无涯.市列珠玑,户盈罗绮,竞豪奢.</p>
                        <p class="large">重湖叠巘清嘉.有三秋桂子,十里荷花.羌管弄晴,菱歌泛夜,嬉嬉钓叟莲娃.千骑拥高牙.乘醉听萧鼓,吟赏烟霞.异日图将好景,归去凤池夸.</p>
                        <ul class="list-icons">
                            <li><i class="fa fa-map-marker pr-10"></i> 北京市, 100000</li>
                            <li><i class="fa fa-phone pr-10"></i> +00 18840831500</li>
                            <li><i class="fa fa-fax pr-10"></i> +00 123456 </li>
                            <li><i class="fa fa-envelope-o pr-10"></i> zys_5413@163.com</li>
                        </ul>
                        <ul class="social-links">
                            <li class="flickr"><a target="_blank" href="#"><i class="fa fa-wechat"></i></a></li>
                            <li class="pinterest"><a target="_blank" href="#"><i class="fa fa-qq"></i></a></li>
                            <li class="googleplus"><a target="_blank" href="#"><i class="fa fa-google-plus"></i></a></li>
                            <li class="facebook"><a target="_blank" href="#"><i class="fa fa-facebook"></i></a></li>
                            <li class="twitter"><a target="_blank" href="#"><i class="fa fa-twitter"></i></a></li>
                            <li class="youtube"><a target="_blank" href="#"><i class="fa fa-youtube"></i></a></li>
                            <%--<li class="skype"><a target="_blank" href="#"><i class="fa fa-skype"></i></a></li>--%>
                            <%--<li class="linkedin"><a target="_blank" href="#"><i class="fa fa-linkedin"></i></a></li>--%>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="footer-content">
                        <form role="form" id="footer-form">
                            <div class="form-group has-feedback">
                                <label class="sr-only" for="name2">Name</label>
                                <input type="text" class="form-control" id="name2" placeholder="Name" name="name2" required>
                                <i class="fa fa-user form-control-feedback"></i>
                            </div>
                            <div class="form-group has-feedback">
                                <label class="sr-only" for="email2">Email address</label>
                                <input type="email" class="form-control" id="email2" placeholder="Enter email" name="email2" required>
                                <i class="fa fa-envelope form-control-feedback"></i>
                            </div>
                            <div class="form-group has-feedback">
                                <label class="sr-only" for="message2">Message</label>
                                <textarea class="form-control" rows="8" id="message2" placeholder="Message" name="message2" required></textarea>
                                <i class="fa fa-pencil form-control-feedback"></i>
                            </div>
                            <input type="submit" value="Send" class="btn btn-default">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- .footer end -->

    <!-- .subfooter start -->
    <div class="subfooter">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <p class="text-center">Copyright &copy; 2017. *** All rights reserved.</p>
                </div>
            </div>
        </div>
    </div>
    <!-- .subfooter end -->

</footer>
<%--end--%>

<!-- scrollToTop -->
<div class="scrollToTop"><i class="icon-up-open-big"></i></div>
<!-- footer end -->

<!-- Jquery and Bootstap core js files -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugins/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/bootstrap/js/bootstrap.min.js"></script>

<!-- Modernizr javascript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugins/modernizr.js"></script>

<!-- Isotope javascript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugins/isotope/isotope.pkgd.min.js"></script>

<!-- Backstretch javascript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugins/jquery.backstretch.min.js"></script>

<!-- Appear javascript -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/plugins/jquery.appear.js"></script>

<!-- Initialization of Plugins -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js/template.js"></script>

<!-- Custom Scripts -->
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js/custom.js"></script>

</body>
</html>
