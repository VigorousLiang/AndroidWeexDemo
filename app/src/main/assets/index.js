// { "framework": "Vue"} 

/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, {
/******/ 				configurable: false,
/******/ 				enumerable: true,
/******/ 				get: getter
/******/ 			});
/******/ 		}
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(__webpack_require__.s = 4);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */,
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(5)
)

/* script */
__vue_exports__ = __webpack_require__(6)

/* template */
var __vue_template__ = __webpack_require__(7)
__vue_options__ = __vue_exports__ = __vue_exports__ || {}
if (
  typeof __vue_exports__.default === "object" ||
  typeof __vue_exports__.default === "function"
) {
if (Object.keys(__vue_exports__).some(function (key) { return key !== "default" && key !== "__esModule" })) {console.error("named exports are not supported in *.vue files.")}
__vue_options__ = __vue_exports__ = __vue_exports__.default
}
if (typeof __vue_options__ === "function") {
  __vue_options__ = __vue_options__.options
}
__vue_options__.__file = "/Users/vigorousliang/AndroidProject/weexTest/weexServer/src/index.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-4faad49a"
__vue_options__.style = __vue_options__.style || {}
__vue_styles__.forEach(function (module) {
  for (var name in module) {
    __vue_options__.style[name] = module[name]
  }
})
if (typeof __register_static_styles__ === "function") {
  __register_static_styles__(__vue_options__._scopeId, __vue_styles__)
}

module.exports = __vue_exports__
module.exports.el = 'true'
new Vue(module.exports)


/***/ }),
/* 5 */
/***/ (function(module, exports) {

module.exports = {
  "banner": {
    "width": "750",
    "paddingTop": "25",
    "paddingRight": "25",
    "paddingBottom": "25",
    "paddingLeft": "25",
    "fontSize": "30",
    "textAlign": "center",
    "fontWeight": "bold",
    "color": "#000000",
    "borderBottomWidth": "1",
    "borderStyle": "solid",
    "borderColor": "rgb(0,0,0)",
    "backgroundColor": "rgb(255,255,255)"
  },
  "panel": {
    "width": "600",
    "height": "300",
    "marginLeft": "75",
    "marginTop": "35",
    "marginBottom": "35",
    "flexDirection": "column",
    "verticalAlign": "middle",
    "//justifyContent": "center",
    "backgroundColor": "rgba(162,217,192,0.2)"
  },
  "image": {
    "width": "600",
    "height": "250",
    "borderColor": "#41B883",
    "borderStyle": "solid",
    "borderWidth": "2",
    "backgroundColor": "#666666"
  },
  "text": {
    "width": "600",
    "height": "50",
    "fontSize": "30",
    "textAlign": "center",
    "color": "#000000"
  }
}

/***/ }),
/* 6 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


Object.defineProperty(exports, "__esModule", {
    value: true
});
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//


var modal = weex.requireModule('modal');
var phoneInfo = weex.requireModule('phoneInfo');
var gtjaWEEXNormal = weex.requireModule('GTJAWEEXNormal');
exports.default = {
    data: function data() {
        return {
            lists: [{
                imageUrl: '',
                data: '帮助贫困儿童',
                url: 'http://www.baidu.com'
            }, {
                imageUrl: '',
                data: '君弘融资大酬宾',
                url: 'http://www.baidu.com'
            }, {
                imageUrl: '',
                data: '我也不知道打什么广告了',
                url: 'http://www.baidu.com'
            }]
        };
    },

    methods: {
        showToast: function showToast(msg) {
            phoneInfo.showToast(msg);
        },
        jumpToWebView: function jumpToWebView(url) {
            gtjaWEEXNormal.openURL(url);
        }
    }
};

/***/ }),
/* 7 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('Float', {
    staticStyle: {
      width: "200px",
      height: "200px"
    },
    attrs: {
      "src": "file://assets/floatImage.js"
    }
  })], 1)
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);