/*
2016年6月21日更新，支持内容区域为iframe的情况,支持多个滚动条嵌套，兼容性在ie9以上,去掉了鼠标滚轮事件
 * 一个页面可以绑定多个滚动条并且互不影响,滚动条过多，就会卡了，在变动事件当中加settimeout解决，但是就去掉了滚动条的高度变化时的动画效果
 * 2016年7月20日14:49:28 不支持横向滚动条
 * scrollDir: "y",滚动条的方向
 * contSelector: "",容器
 * scrollBarSelector: "",滚动条
 * showScrollBar: false,滚动条是否始终显示
 * sliderSelector: "",滑块
 * addBtnSelector: "",向下向右按钮，
 * subBtnSelector: "",向左向上按钮
 * btnClkStepSize: 60,点击按钮的时候，每次移动的距离
 * sliderMinHeight: 10,滑块的最小高度，小于此高度的时候，滑块高度使用此值
 * sliderAlwaysShow:false,滑块是否保持显示(false则按照内容多少，自动判断显示、隐藏)
 * scrollAnimTime:80, 每次滚动动画的时间
 * scrollStepTime:10,  每一帧的时长
 * scrollAnimAwait:false, 是否等待上次动画完成
 * wheelStepSize:80,滚轮滚动时候每次运动的距离
 * wheelBindSelector:'', 绑定滚轮事件的dom
 * autoInitUiEvent:true, 是否自动初始化
 * animEasing:   , 动画函数
 * scrollHoverClass:scrollbar-hover,鼠标拖动时候增加的class
 * @events <自定义事件> 通过实例cs.on(type,handler)绑定、通过cs.un(type,handler)解绑、通过cs.fire(type)触发
 *  {
 *       scroll               //滚动条变化
 *       resizeSlider         //滑块尺寸变更 、内容容器改变
 *       sliderMove           //滑块坐标变更
 *       sliderShow           //滑块由隐藏变为显示
 *       sliderHide           //滑块由显示变为隐藏
 *       contChange           //变更了绑定的内容容器
 *       mouseEnter           //hoverDoms hover触发（含滑块、滚动条容器、按钮、内容容器）
 *       mouseLeave           //hoverDoms hover触发（含滑块、滚动条容器、按钮、内容容器）
 *       scrollToAnimSta      //滚动动画开始
 *       scrollToAnimEnd      //滚动动画结束
 *       scrollToAniming      //滚动动画播放中每一帧触发一次...
 * 		 resetSlide           //从隐藏状态显示的时候，调用
 * 		 modified             //dom树高度发生变化的时候，调用。高级浏览器有变动事件，这个事件针对ie9以下
 * 		 scrollup             //如果需要在操作的时候滚动一段距离，调用，并传入参数
 *  }
 * 
 * */
