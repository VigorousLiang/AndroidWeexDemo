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
/******/ 	return __webpack_require__(__webpack_require__.s = 6);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */,
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */
/***/ (function(module, exports, __webpack_require__) {

var __vue_exports__, __vue_options__
var __vue_styles__ = []

/* styles */
__vue_styles__.push(__webpack_require__(7)
)

/* script */
__vue_exports__ = __webpack_require__(8)

/* template */
var __vue_template__ = __webpack_require__(9)
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
__vue_options__.__file = "/Users/vigorousliang/AndroidProject/weexTest/weexServer/src/test.vue"
__vue_options__.render = __vue_template__.render
__vue_options__.staticRenderFns = __vue_template__.staticRenderFns
__vue_options__._scopeId = "data-v-7186ddc1"
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
/* 7 */
/***/ (function(module, exports) {

module.exports = {
  "row": {
    "flexDirection": "row"
  },
  "cell": {
    "justifyContent": "center",
    "marginTop": "2",
    "marginBottom": "2",
    "marginLeft": "2",
    "marginRight": "2",
    "borderWidth": "2",
    "borderStyle": "solid",
    "borderColor": "rgb(65,184,131)",
    "backgroundColor": "rgba(65,184,131,0.25)"
  },
  "text": {
    "color": "rgb(65,184,131)",
    "textAlign": "center",
    "fontWeight": "200"
  }
}

/***/ }),
/* 8 */
/***/ (function(module, exports, __webpack_require__) {

"use strict";


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

var VIEWPORT = 750;
var COLUMN = 15;
var ROW = Math.ceil(COLUMN * WXEnvironment.deviceHeight / WXEnvironment.deviceWidth);

module.exports = {
    data: function data() {
        var matrix = [];
        for (var i = 0; i < ROW; i++) {
            matrix.push([]);
            for (var j = 0; j < COLUMN; j++) {
                matrix[i].push(Math.floor(Math.random() * 10));
            }
        }
        return {
            cellSize: VIEWPORT / COLUMN - 4 + 'px',
            fontSize: Math.round(VIEWPORT / COLUMN / 2) + 'px',
            matrix: matrix
        };
    }
};

/***/ }),
/* 9 */
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: ["canvas"]
  }, _vm._l((_vm.matrix), function(vector, r) {
    return _c('div', {
      key: r,
      staticClass: ["row"]
    }, _vm._l((vector), function(number, c) {
      return _c('div', {
        key: c,
        staticClass: ["cell"],
        style: {
          width: _vm.cellSize,
          height: _vm.cellSize
        }
      }, [_c('text', {
        staticClass: ["text"],
        style: {
          fontSize: _vm.fontSize
        }
      }, [_vm._v(_vm._s(number))])])
    }))
  }))
},staticRenderFns: []}
module.exports.render._withStripped = true

/***/ })
/******/ ]);