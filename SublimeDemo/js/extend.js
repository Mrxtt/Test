(function($){
	var PageSwitch=(function(){
		function PageSwitch(element,options) {
			this.settings=$.extend(true,$.fn.PageSwitch.defaults,optins||{});
			this.element=elment;
			this.init();
		}
		PageSwitch.prototype={
			/*说明: 初始化插件*/
			/*说明: 初始化dom结构，布局，分页及绑定*/			
			init:function() {
				var me=this;
				me.selectors=me.settings.selectors;
				me.sections=me.selectors.sections;
				me.section=me.selectors.section;

				me.direction=me.settings.direction=="vertical"?true:false;
				me.pagesCount=me.pagesCount();
				me.index=(me.settings.index>=0 && me.settings.index<pagesCount)?me.setting.index:0;
				
				if(!me.direction){
					me._initLayout();
				}
				if(me.settings.pagination){
					me._initPaging();
				}
				me._initEvent();
			},
			pagesCount:function(){
				return this.section.length();
			},
			switchlength:function(){
				retrun this.direction?this.element.height():this.element.width();
			},
			_initLayout:function(){
				var me=this;
				var width=(me.pagesCount*100)+"%",
					celWidth=(100/me.pagesCount).toFixed(2)+"%";
				me.sections.width(width);
				me.section.width(cellWidth);
				me.section.css("float","left");
			},
			_initPaging:function(){
				var me=this,
					pagesClass=me.selectors.page.substring(1),
					activeClass=me.selector.active.subtring(1);

				var pageHtml="<ur class="+pagesClass+">";
				for (var i =0; i < me.selectors.pagesCount; i++) {
					pageHtml+="<li></li>"
				}
				me.element.append(pageHtml);
				var pages=me.element.find(me.selectors.page);
				me.pageItem=pages.find("li");
				me.pageItem.eq(me.index).addClass(me.activeClass);

				//判断方向
				if (me.direction) {
					page.addClass("vertical");
				}else{
					pages.addClass('horizontal');
				}
			},
			_initEvent:function(){

			}
		}
		return PageSwitch();
	})()
	var PageSwitch=function(options) {
		return this.each(function(){
			var me=$(this),
				instance=me.data("PageSwitch");
			if (!instance) {
				instance=new PageSwitch(me,options);
				me.data('PageSwitch', intance);
			}
			if($.type(options)==="string") retrun instance[options];
		});
	}
	$.fn.PageSwitch.defaults={
		selectors:{
			sections:".sections",
			section:".section",
			page:".pages",
			active:".active"
		},
		index:0//页面开始的索引值
		easing:"ease",
		duration:500,//单位毫秒
		loop:false,//是否可以循环播放
		pagination:true,//分页处理
		keyboard：true,
		direction:"vertical",//horizontal
		callback:""
	}
	$(function() {
		$("[data-PageSwitch]").PageSwitch();
	})
})(jQuery)