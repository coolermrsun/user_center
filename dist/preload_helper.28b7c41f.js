!function(){"use strict";var t="/".replace(/([^/])$/,"$1/"),e=location.pathname,n=e.startsWith(t)&&decodeURI("/".concat(e.slice(t.length)));if(n){var a=document,c=a.head,r=a.createElement.bind(a),i=function(t,e,n){var a,c=e.r[t]||(null===(a=Object.entries(e.r).find((function(e){var n=e[0];return new RegExp("^".concat(n.replace(/\/:[^/]+/g,"/[^/]+").replace("/*","/.+"),"$")).test(t)})))||void 0===a?void 0:a[1]);return null==c?void 0:c.map((function(t){var a=e.f[t][1],c=e.f[t][0];return{type:c.split(".").pop(),url:"".concat(n.publicPath).concat(c),attrs:[["data-".concat(e.b),"".concat(e.p,":").concat(a)]]}}))}(n,{"p":"ant-design-pro","b":"webpack","f":[["54.6af12736.async.js",54],["96.174e7eb5.async.js",96],["110.70b45637.async.js",110],["134.31e76096.async.js",134],["164.76251513.async.js",164],["177.47e5ca31.async.js",177],["p__Welcome.81acbeb2.async.js",185],["278.090e3ef8.async.js",278],["t__plugin-layout__Layout.5012e1ab.chunk.css",301],["t__plugin-layout__Layout.189762a0.async.js",301],["p__TableList__index.20e861b8.async.js",325],["p__User__Login__index.e61e4a48.async.js",366],["390.83388097.async.js",390],["393.69b70a30.async.js",393],["p__User__Register__index.af413b64.async.js",470],["p__Admin.8beff5f1.async.js",551],["635.5e8f103f.async.js",635],["p__Admin__UserManage__index.75cccded.async.js",901]],"r":{"/":[3,4,8,9,16],"/welcome":[1,2,3,6,13,4,8,9,16],"/admin":[0,1,2,3,4,5,15,8,9,16],"/list":[0,1,2,3,4,5,7,10,13,8,9,16],"/user/login":[1,3,5,11],"/user/register":[1,3,5,14],"/admin/user-manage":[0,1,3,4,5,17,2,15,8,9,16]}},{publicPath:"/"});null==i||i.forEach((function(t){var e,n=t.type,a=t.url;if("js"===n)(e=r("script")).src=a,e.async=!0;else{if("css"!==n)return;(e=r("link")).href=a,e.rel="preload",e.as="style"}t.attrs.forEach((function(t){e.setAttribute(t[0],t[1]||"")})),c.appendChild(e)}))}}();