(function(window,document,$){
	var mousewheel= "onmousewheel" in document.documentElement?"mousewheel":"DOMMouseScroll";//判断滚轮事件
	var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver;//观察dom变动
	var requestAnimationFrame = window.requestAnimationFrame ||
								window.mozRequestAnimationFrame ||
								window.webkitRequestAnimationFrame ||
								window.msRequestAnimationFrame||function (callback) { setTimeout(callback, 1000 / 60)};
    var browser={
        macosx: /mac/i.test(navigator.platform),
        mobile: /android|webos|iphone|ipad|ipod|blackberry/i.test(navigator.userAgent),
        overlay: null,
        scroll: null,
        scrolls: [],
        webkit: /webkit/i.test(navigator.userAgent) && !/edge\/\d+/i.test(navigator.userAgent)
    }
	function ScrollBar(dom,opt){
		//内容容器
		this._contDom=$(dom);
		this._sliderBarDom = opt.sliderSelector && this._contDom.next().find(opt.sliderSelector);
        browser.scroll=getBrowserScrollSize();
        // 设置高度，margin，把滚动条移动到显示区域外
//      if(!browser.webkit){
            // var maxHeight=this._contDom.parent().height();
        // this._contDom.css({"margin-bottom":"-"+browser.scroll.height+"px","margin-right":"-"+browser.scroll.width+"px","height":maxHeight+browser.scroll.height});
            // 第二种方案解决横向滚动条露出问题，只兼容ie9+
//          var maxHeight='(100% + ' + browser.scroll.height +'px)';
//          this._contDom.css({"margin-bottom":"-"+browser.scroll.height+"px","margin-right":"-"+browser.scroll.width+"px"});
			this._contDom.css({"margin-right":"-"+browser.scroll.width+"px"});
            //2016年7月19日 去除横向滚动条支持
//          this._contDom[0].style.height="calc"+maxHeight;
//      }
        if(this._contDom.css("max-height")!=="none"||this._contDom.parent().css("max-height")!="none"){
        	var mh=this._contDom.css("max-height")=="none"?this._contDom.parent().css("max-height"):this._contDom.css("max-height");
        	mh=parseInt(mh);//+browser.scroll.height;
      		this._contDom.css("max-height",mh);
        }
		//滑块
		var _sliderbar=this._sliderBarDom;
		//绑定滚轮事件的元素
		this._wheelBindDom = opt.wheelBindSelector ? $(opt.wheelBindSelector) : this._contDom;
		//滚动条,如果没有则是滑块的父级元素
        this._scrollBarDom = opt.scrollBarSelector ? this._contDom.nextAll(opt.scrollBarSelector) : _sliderbar && this._sliderBarDom.parent();
		this.init.call(this,opt);
	}
	var topLeft={x:"Left",y:"Top"},widthHeight={x:"Width",y:"Height"},
	preventDefaultHandler = function(e) {
            e.preventDefault();
        },
    selectEventType = "onselectstart" in document.createElement("div") ? "selectstart" : "mousedown";
    $.fn.scrollBar=function(options){
    	return this.each(function(){
    		return new ScrollBar(this,options);
    	})
    }
	$.extend(ScrollBar.prototype,{
		//在原型上定义需要的方法
		//程序入口
		init:function(opt){
			var _t=this;
			var options=opt||{};
			_t.options=$.extend(true,{
				scrollDir: "y",
//              contSelector: "",
                scrollBarSelector: "",
                sliderSelector: "",
                addBtnSelector: "",
                subBtnSelector: "",
                btnClkStepSize: 60,
                sliderMinHeight: 10,
                sliderAlwaysShow: false,
                scrollAnimTime: 80,
                scrollStepTime: 10,
                scrollAnimAwait: false,
                wheelStepSize: 80,
                wheelBindSelector: '',
                scrollHoverClass:'scrollbar-hover',
                autoInitUiEvent: true,
                animEasing: function(p) {
                        return p
                    }
			},options);
			//根据设置来初始化
			var scr="scroll",_dir=options.scrollDir == 'x' ? 'x' : 'y';
			var _tl=topLeft[_dir],_wh = widthHeight[_dir];
			$.extend(true, _t, {
                _dir: _dir, //'x' || 'y'
                _tl: _tl, //'Top' || 'Left'
                _tl_: _tl.toLowerCase(), //'top' || 'left'
                _stl: scr + _tl, //'scrollTop' || 'scrollLeft'
                _wh: _wh, //'Width' || 'Height'
                _wh_: _wh.toLowerCase(), //'width' || 'height'
                _swh: scr + _wh //'scrollWidth' || 'scrollHeight'
            });
            _t.initCustEvent().initDOMEvent();            
            return _t;
		},
		//自定义事件
		initCustEvent:function(){
			var _t=this;
			_event=$(_t);
			$.each({
				on:"on",//绑定
				un:"ubind",//解绑
				fire: "triggerHandler" /*触发*/
				},function(key,val){
					_t[key] = function() {
	                	_event[val].apply(_event, arguments);
	                    return _t;
	                };
	          });
			return _t;
		},
		//初始化调用
		initDOMEvent:function(){
			var _t=this,opts=this.options;
			//滑块
			var _sliderbar=_t._sliderBarDom;
            //文档对象
            _t.doc=$(document);
            //自动初始化
            if (opts.autoInitUiEvent) {
				
                //初始化滚动条控制按钮（add、sub分别对应滚动条坐标增加、减少控制按钮）
                _t.initButton("add")
                    .initButton("sub")
                    /*初始化滑块拖动功能*/
                    .initSliderDragEvent()
                    /*初始化滚动条点击重新定位功能*/
                    .initScrollBarEvent()
                    /*重算滑块尺寸*/
                    .resizeSlider()
                    /*重算滑块坐标*/
                    .removeSlider()
                    /*绑定内容滚动交互，同步滑块坐标*/
                    .bindContScroll()
                    
                    /*绑定hover交互*/
                    .bindContHover()
                   /*绑定浏览器窗口大小改变事件*/
                    .resize()
                    /*绑定dom变动事件，如果不支持，则在引起dom变动的方法当中手动触发modified事件*/
                    .mutation()
                    /*当元素初始状态不可见，点击显示的时候，手动触发resetSlide事件*/
                    .resetSlide()
                    /*控制滚动条是否始终显示*/
                    .showScrollbar()
                    /*传递参数使滚动条滚动指定高度*/
                    .scrollUp()
                    /*鼠标滚轮在滚动条上滚动的时候也要能触发滚动*/
                    .bindScrollBarMouseWheel();
            }
		},
		//初始化按钮
		initButton:function(btnType){
			var _t=this,opts=this.options,btnSel=opts[btnType+"BtnSelector"];
			//当存在按钮的时候，执行，否则直接返回_t
			if(btnSel){
				var btnDom=_t["_"+btnType+"BtnDom"]=_t._contDom.find(btnSel);
				var baseNum = btnType == "add" ? 1 : -1;
				var doc=_t.doc;
				//点击按钮的时候要执行的动画
				function btnAnim(){
					_t.scrollToAnim(_t.getScrollPosition() + _t.options.btnClkStepSize * basicNum);
				}
				//解绑事件
				function btnMouseUp(e){
					e.preventDefault();
					_t.un("scrollToAnimEnd", btnAnim);
					doc.unbind("mouseup", btnMouseUp);
				}
				//绑定鼠标按下事件
				btnDom.on("mousedown", function(e) {
                    e.preventDefault();
                    docW.on("mouseup", btnMouseUp);
                    _t.on("scrollToAnimEnd", btnAnim);
                    btnAnim();
                });
			}
			return _t;
		},
		//将滚动条绑定到另一个容器当中
		replaceCont:function(count){
			var _t=this,autoInit=_t.options.autoInitUiEvent;
			//解除已绑定的DOM事件
            autoInitEvt && _t.unbindContScroll().unbindContHover().unbindScrollBarMouseWheel();
            //重新初始化滑块尺寸、坐标、各DOM元素事件绑定
            autoInitEvt && _t.resizeSlider().removeSlider().bindContScroll().bindContHover().bindScrollBarMouseWheel();
            _t.fire("contChange");
            return _t;
		},
		//绑定鼠标滑过事件
		bindContHover:function(){
			var _t=this;
			var contParent=_t._contParent=_t._contDom.parent();
			_t._scH=_t.getScrollSize();
			_t._conH=_t.getContSize();
			
			contParent.on("mouseover",_t._contHover||(_t._contHover=function(){
				if(_t._scH>_t._conH){
					_t._scrollBarDom.css("visibility","visible");
				}else{
					_t._scrollBarDom.css("visibility","hidden");
				}
			})).on("mouseout",_t._contOut||(_t._contOut=function(){
				if(_t.options.showScrollBar && _t._scH>_t._conH){
					_t._scrollBarDom.css("visibility","visible");
					return;
				}
				_t._scrollBarDom.css("visibility","hidden");
			}))
			return _t;
		},
		//移除鼠标滑过事件
		unbindContHover:function(){
			var _t = this;
			var contParent=_t._contParent;
			contParent.unbind("mouseover",_t._contHover).unbind("mouseout",_t._contOut);
		},
		//容器滚动事件
		bindContScroll:function(){
			var _t=this,opts=_t.options;;
			_t._contDom.on("scroll",_t._contScrollHandler||(_t._contScrollHandler=function(){
				_t.removeSlider();
			}));
			return _t;
		},
		//移除滚动事件
		unbindContScroll: function() {
            var _t = this,
                _contScrollHandler = _t._contScrollHandler;
            _contScrollHandler && _t._contW.unbind("scroll", _contScrollHandler);
            return _t;
        },
        //始终显示滚动条
        showScrollbar:function(){
        	var _t = this,opt=this.options;
        	if(opt.showScrollBar && _t._scH>_t._conH){
        		_t._scrollBarDom.css("visibility","visible");
        	}
        	return _t; 
        },
        //初始化拖动事件
        initSliderDragEvent:function(){
        	var _t=this,sliderBarDom=_t._sliderBarDom,sliderEl=sliderBarDom&&sliderBarDom[0];
        	if(sliderEl){
        		var doc=_t.doc,dragStaPagePos, dragStaScrollBarRate, dragStaScrollPos;
        		/* document的museup事件释放拖动（除了鼠标释放，自动加载下一屏的需求中也需要自动释放拖动，所以后面通过releaseDrag将该方法暴露出去） */
                function docMouseupHandler(e) {
                    dragStaPagePos = null;
                    e && e.preventDefault();
                    sliderBarDom.parent().removeClass(_t.options.scrollHoverClass);//鼠标按下时给元素增加状态
                    sliderEl.releaseCapture && sliderEl.releaseCapture(); //for ie6 解除绑定滑块事件监听
                    doc.unbind(selectEventType, preventDefaultHandler); //重启拖选
                    doc.unbind("mouseup contextmenu", docMouseupHandler).unbind("mousemove", docMousemoveHandler);
                };
                /* 暴露主动释放拖拽的功能接口 */
                _t.releaseDrag = docMouseupHandler;
                //拖拽
                function docMousemoveHandler(e) {
                    if (dragStaPagePos == null) return;
                    //起始坐标点 + (鼠标位移距离*滚动条最大坐标值/滚动条容器尺寸)
                    _t.scrollTo(dragStaScrollPos + (_t.getPageXY(e) - dragStaPagePos) * dragStaScrollBarRate);
                };
                /* 滑块被按下时启动拖拽 */
                sliderBarDom.on("mousedown", function(e) {
                    e.stopPropagation();
                    e.preventDefault();
                    dragStaPagePos = _t.getPageXY(e); //鼠标按下时页面中的所在坐标
                    sliderBarDom.parent().addClass(_t.options.scrollHoverClass);//鼠标按下时给元素增加状态
                    dragStaScrollBarRate = _t.getScrollSize() / _t.getScrollBarSize(); //鼠标按下时计算滚动条最大值与滚动条容器的比率(用于将鼠标位移距离转换为滚动条位移距离)
                    dragStaScrollPos = _t.getScrollPosition(); //鼠标按下时滚动条所在的坐标点
                    sliderEl.setCapture && sliderEl.setCapture(); //for ie6 绑定事件监听到滑块
                    doc.on(selectEventType, preventDefaultHandler) //关闭拖选
                        .on("mouseup contextmenu", docMouseupHandler)
                        .on("mousemove", docMousemoveHandler);
                });
        	}
        	return _t;
        },
        //绑定滚动条上的鼠标滚动事件
        bindScrollBarMouseWheel:function(){
        	var _t=this,sliderBarDom=_t._sliderBarDom,sliderEl=sliderBarDom&&sliderBarDom[0];
        	if(sliderEl){
        		_t._scrollBarDom.on(mousewheel,function(e){
        			e.preventDefault();
        			var delta = e.originalEvent.wheelDelta * -1 || e.originalEvent.detail;
        			if(delta>0){
        				basicNum=1;
        			}else{
        				basicNum=-1;
        			}
        			_t.scrollToAnim(_t.getScrollPosition() + _t.options.wheelStepSize * basicNum);
        		})
        	}
        	return _t;
        },
        //移除滚动条滚轮事件
        unbindScrollBarMouseWheel:function(){
        	var _t=this;
        	_t._scrollBarDom.off(mousewheel);
        },
        //点击滚动条容器触发滚动,同时移动滑块位置
        initScrollBarEvent: function() {
            var _t = this,
                scrollBarDom = _t._scrollBarDom;
            scrollBarDom && scrollBarDom.on("mousedown", function(e) {
                _t.scrollToByPagePos(e);
            });
            return _t;
        },
        _animTimer: null,
        _animProgress: null,
        //滚动动画
        scrollToAnim:function(toVal,stepTime,animTime,isAwait,animEasing){
        	var _t=this,opts=_t.options,easing = opts.animEasing;
        	if(_t._animTimer){
        		if (isAwait == null ? opts.scrollAnimAwait : isAwait) return;
                _t.scrollToAnimStop();
        	}
        	stepTime = stepTime || opts.scrollStepTime;
            animTime = animTime || opts.scrollAnimTime;
            var formVal = _t.getScrollPosition(),
                currVal = formVal,
                distance = toVal - formVal,
                frameCount = Math.ceil(animTime / stepTime),
                frameProgress = 0,
                progress = 0;
            _t.fire('scrollToAnimSta');
            var animate=requestAnimationFrame;
            //开始动画
            animate(function(){
            	frameProgress++;
                progress = frameProgress / frameCount;
                currVal = formVal + distance * easing(progress);
                _t.scrollTo(currVal);
                _t._animProgress = {
                    progress: progress,
                    currVal: currVal,
                    formVal: formVal,
                    toVal: toVal,
                    distance: distance
                };
                _t.fire('scrollToAniming', _t._animProgress);
                if (frameProgress == frameCount) {
                    _t.scrollToAnimStop();
                } else {
//                  animate(arguments.callee);
                    _t._animTimer = animate(arguments.callee);
                }
            })
            return _t;
        },
        //终止动画
        scrollToAnimStop: function() {
            var _t = this;
            clearTimeout(_t._animTimer);
            _t._animTimer = null;
            _t.fire('scrollToAnimEnd', _t._animProgress);
        },
        /* 根据event对象及dir ["x"||"y"]得到鼠标坐标 */
        getPageXY: function(event, dir) {
            return event["page" + ((dir || this._dir).toUpperCase())];
        },
        //按照pageXY定位滚动条坐标 scrollPosDiff是滑块操作时候的初始差值
        //如果不传scrollPosDiff则以当前pageXY作为滑块中心点,否则以scrollPosDiff与当前pageXY的差值作为滚动条新坐标
        scrollToByPagePos: function(e, scrollPosDiff) {
            var _t = this;
            _t.scrollTo(_t.getScrollSize() * (_t.getPageXY(e) - _t.getScrollBarPosition() - _t.getSliderSize() * .5) / (_t.getScrollBarSize() || 1));
            return _t;
        },
        //按差值调整滚动条所在位置
        scrollAdd: function(diffVal) {
            var _t = this;
            _t.scrollTo(_t.getScrollPosition() + (diffVal || 0));
            return _t;
        },
        //改变滚动条所在位置到positionVal
        scrollTo: function(positionVal) {
            var _t = this;
            _t._contDom.eq(0)[_t._stl](positionVal);
            _t.fire('scroll');
            return _t;
        },
        //取得滚动条所在位置坐标值
        getScrollPosition: function() {
            return this._contDom[0][this._stl] || 0;
        },
        //取得滚动条最大可滚动到的坐标值
        getMaxScrollPosition: function() {
            return this.getScrollSize() - this.getContSize();
        },
        //内容容器高度
        getContSize: function() {
//          return this._contDom[this._wh_]() - browser.scroll[this._wh_];
            return this._contDom[this._wh_]();
        },
        //可滚动内容尺寸
        getScrollSize: function() {
            var _t = this;
            return Math.max(_t.getContSize(), this._contDom[0][_t._swh] || 0);
        },
        //当前内容区的滚动高度
        getContScrollSize:function(){
        	var _t = this;
            return this._contDom[0][_t._stl] || 0;
        },
        //滚动条模拟元素尺寸
        getScrollBarSize: function() {
            return this._scrollBarDom[this._wh_]();
        },
        //滚动条模拟元素坐标
        getScrollBarPosition: function() {
            return this._scrollBarDom.offset()[this._tl_];
        },
        //滑块当前应在尺寸
        getSliderSize: function() {
            var _t = this;
            return Math.max(_t.getScrollBarSize() * _t.getContSize() / (_t.getScrollSize() || 1), _t.options.sliderMinHeight);
        },
        //滑块最大坐标
        getMaxSliderPosition: function() {
            return this.getScrollBarSize() - this._sliderBarDom[this._wh_]();
        },
        //滑块当前应在坐标
        getSliderPosition: function() {
            var _t = this,
                maxSliderPos = _t.getMaxSliderPosition();
            return Math.min(maxSliderPos * _t.getScrollPosition() / (_t.getMaxScrollPosition() || 1), maxSliderPos);
        },
        //重新计算并设置滑块尺寸
        resizeSlider: function(sizeVal) {
            var _t = this,
                sliderEl = _t._sliderBarDom && _t._sliderBarDom[0];
            if (sliderEl) {
                sizeVal = isNaN(sizeVal) ? _t.getSliderSize() : sizeVal;
                var sliderDisplay = _t.options.sliderAlwaysShow || sizeVal < _t.getScrollBarSize() ? "" : "none",
                    originDisplay = sliderEl.style.display;
                if (sliderDisplay != originDisplay) {
                    sliderEl.style.display = sliderDisplay;
                    _t.fire(sliderDisplay == "none" ? "sliderHide" : "sliderShow");
                }
                sliderEl.style[_t._wh_] = sizeVal + 'px';
                _t.fire('resizeSlider');
            }
            return _t;
        },
        //重新计算并设置滑块位置
        removeSlider: function(positionVal) {
            var _t = this,
                sliderEl = _t._sliderBarDom && _t._sliderBarDom[0];
            if (sliderEl) {
                sliderEl.style[_t._tl_] = (isNaN(positionVal) ? _t.getSliderPosition() : positionVal) + 'px';
                _t.fire('sliderMove');
            }
            return _t;
        },
        //屏幕大小发生改变的时候，改变滚动条
        resize: function(){
        	var _t = this;
        	var time;
        	$(window).resize(function(){
        		if(time){
        			clearTimeout(time);
        		}
        		time=setTimeout(function(){
                    // 采用计算的方法，在浏览器发生变化时，横向滚动条会显示出来
                    //var maxHeight=_t._contDom.parent().height();
	        		_t._scH=_t.getScrollSize();
					_t._conH=_t.getContSize();
                    // if(!browser.webkit){
                    //     _t._contDom.css({"height":maxHeight+browser.scroll.height});
                    // }
        			_t.resizeSlider().removeSlider().showScrollbar();
        		},150);
        	})
        	return _t;
        },
        //dom变化时调用
        mutation:function(){
        	var _t = this;
        	_t.DOMAttrModified=false;
        	_t.MutationObserver=MutationObserver?true:false;
        	if(MutationObserver){
				var target=_t._contDom[0];
				var timer;
				var observer=new MutationObserver(function(mutations){
					if(timer){
						clearTimeout(timer);
					}
					//如果运行的时候卡，就用settimeout
					timer=setTimeout(function(){
						_t._scH=_t.getScrollSize();
						_t._conH=_t.getContSize();
						_t.resizeSlider().removeSlider().showScrollbar();
					},50)
					
				})
				var config={attributes: true,subtree:true, childList: true, characterData: true};
				observer.observe(target,config);
			}else{
				_t._contDom.on("DOMAttrModified",function(){
					_t.DOMAttrModified=true;
					if(timer){
						clearTimeout(timer);
					}
					timer=setTimeout(function(){
						_t._scH=_t.getScrollSize();
						_t._conH=_t.getContSize();
						_t.resizeSlider().removeSlider().showScrollbar();
					},50)
					
				})
				if(!_t.DOMAttrModified&&!_t.MutationObserver){
					_t._contDom.on("modified",function(){
						if(timer){
							clearTimeout(timer);
						}
						timer=setTimeout(function(){
							_t._scH=_t.getScrollSize();
							_t._conH=_t.getContSize();
							_t.resizeSlider().removeSlider().showScrollbar();
						},50)
		       		})
				}
			}
			return _t;
       },
       resetSlide:function(){
       		var _t=this;
   			_t._contDom.on("resetSlide",function(e){
				_t._scH=_t.getScrollSize();
				_t._conH=_t.getContSize();
				_t.resizeSlider().removeSlider().showScrollbar();
       		})
   			return _t;
       },
       scrollUp:function(){
       		var _t=this;
       		_t._contDom.on("scrollup",function(e,data){
				var now=_t.getContScrollSize();
				_t.scrollToAnim(now+parseFloat(data),null,300);
       		})
       		return _t;
       }
	});
    // 获取浏览器的默认滚动条宽高,webkit内核浏览器返回0，其他浏览器返回具体值
    function getBrowserScrollSize(actualSize) {

//      if (browser.webkit && !actualSize) {
//          return {
//              "height": 0,
//              "width": 0
//          };
//      }

        if (!browser.outer) {
            var css = {
                "border": "none",
                "box-sizing": "content-box",
                "height": "200px",
                "margin": "0",
                "padding": "0",
                "width": "200px"
            };
            browser.inner = $("<div>").css($.extend({}, css));
            browser.outer = $("<div>").css($.extend({
                "left": "-1000px",
                "overflow": "scroll",
                "position": "absolute",
                "top": "-1000px"
            }, css)).append(browser.inner).appendTo("body");
        }

        browser.outer.scrollLeft(1000).scrollTop(1000);

        return {
            "height": Math.ceil((browser.outer.offset().top - browser.inner.offset().top) || 0),
            "width": Math.ceil((browser.outer.offset().left - browser.inner.offset().left) || 0)
        };
    }
//	window.ScrollBar = ScrollBar;
})(window,document,jQuery)