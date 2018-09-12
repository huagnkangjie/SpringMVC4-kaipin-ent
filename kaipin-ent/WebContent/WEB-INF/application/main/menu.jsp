<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
		<div id="hNav" class="headNav">
			<div class="navPeanel">
				<div class="send-meeting-btn fr">
					<c:if test="${index == 'index' }">
						<a href="javascript:void(0)" id="send-preachBtn">发布信息</a> 
					</c:if>
					
					<a href="${BASE_PATH }/position/pulishPage" >发布职位</a>
				</div>
				<ul class="lists-details fl" id="ent-menu">
					<li><a href="${BASE_PATH }/home" title="首页">首页</a></li>
					<li><a href="${BASE_PATH }/video">视频</a></li>
					<li><a href="${BASE_PATH }/resume" id="resumeDatas">人才库（0）</a></li>
					<li><a href="${BASE_PATH }/position">职位库</a></li>
					<li><a href="${BASE_PATH }/examCount">笔试管理</a></li>
				</ul>

			</div>
		</div>