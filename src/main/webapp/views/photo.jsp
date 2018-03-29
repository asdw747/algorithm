<%--
  Created by IntelliJ IDEA.
  User: 123
  Date: 2017/3/1
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="col-sm-6 col-md-3 isotope-item web-design">
    <div class="image-box">
        <div class="overlay-container">
            <img src="${pageContext.request.contextPath}/files/images/portfolio-1.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-1">
                <i class="fa fa-search-plus"></i>
                <span>Web Design</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-1">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-1" tabindex="-1" role="dialog" aria-labelledby="project-1-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-1-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-1.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-2.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-2">
                <i class="fa fa-search-plus"></i>
                <span>App Development</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-2">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-2" tabindex="-1" role="dialog" aria-labelledby="project-2-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-2-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-2.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-3.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-3">
                <i class="fa fa-search-plus"></i>
                <span>Web Design</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-3">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-3" tabindex="-1" role="dialog" aria-labelledby="project-3-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-3-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-3.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-4.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-4">
                <i class="fa fa-search-plus"></i>
                <span>Site Building</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-4">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-4" tabindex="-1" role="dialog" aria-labelledby="project-4-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-4-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-4.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-5.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-5">
                <i class="fa fa-search-plus"></i>
                <span>App Development</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-5">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-5" tabindex="-1" role="dialog" aria-labelledby="project-5-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-5-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-5.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-6.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-6">
                <i class="fa fa-search-plus"></i>
                <span>Web Design</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-6">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-6" tabindex="-1" role="dialog" aria-labelledby="project-6-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-6-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-6.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-7.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-7">
                <i class="fa fa-search-plus"></i>
                <span>Site Building</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-7">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-7" tabindex="-1" role="dialog" aria-labelledby="project-7-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-7-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-7.jpg" alt="">
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
            <img src="${pageContext.request.contextPath}/files/images/portfolio-8.jpg" alt="">
            <a class="overlay" data-toggle="modal" data-target="#project-8">
                <i class="fa fa-search-plus"></i>
                <span>Web Design</span>
            </a>
        </div>
        <a class="btn btn-default btn-block" data-toggle="modal" data-target="#project-8">Project Title</a>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="project-8" tabindex="-1" role="dialog" aria-labelledby="project-8-label" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="project-8-label">Project Title</h4>
                </div>
                <div class="modal-body">
                    <h3>Project Description</h3>
                    <div class="row">
                        <div class="col-md-6">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque sed, quidem quis praesentium, ut unde. Quae sed, incidunt laudantium nesciunt, optio corporis quod earum pariatur omnis illo saepe numquam suscipit, nemo placeat dignissimos eius mollitia et quas officia doloremque ipsum labore rem deserunt vero! Magnam totam delectus accusantium voluptas et, tempora quos atque, fugiat, obcaecati voluptatibus commodi illo voluptates dolore nemo quo soluta quis.</p>
                            <p>Molestiae sed enim laboriosam atque delectus voluptates rerum nostrum sapiente obcaecati molestias quasi optio exercitationem, voluptate quis consequatur libero incidunt, in, quod. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos nobis officiis, autem earum tenetur quidem. Quae non dicta earum. Ipsum autem eaque cum dolor placeat corporis quisquam dolorum at nesciunt.</p>
                        </div>
                        <div class="col-md-6">
                            <img src="${pageContext.request.contextPath}/files/images/portfolio-8.jpg" alt="">
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

</body>
</html>
